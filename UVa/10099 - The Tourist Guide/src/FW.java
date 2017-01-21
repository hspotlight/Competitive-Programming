import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class FW {
	//10099 - The Tourist Guide
	int N,R,S,T,P;
	StreamTokenizer stk;
	//FW
	int[][] map;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new FW().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		while(bfr.ready()){
			N = readInt(); R = readInt();
			if(N==0 && R==0) break;
			//initialize
			//FW
			map = new int[N+1][N+1];
			for(int i=0;i<R;i++){
				int f = readInt(), t = readInt(), w = readInt();
				map[f][t] = map[t][f] = w;
			}
			
			//FW
			fw();
			
			S = readInt(); T = readInt(); P = readInt();
			int min = map[S][T];
			int nPersonPerTrips = min - 1;// 1 for guide
			int nTrips = (int)(Math.ceil((P*1.0)/nPersonPerTrips));
			System.out.println("Scenario #"+c);
			System.out.println("Minimum Number of Trips = "+nTrips);
			System.out.println();
			c++;
		}
	}
	void fw(){
		for(int k=1;k<=N;k++){
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(map[i][k]==0 || map[k][j]==0) continue;
					map[i][j] = Math.max(map[i][j], Math.min(map[i][k], map[k][j]));
				}
			}
		}
	}
}
