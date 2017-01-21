import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00872 - Ordering
	int N;
	int[][] map;
	int[] inDegree;
	boolean possible;
	boolean[] variable;
	boolean[] visited;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			//line 1: variable
			N = 0;
			map = new int[26][26];
			inDegree = new int[26];
			variable = new boolean[26];
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			while(st.hasMoreTokens()){
				variable[st.nextToken().charAt(0)-'A'] = true;
				N++;
			}
			//line 2: X<Y
			st = new StringTokenizer(bfr.readLine());
			while(st.hasMoreTokens()){
				char[] s = st.nextToken().toCharArray();
				int u = s[0]-'A', v = s[2]-'A';
				map[u][v] = 1;
				inDegree[v]++;
			}
			/*******************************************************/
			possible = false;
			for(int u=0;u<26;u++){
				if(variable[u] && inDegree[u]==0){
					visited = new boolean[26];
					visited[u] = true;
					DFS(u,1,""+((char)(u+'A')));
				}
			}
			if(!possible) System.out.println("NO");
			if(tc>0) System.out.println();
		}
	}
	void DFS(int u, int count, String path){
		if(count==N){ possible = true; System.out.println(path); }
		//remove task
		for(int v=0;v<26;v++) if(map[u][v]==1) inDegree[v]--;
		
		for(int v=0;v<26;v++){
			if(!visited[v]&& variable[v] && inDegree[v]==0){
				visited[v] = true;
				DFS(v, count+1, path+" "+((char)(v+'A')));
				visited[v] = false;
			}
		}
		//recover task
		for(int v=0;v<26;v++) if(map[u][v]==1) inDegree[v]++;
	}
}