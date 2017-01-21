import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//11857 - Driving Range
	//sb, bfw = 1.030
	//system.out = 0.890
	int N, M;
	int parent[];
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	ArrayList<Edge> list = new ArrayList<Edge>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(true){
			N = readInt(); M = readInt();
			if(N==0 && M==0) break;
			list.clear();
			for(int i=0;i<M;i++){
				int u = readInt(), v = readInt(), w = readInt();
				list.add(new Edge(u,v,w));
			}
			long min = kruskal();
			System.out.println(min==-1?"IMPOSSIBLE":min);
		}
	}
	long kruskal(){
		int cnt = 0;
		long cost = 0;
		parent = new int[N+1];
		for(int i=0;i<=N;i++) parent[i] = i;
		Collections.sort(list);
		long max = Integer.MIN_VALUE;
		for(Edge e : list){
			if(union(e)){
				cnt++;
				cost += e.weight;
				max = Math.max(max, e.weight);
			}
			if(cnt==N-1) break;
		}
		return cnt==N-1?max:-1;
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr == setTo) return false;
		parent[setTo] = setFr;
		return true;
	}
	int find(int node){
		return parent[node]==node?node:(parent[node] = find(parent[node]));
	}
	public class Edge implements Comparable<Edge>{
		int from, to, weight;
		public Edge(int f, int t, int w){
			from = f;
			to = t;
			weight = w;
		}
		public int compareTo(Edge e){ return weight - e.weight; }
	}
}
