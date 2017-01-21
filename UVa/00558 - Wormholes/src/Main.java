import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//00558 - Wormholes
	int n,alt;
	int distance[];
	ArrayList<ArrayList<Edge>> path;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			n = readInt(); int m = readInt();
			path = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<n;i++) path.add(new ArrayList<Edge>());
			for(int i=0;i<m;i++){
				int u = readInt(), v = readInt(), w = readInt();
				path.get(u).add(new Edge(v,w));
			}
			distance = new int[n];
			for(int i=0;i<n;i++) distance[i] = Integer.MAX_VALUE;
			distance[0] = 0;
			for(int iteration=1;iteration<n;iteration++){
				for(int i=0;i<n;i++){
					for(Edge e : path.get(i)){
						int j = e.to, w = e.weight;
						if(w==0 || distance[i]==Integer.MAX_VALUE) continue;
						if((alt = distance[i]+w) < distance[j]) distance[j] = alt;
					}
				}
			}
			bfw.write((check()?"":"not ")+"possible\n");
		}
		bfw.flush();
	}
	public class Edge{
		int to;
		int weight;
		public Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	boolean check(){
		for(int i=0;i<n;i++){
			for(Edge e : path.get(i)){
				int j = e.to, w = e.weight;
				if(w==0 || distance[i]==Integer.MAX_VALUE) continue;
				if((alt = distance[i]+w) < distance[j]) return true;
			}
		}
		return false;
	}
}