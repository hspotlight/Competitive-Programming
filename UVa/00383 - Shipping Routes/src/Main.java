import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	//00383 - Shipping Routes
	int M,N,P;
	int path[][];
	BufferedReader bfr;
	StringTokenizer st;
	HashMap<String,Integer> map;
	int readInt() throws Exception{ return Integer.parseInt(st.nextToken()); }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		bfr = new BufferedReader(new InputStreamReader(System.in));
		int nCases = Integer.parseInt(bfr.readLine());
		System.out.println("SHIPPING ROUTES OUTPUT");
		for(int tc=1;tc<=nCases;tc++){
			st = new StringTokenizer(bfr.readLine());
			M = readInt(); N = readInt(); P = readInt();
			
			map = new HashMap<String,Integer>();
			st = new StringTokenizer(bfr.readLine());
			for(int i=0;i<M;i++) map.put(st.nextToken(), i);
			
			path = new int[M][M];
			for(int i=0;i<M;i++)
				for(int j=0;j<M;j++)
					path[i][j] = i==j?0:Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++){
				st = new StringTokenizer(bfr.readLine());
				int u = map.get(st.nextToken()), v = map.get(st.nextToken());
				path[u][v] = path[v][u] = 1;
			}
			
			System.out.println("\nDATA SET  "+tc+"\n");
			for(int k=0;k<M;k++){
				for(int i=0;i<M;i++){
					for(int j=0;j<M;j++){
						if(path[i][k]==Integer.MAX_VALUE || path[k][j]==Integer.MAX_VALUE) continue;
						path[i][j] = Math.min(path[i][j], path[i][k]+path[k][j]);
					}
				}
			}
			for(int i=0;i<P;i++){
				st = new StringTokenizer(bfr.readLine());
				int w = readInt(), u = map.get(st.nextToken()), v = map.get(st.nextToken());
				if(path[u][v]==Integer.MAX_VALUE) System.out.println("NO SHIPMENT POSSIBLE");
				else System.out.println("$"+(w*path[u][v]*100));
			}
		}
		System.out.println("\nEND OF OUTPUT");
	}
}
