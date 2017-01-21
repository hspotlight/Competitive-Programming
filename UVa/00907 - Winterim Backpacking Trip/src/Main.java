import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//00907 - Winterim Backpacking Trip
	int N, K;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());//K night to sleep at camp
			int campsites[] = new int[N+2];
			for(int i=1;i<=N+1;i++)
				campsites[i] = (i==0?0:campsites[i-1])+ Integer.parseInt(bfr.readLine());
			int n = K+1;
			int m = N+2;
			int[][] dp = new int[n][m];
			for(int i=0;i<n;i++){
				for(int j=i+1;j<m;j++){
					if(i==0) dp[i][j] = campsites[j];
					else{
						int min = Integer.MAX_VALUE;
						for(int k=j-1;k>=i;k--)
							min = Math.min(min, Math.max(dp[i-1][k], campsites[j] - campsites[k]));
						dp[i][j] =  min;
					}
				}
			}
			System.out.println(dp[n-1][m-1]);
		}
	}
}

