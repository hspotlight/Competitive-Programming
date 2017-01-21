import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//459 - Graph Connectivity
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine());
		String s = bfr.readLine();//blankline
		while(tc-->0){
			int n = bfr.readLine().charAt(0) - 'A' + 1;//largest node
			int map[][] = new int[n][n];
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++) map[i][j] = i==j?0:100;
			}
			while((s = bfr.readLine()) != null){
				if(s.isEmpty()) break;
				int u = s.charAt(0) - 'A', v = s.charAt(1) - 'A';
				map[u][v] = map[v][u] = 1;
			}
			for(int k=0;k<n;k++){
				for(int i=0;i<n;i++){
					for(int j=0;j<n;j++){
						map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					}
				}
			}
			boolean visited[] = new boolean[26];
			int CC = 0;
			for(int i=0;i<n;i++){
				if(!visited[i]){
					visited[i] = true;
					CC++;
					for(int j=0;j<n;j++){ 
						if(map[i][j] != 100) {
							visited[j] = true;
						}
					}
				}
			}
			System.out.println(CC);
			if(tc>0) System.out.println();
		}
	}
}