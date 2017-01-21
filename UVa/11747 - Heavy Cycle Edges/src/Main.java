import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//11747 - Heavy Cycle Edges
	int n, m;
	int parent[];
	StreamTokenizer stk;
	ArrayList<Edge> list;
	StringBuilder sb = new StringBuilder();
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			n = readInt(); m = readInt();
			if(n==0 && m==0) break;
			list = new ArrayList<Edge>();
			for(int i=0;i<m;i++){
				int f = readInt(), t = readInt(), w = readInt();
				list.add(new Edge(f,t,w));
			}
			//KRUSKAL
			kruskal();
		}
		bfw.write(""+sb);
		bfw.flush();
	}
	public void kruskal(){
		boolean forest = true;
		boolean first = true;
		parent = new int[n];
		for(int i=0;i<n;i++) parent[i] = i;
		Collections.sort(list);
		for(Edge e : list){
			if(!union(e)) {
				sb.append( first? e.weight : (" "+e.weight));
				forest = first = false;
			}
		}
		if(forest) sb.append("forest");
		sb.append("\n");
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr==setTo) return false;
		parent[setTo] = setFr;
		return true;
	}
	int find(int node){
		return parent[node]==node ? node : (parent[node] = find(parent[node]));
	}
	public class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int f, int t, int w){
			from = f;
			to = t;
			weight= w;
		}
		public int compareTo(Edge another){
			return this.weight - another.weight;
		}
	}
}
