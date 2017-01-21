import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//00259 - Software Allocation
	int S = 36, T = 37, n = 38;//[0-9] + [A-Z] + (S,T)
	int parent[], matrix[][];
	char match[];
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			String s;
			matrix = new int[n][n];
			while( (s = bfr.readLine()) != null){
				if(s.isEmpty()) break;
				int app = s.charAt(0) - 'A' + 10;
				int nRequires = ((int)s.charAt(1)-'0');
				char[] computers = s.substring(3, s.length()-1).toCharArray();
				matrix[S][app] = nRequires;
				for(char c : computers){
					int cNum = c - '0';
					matrix[app][cNum] = 1;
					matrix[cNum][T] = 1;
				}
			}
			
			/****************************************************************/
			match = new char[10];
			for(int i=0;i<10;i++) match[i] = '_';
			while(true){
				//BFS
				parent = new int[n];
				for(int i=0;i<n;i++) parent[i] = -1;
				BFS();
				if(parent[T]==-1) break;//no augment path
				//compute bottleneck
				int v = T, u = parent[v];
				int min = 1<<10;
				while(true){//u->v
					min = Math.min(min, matrix[u][v]);
					if(u==S) break;
					v = u; u = parent[v];
				}
				//augment flow, construct residual graph
				v = T; u = parent[v];
				while(true){//u->v
					if(10<= u && u <= 35 && 0<= v && v<=9) match[v] = (char)(u-10+'A');
					matrix[u][v] -= min; matrix[v][u] += min;
					if(u==S) break;
					v = u; u = parent[v];
				}
			}
			if(!isOK()) System.out.println("!");
			else System.out.println(new String(match));
		}
	}
	boolean isOK(){
		for(int i=0;i<n;i++)
			if(matrix[S][i]>0) return false;
		return true;
	}
	void BFS(){
		Queue<Integer> qe = new LinkedList<Integer>();
		boolean visited[] = new boolean[n];
		qe.add(S); visited[S] = true;
		while(!qe.isEmpty()){
			int u = qe.poll();
			if(u == T) break;
			for(int v=0;v<n;v++){//[0-9] + [A-Z] + (S,T)
				if(!visited[v] && matrix[u][v]>0){//have path u->v
					visited[v] = true; parent[v] = u;
					qe.add(v);
				}
			}
		}
	}
}