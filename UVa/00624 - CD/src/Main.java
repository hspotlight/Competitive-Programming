import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	//00624 - CD
	int n, max;
	int[][] dp;
	int[][] parent;
	StringBuilder sb = new StringBuilder();
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			if(!st.hasMoreTokens()) break;
			max = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int data[] = new int[n];
			for(int i=0;i<n; data[i++] = Integer.parseInt(st.nextToken()) );
			dp = new int[n+1][max+1]; parent = new int[n+1][max+1];
			/************************ process *****************************/
			for(int i=1;i<=n;i++){
				int val = data[i-1];
				for(int j=1;j<=max;j++){
					if(j-val<0) {//just copy
						dp[i][j] = dp[i-1][j];
						parent[i][j] = (i-1)*(max+1) + j;
					}
					else{//fine max
						int left = dp[i][j-1];
						int top  = dp[i-1][j];
						int put  = dp[i-1][j-val] + val;
						if (put > left && put > top){
							dp[i][j] = put;
							parent[i][j] = (i-1)*(max+1) + (j-val);
						}
						else if(left >= top && left >= put) {
							dp[i][j] = left;
							parent[i][j] = i*(max+1) + (j-1);
						}
						else {
							dp[i][j] = top;
							parent[i][j] = (i-1)*(max+1) + j; 
						}
					}
				}
			}
			/************************ output *****************************/
			print(parent[n][max],n*(max+1) + max);
			sb.append("sum:"+dp[n][max]+"\n");
		}
		bfw.write(sb.toString());
		bfw.flush();
	}
	void print(int prev, int cur){
		int pi = prev/(max+1), pj = prev%(max+1);
		int ci = cur/(max+1), cj = cur%(max+1);
		if(ci==0 || cj==0) return;
		if(ci==pi || cj==pj) print(parent[pi][pj], prev);
		else{
			print(parent[pi][pj], prev);
			sb.append(Math.abs(cj-pj)+" ");
		}
	}
}