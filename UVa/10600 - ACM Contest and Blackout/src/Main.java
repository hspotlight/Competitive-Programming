import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//10600 - ACM Contest and Blackout
	StreamTokenizer stk;
	int N, M;
	int[] parent;
	ArrayList<Edge> edgeList, MSTEdge, MSTBEST;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			N = readInt(); M = readInt();
			edgeList = new ArrayList<Edge>();
			
			for(int i=0;i<M;i++){
				int f = readInt(), t = readInt(), w = readInt();
				edgeList.add(new Edge(f,t,w));
			}
			
			int first = KRUSKAL();
			int second = Integer.MAX_VALUE;;
			MSTBEST = MSTEdge;
			for(Edge e : MSTBEST){
				int f = e.from, t = e.to, w = e.weight;
				edgeList.remove(e);
				int test = KRUSKAL();
				if(test >= first && test < second) second = test;
				edgeList.add(new Edge (f,t,w));
			}
			System.out.println(first+" "+second);
		}
	}
	int KRUSKAL(){
		parent = new int[N+1];
		for(int i=1;i<=N;i++) parent[i] = i;
		int index = 0, cost = 0, count = 0;
		Collections.sort(edgeList);
		MSTEdge = new ArrayList<Edge>();
		while(index < edgeList.size() && count < N-1){
			Edge e = edgeList.get(index++);
			if(union(e)){
				MSTEdge.add(e);
				cost += e.weight;
				count++;
			}
		}
		if(count!=N-1) return Integer.MAX_VALUE;
		return cost;
	}
	boolean union(Edge e){
		int fromSet = find(e.from);
		int toSet = find(e.to);
		if(fromSet==toSet) return false;
		parent[toSet] = fromSet;
		return true;
	}
	int find(int node){
		return parent[node]==node? node: (parent[node] = find(parent[node]));
	}
	class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int f, int t, int w){
			from = f; to = t; weight = w;
		}
		public int compareTo(Edge another){
			return this.weight - another.weight;
		}
	}
}
