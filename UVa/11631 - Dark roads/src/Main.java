import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//11631 - Dark roads
	int N, M, parent[];
	StringTokenizer st; 	
	PriorityQueue<Edge> pq;
	int total;
	int readInt(){ return Integer.parseInt(st.nextToken()); }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			st = new StringTokenizer(bfr.readLine());
			pq = new PriorityQueue<Edge>();
			N = readInt(); M = readInt();
			if(N==0 && M==0) break;
			total = 0;
			for(int i=0;i<M;i++){
				st = new StringTokenizer(bfr.readLine());
				int s = readInt(), d = readInt(), w = readInt();
				total += w;
				pq.add(new Edge(s,d,w));
			}
			kruskal();
		}
	}
	void kruskal(){
		parent = new int[N];
		for(int i=0;i<N;i++) parent[i] = i;
		int cost = 0, count = 0;
		while(count < N-1){		
			Edge e = pq.poll();
			if(union(e)){
				count++;
				cost += e.weight;
			}
		}
		System.out.println(total-cost);
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr==setTo) return false;
		parent[setTo] = setFr;
		return true;
	}
	int find(int node){
		return parent[node]==node? node: (parent[node]=find(parent[node]));
	}
	public class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int f, int t, int w){
			this.from = f;
			this.to = t;
			weight = w;
		}
		public int compareTo(Edge another){
			return (this.weight - another.weight);
		}
	}
}
