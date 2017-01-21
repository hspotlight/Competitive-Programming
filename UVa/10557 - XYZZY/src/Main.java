import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	//10557 - XYZZY
	int N, point[], energy[];
	boolean visited[];
	StreamTokenizer stk;
	ArrayList<Edge> eList;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		while((N=readInt())!=-1){
			point = new int[N+1];
			eList = new ArrayList<Edge>();
			for(int u=1;u<=N;u++){
				point[u] = readInt();
				int m = readInt();
				while(m-->0) eList.add( new Edge(u,readInt()) );
			}
			boolean ans = bellmanFord() || reachableCycle();
			bfw.write(ans?"winnable\n":"hopeless\n");
		}
		bfw.flush();
		bfw.close();
	}
	boolean reachableCycle(){
		visited = new boolean[N+1];
		for(Edge e : eList){
			if(energy[e.from]<=0) continue;//can start from here
			if(energy[e.from] + point[e.to] > energy[e.to] && reachable(e.from)) return true;
		}
		return false;
	}
	boolean reachable(int node){
		if(node==N) return true;
		visited[node] = true;
		for(Edge e: eList){
			if(e.from==node && !visited[e.to] && reachable(e.to)) return true;
		}
		return false;
	}
	boolean bellmanFord(){//reverse
		energy = new int[N+1];
		Arrays.fill(energy,Integer.MIN_VALUE);
		energy[1] = 100;
		for(int iter=1;iter<N-1;iter++){//
			for(Edge e : eList){
				if(energy[e.from]<=0) continue; 
					energy[e.to] = Math.max(energy[e.to], energy[e.from] + point[e.to]);
			}
		}
		return energy[N] > 0;//check reachable from 1 to N
	}
	public class Edge{
		int from, to;
		public Edge(int from, int to){
			this.from = from;
			this.to   = to;
		}
	}
} 