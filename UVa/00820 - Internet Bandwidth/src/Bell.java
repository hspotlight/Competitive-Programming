import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Bell {
	//00820 - Internet Bandwidth
	int path[][];
	int n,s,t,c;
	BufferedWriter bfw;
	StreamTokenizer stk;
	int readInt() throws IOException{stk.nextToken(); return (int)stk.nval;}
	public static void main(String[]args) throws IOException{new Bell().run();}
	void run() throws IOException{
		System.setIn(new FileInputStream("input.txt"));
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int nCases = 0;
		while((n=readInt())!=0){
			s = readInt();
			t = readInt();
			c = readInt();
			path = new int[n+1][n+1];
			for(int i=0;i<c;i++){
				int u = readInt(), v = readInt();
				path[u][v] = (path[v][u] += readInt());
			}
			bfw.write("Network "+(++nCases)+"\nThe bandwidth is "+findMaxFlow()+".\n\n");
		}
		bfw.close();
	}
	
	int findMaxFlow() throws IOException{
		int maxflow = 0;
		boolean pathVisited[][] = new boolean[n][n];
		
		while(true){
			int u=0,v=0;
			int parent[] = new int[n+1];
			for(int i=0;i<n+1;i++) parent[i] = -1;
			boolean visited[] = new boolean[n+1];
			
			//1. find augmenting path
			Queue<Integer> qe = new LinkedList<Integer>();
			qe.add(s); visited[s] = true;
			while(!qe.isEmpty()){
				u = qe.poll();
//				bfw.write(u+"\n");
				if(u==t)break;
				for(v=0;v<n+1;v++)
					if(!visited[v] && path[u][v]>0){
						parent[v] = u;
						qe.add(v); visited[v] = true;
					}
			}
//			System.out.println("X");
//			if(u!=t) break;//break if no augmenting path
			if(parent[t]==-1) break;
			
			//2. compute bottleneck
			v=t; u=parent[v];
			int bottleneck = Integer.MAX_VALUE;
			while(v!=s){
//				if(bottleneck>path[u][v])
//					bottleneck = path[u][v];
				bottleneck = Math.min(bottleneck, path[u][v]);
				v=u; u=parent[v];
			}
			maxflow += bottleneck;
//			bfw.write("bottleneck: "+bottleneck+"\n");
			
			//3. augment flow & construct residual graph
			v = t; u = parent[v];
			while(v!=s){
				path[u][v] -= bottleneck;
				path[v][u] += bottleneck;
//				if(!pathVisited[v][u]){
//					pathVisited[v][u] = true;
//					path[v][u] = bottleneck;
//				}else{ 
//					path[v][u] += bottleneck;
//				}
				v = u; u = parent[v];
			}			
		}
		return maxflow;
	}
}
