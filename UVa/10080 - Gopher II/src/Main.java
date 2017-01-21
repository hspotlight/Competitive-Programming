import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	//10080 - Gopher II
	//something wrong with weight of edge 
	int n, m, t, v;//second velocity
	int N, S, T;
	double coorX[], coorY[]; //[0-(n-1)] [n-(n+m-1)]
	int matrix[][], parent[];
	StringTokenizer st;
	boolean found;//
	boolean visited[];//
	int readInt() throws Exception { return (int) readDouble(); }
	double readDouble() throws Exception { return Double.parseDouble(st.nextToken()); }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			st = new StringTokenizer(bfr.readLine());
			n = readInt(); m = readInt(); t = readInt(); v = readInt();
			N = n+m+2; S = n+m; T = S+1;
			matrix = new int[N][N];
			coorX  = new double[n+m]; coorY  = new double[n+m];
			for(int i=0;i<n;i++) matrix[S][i] = 1;//S -> gopher i	
			for(int i=n;i<S;i++) matrix[i][T] = 1;//hole i -> T
			for(int i=0;i<n+m;i++){
				st = new StringTokenizer(bfr.readLine());
				coorX[i] = readDouble(); coorY[i] = readDouble();
				if(i>=n){//hole i 
					for(int j=0;j<n;j++){// gopher j
						double dist = Math.sqrt( Math.pow(coorX[i]-coorX[j], 2) + Math.pow(coorY[i]-coorY[j], 2));
						if(v*t >= dist) matrix[j][i] = 1;
					}
				}
			}
			int maxAlive = n;
			while(true){
				parent = new int[N];
				for(int i=0;i<N;i++) parent[i] = -1;
				found = false;
				visited = new boolean[N];
				recur(S);
				if(parent[T]==-1) break;
				
				int min = Integer.MAX_VALUE;
				int v = T, u = parent[T];
				do{
					min = Math.min(min, matrix[u][v]);
					v = u; u = parent[v];
				}while(v!=S);
				//
				maxAlive -= min;
				//
				v = T; u = parent[T];
				do{
					matrix[u][v] -= min; matrix[v][u] += min;
					v = u; u = parent[v];
				}while(v!=S);
			}
			System.out.println(maxAlive);
		}
	}
	void recur(int u){
		if(u==T) found = true;
		if(!found){
			visited[u] = true;
			for(int v=0;v<N;v++){
				if(!visited[v] && matrix[u][v] > 0){
					parent[v] = u; recur(v);
				}
			}
		}
	}
	void DFS(){
		Stack<Integer> stack = new Stack<Integer>();
		visited = new boolean[N];
		stack.add(S);
		while(!stack.isEmpty()){
			int u = stack.pop();
			if(visited[u]) continue;
			visited[u] = true;
			if(u==T) break;
			for(int v=0;v<N;v++){
				if(!visited[v] && matrix[u][v] > 0){
					stack.add(v); parent[v] = u;
				}
			}
		}
	}
	void BFS(){
		Queue<Integer> qe = new LinkedList<Integer>();
		visited = new boolean[N];
		qe.add(S); visited[S] = true;
		while(!qe.isEmpty()){
			int u = qe.poll();
			if(u==T) break;
			for(int v=0;v<N;v++){
				if(!visited[v] && matrix[u][v] > 0){
					qe.add(v); visited[v] = true;
					parent[v] = u;
				}
			}
		}
	}
}