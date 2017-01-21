import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11045 - My T-shirt suits me
	int n, m, N,S,T;
	int map[][], parent[];// [6+m+2]
	boolean visited[], found;
	String[] size = {"XS","S","M","L","XL","XXL"};
	//shirt -> volunteer
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			st = new StringTokenizer(bfr.readLine());
			n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
			N = 6+m+2; S = N-2; T = N-1;
			int nShirts = n/6;
			
			map = new int[N][N];
			for(int i=0;i<6;i++) map[S][i] = nShirts; 
			for(int i=0;i<m;i++){//person m
				st = new StringTokenizer(bfr.readLine());
				map[i+6][T] = 1;
				while(st.hasMoreTokens()){
					int index = indexOf(st.nextToken());//size j
					map[index][i+6] = 1;
				}
			}
			//
			int maxFlow = 0;
			while(true){
				visited = new boolean[N];
				parent = new int[N];
				found = false;
				DFS(S);
				if(!found) break;
				int v = T, u = parent[v];
				maxFlow++;
				while(v!=S){
					map[u][v] -= 1; map[v][u] += 1;
					v = u; u = parent[v];
				}
			}
			System.out.println((maxFlow==m)?"YES":"NO");
		}
	}
	void DFS(int u){
		if(u==T) found = true;
		if(found) return;
		visited[u] = true;
		for(int v=0;v<N;v++){
			if(!visited[v] && map[u][v]>0){
				parent[v] = u; DFS(v);
			}
		}
	}
	int indexOf(String s){
		for(int i=0;i<size.length;i++) if(s.equals(size[i])) return i;
		return -1;
	}
}