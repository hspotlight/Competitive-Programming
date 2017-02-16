import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//10369 - Arctic Network
	int S, N;
	int[] parent, coorX, coorY;
	ArrayList<Edge> list = new ArrayList<Edge>();
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		new Main().run();
	}
	void run() throws Exception{
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int tc = readInt();
		while(tc-->0){
			S = readInt(); N = readInt();
			list.clear();
			parent = new int[N];
			for(int i=0;i<N;i++) parent[i] = i;
			coorX = new int[N]; coorY = new int[N];
			for(int i=0;i<N;i++){
				coorX[i] = readInt(); coorY[i] = readInt();
			}
			
			for(int i=0;i<N;i++){
				for(int j=i+1;j<N;j++){
					list.add(new Edge(i, j, dist(i, j)));
				}
			}
			
			Collections.sort(list);
			double ans = findMinEdge();
			System.out.println(String.format("%.2f", ans));
		}
	}
	double findMinEdge(){
		double last = -1;
		int counter = 0;
		for(Edge e : list){
			if(union(e)){
				counter++;
				last = e.weight;
			}
			if(counter == N-S) break;	
		}
		return last;
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr == setTo) return false;
		parent[setFr] = setTo;
		return true;
	}
	int find(int node){
		return parent[node]==node?node:(parent[node] = find(parent[node]));
	}
	public double dist(int u, int v){
		return Math.sqrt(Math.pow(coorX[u]-coorX[v], 2) +
				Math.pow(coorY[u] - coorY[v], 2));
	}
	public class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		public Edge(int f, int t, double w){
			this.from = f;
			this.to = t;
			weight = w;
		}
		public int compareTo(Edge e){ return Double.compare(weight, e.weight); }
	}
}
