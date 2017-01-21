import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//00796 - Critical Links
	StreamTokenizer stk;
	int N, count;
	int[] parent, num, low;
	ArrayList<ArrayList<Integer>> path;
	ArrayList<Edge> bridge;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			N = Integer.parseInt(bfr.readLine());
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<N;i++) path.add(new ArrayList<Integer>());
			for(int i=0;i<N;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int from = Integer.parseInt(st.nextToken());
				st.nextToken();
				while(st.hasMoreTokens()){
					int to = Integer.parseInt(st.nextToken());  
						path.get(from).add(to);
						path.get(to).add(from);
				}
			}
			count = 0;
			bridge = new ArrayList<Edge>();
			num = new int[N]; low = new int[N]; parent = new int[N];
			for(int u=0;u<N;u++){
				if(num[u]==0){
					tarjan(u);
				}
			}
			Collections.sort(bridge);
			System.out.println(bridge.size()+" critical links");
			for(Edge e : bridge){
				System.out.println(e);
			}
			if(bfr.ready()) bfr.readLine();
			System.out.println();
		}
	}
	void tarjan(int u){
		num[u] = low[u] = ++count;
		for(Integer v : path.get(u)){
			if(num[v]==0){
				parent[v] = u;
				tarjan(v);
				low[u] = Math.min(low[u], low[v]);
				if(num[v]==low[v]) bridge.add(new Edge(u,v)); 
			}
			else if (parent[u]!=v) low[u] = Math.min(low[u], num[v]);
		}
	}
	class Edge implements Comparable<Edge>{
		int[] node;
		public Edge(int i, int j){
			node = new int[2];
			node[0] = i; node[1] = j;
			Arrays.sort(node);
		}
		public int compareTo(Edge another){
			if(node[0] - another.node[0]==0) return node[1] - another.node[1];
			else return node[0] - another.node[0];
		}
		public String toString(){
			return node[0]+" - "+node[1];
		}
	}
}
