import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {
	//00908 - Re-connecting Computer Sites
	int n;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	ArrayList<ArrayList<Edge>> path;
	public static void main(String [] args) throws Exception{
		new Prim().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			n = readInt();
			path = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<=n;i++) path.add(new ArrayList<Edge>());
			for(int i=0;i<n-1;i++){
				int f = readInt(), t = readInt(), w = readInt();
				path.get(f).add(new Edge(f,t,w));
				path.get(t).add(new Edge(t,f,w));
			}
			int k = readInt();
			int[][] s = new int[k][3];
			for(int i=0;i<k;i++){
				for(int j=0;j<3;j++) s[i][j] = readInt();
			}
			int m = readInt();
			for(int i=0;i<m;i++){
				int f = readInt(), t = readInt(), w = readInt();
				path.get(f).add(new Edge(f,t,w));
				path.get(t).add(new Edge(t,f,w));
			}
			//PRIM
			PRIM();
			for(int i=0;i<k;i++){
				int f = s[i][0], t = s[i][1], w = s[i][2];
				path.get(f).add(new Edge(f,t,w));
				path.get(t).add(new Edge(t,f,w));
			}
			//PRIM
			PRIM();
			if(bfr.ready()){ bfr.readLine(); System.out.println(); }
		}
	}
	void PRIM(){
		boolean visited[] = new boolean[n+1];
		int totalCost = 0, count = 0, distance[] = new int[n+1];
		for(int i=1;i<=n; distance[i++] = Integer.MAX_VALUE );
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(Edge c : path.get(1)){
			pq.add(new Edge(c.from,c.to,distance[c.to] = c.weight));
		}
		visited[1] = true;
		while(count < n - 1){
			Edge e = pq.poll();
			if(visited[e.to]) continue;
			visited[e.to] = true;
			totalCost += e.weight;
			count++;
			for(Edge c : path.get(e.to)){
				if(!visited[c.to] && c.weight < distance[c.to]){
					pq.add(new Edge(c.from,c.to,distance[c.to] = c.weight));
				}
			}
		}
		System.out.println(totalCost);
	}
	class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int f, int t, int w){
			from = f;
			to = t;
			weight = w;
		}
		public int compareTo(Edge e){ return weight - e.weight; }
	}
}