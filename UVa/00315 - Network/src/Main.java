import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//00315 - Network
	StreamTokenizer stk;
	int N, index;
	int[] num, low, parent, nChildrens;
	boolean[] articulatingPoint;
	ArrayList<ArrayList<Integer>> path;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(true){
			N = Integer.parseInt(bfr.readLine());
			if(N==0) break;
			//input
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<=N;i++) path.add(new ArrayList<Integer>());
			String s;
			while(!(s = bfr.readLine()).equals("0")){
				StringTokenizer st = new StringTokenizer(s);
				int u = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens()){
					int v = Integer.parseInt(st.nextToken());
					path.get(u).add(v);
					path.get(v).add(u);
				}
			}
			//initial
			index = 0;
			num = new int[N+1]; low = new int[N+1]; 
			parent = new int[N+1];
			articulatingPoint = new boolean[N+1];
			nChildrens = new int[N+1];
			//tarjan
			for(int u=1;u<=N;u++){
				if(num[u]==0){//!visited[u]
					tarjan(u);
					//for root's case
					if(nChildrens[u]>1) articulatingPoint[u] = true;
					else articulatingPoint[u] = false;
				}
			}
			//output
			int count = 0;
			for(int i=1;i<=N;i++){
				if(articulatingPoint[i]) count++;
			}
			System.out.println(count);
		}
	}
	void tarjan(int u){
		num[u] = low[u] = ++index;
		for(Integer v : path.get(u)){
			if(num[v] == 0){//!visited[v]
				parent[v] = u;
				nChildrens[u]++;
				tarjan(v);
				low[u] = Math.min(low[u], low[v]);
				if(low[v]>=num[u]) articulatingPoint[u] = true;
			}
			else if (parent[u]!=v) low[u] = Math.min(low[u], num[v]);
		}
	}
}
