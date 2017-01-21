import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//00820 - Internet Bandwidth
	int S,T,n;
	int matrix[][], parent[];
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int testcase = 1;
		while( (n = readInt()) != 0){
			matrix = new int[n+1][n+1];
			S = readInt(); T = readInt(); int c = readInt();
			for(int i=0;i<c;i++){
				int u = readInt(), v = readInt(), w = readInt();
				matrix[u][v] += w; matrix[v][u] += w;
			}
			int bandwidth = 0;
			while(true){
				parent = new int[n+1];
				for(int i=0;i<parent.length;i++) parent[i] = -1;
				BFS();
				if(parent[T]==-1) break;
				
				int v = T, u = parent[v];
				int min = 10000;
				while(v!=S){
					min = Math.min(min, matrix[u][v]);
					v = u; u = parent[v];
				}
				
				bandwidth += min;
				v = T; u = parent[v];
				while(v!=S){
					matrix[u][v] -= min; matrix[v][u] += min;
					v = u; u = parent[v];
				}
			}
			System.out.println("Network "+testcase);
			System.out.println("The bandwidth is "+bandwidth+".");
			System.out.println();
			testcase++;
		}
	}
	void BFS(){
		Queue<Integer> qe = new LinkedList<Integer>();
		boolean visited[] = new boolean[n+1];
		qe.add(S); visited[S] = true;
		while(!qe.isEmpty()){
			int u = qe.poll();
			if(u==T) break;
			for(int v=0;v<n+1;v++){
				if(!visited[v] && matrix[u][v] > 0){
					visited[v] = true; parent[v] = u;
					qe.add(v);
				}
			}
		}
	}
}