import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//10462 - Is There A Second Way Left
	int N, M;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int) stk.nval; }
	ArrayList<Edge> list = new ArrayList<Edge>();
	ArrayList<Edge> best = new ArrayList<Edge>();
	int[] parent;
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int TC = readInt();
		for(int tc=1;tc<=TC;tc++){
			
			N = readInt(); M = readInt();
			list.clear();
			
			for(int i=0;i<M;i++){
				int u = readInt(), v = readInt(), w = readInt();
				list.add(new Edge(u, v, w));
			}
			
			Collections.sort(list);
			best.clear();
			
			String out = "Case #"+tc+" : ";
			int best1 = kruskal(1);//find first best
			if(best1 == -1) out += "No way";
			else{
				int best2 = Integer.MAX_VALUE;
				for(Edge e : best){
					e.used = false;
					int test = kruskal(0);
					if(test != -1) best2 = Math.min(best2, test);
					e.used = true;
				}
				if(best2 == Integer.MAX_VALUE) out += "No second way";
				else out += ""+best2;
			}
			System.out.println(out);
		}
	}
	int kruskal(int flag){
		parent = new int[N+1];
		for(int i=1;i<=N;i++) parent[i] = i;
		int i = 0, cnt = 0, cost = 0;
		while(i<list.size() && cnt < N-1){
			Edge e = list.get(i);
			if(e.used && union(e)){
				cost += e.weight;
				cnt++;
				if(flag == 1) best.add(e);
			}
			i++;
		}
		return cnt == N-1 ? cost : -1;
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr==setTo) return false;
		parent[setFr] = setTo;
		return true;
	}
	int find(int node){
		return parent[node] == node ? node : (parent[node] = find(parent[node]));
	}
	public class Edge implements Comparable<Edge>{
		int from, to, weight;
		boolean used;
		public Edge(int f, int t, int w){
			from = f;
			to = t;
			weight = w;
			used = true;
		}
		public String toString(){ return "("+from+" "+to+" "+weight+","+used+")"; }
		public int compareTo(Edge e){ return weight - e.weight; }
	}
}
