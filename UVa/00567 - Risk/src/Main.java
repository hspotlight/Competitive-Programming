import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	//00567 - Risk
	final int N = 21;
	int[][] map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter (new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(bfr.ready()){
			map = new int[N][N];
			for(int i=1;i<N;i++){
				for(int j=1;j<N;j++)
					map[i][j] = i==j?0:Integer.MAX_VALUE;
			}
			
			for(int u=1;u<20;u++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				st.nextToken();
				while(st.hasMoreTokens()){
					int v = Integer.parseInt(st.nextToken());
					map[u][v] = map[v][u] = 1;
				}
			}
			/**********************************************/
			for(int k=1;k<=20;k++){
				for(int i=1;i<=20;i++){
					for(int j=1;j<=20;j++)
						if(map[i][k]!=Integer.MAX_VALUE && map[k][j]!=Integer.MAX_VALUE)
							map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
			
			int n = Integer.parseInt(bfr.readLine());
			sb.append("Test Set #"+(tc++)+"\n");
			for(int i=0;i<n;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
				sb.append(String.format("%2d to %2d: %d\n",u,v,map[u][v]));
			}
			sb.append("\n");
		}
		bfw.write(""+sb);
		bfw.flush();
	}
}
