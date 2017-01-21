import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	//10617 - Again Palindrome
	long dp[][];
	char[] seq;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int nCases = Integer.parseInt(bfr.readLine().trim());
		for(int n=0;n<nCases;n++){
			seq = bfr.readLine().trim().toCharArray();
			dp = new long[seq.length][seq.length];
			for(int i=0;i<seq.length; Arrays.fill(dp[i++], -1));
			bfw.write(palindrome(0,seq.length-1) - 1 + "\n");
		}
		bfw.close();
	}
	long palindrome(int start, int end){
		if(start>end) return 1;
		if(dp[start][end] != -1) return dp[start][end];
		long c = (seq[start] == seq[end])? 0: palindrome(start+1,end-1);
		dp[start][end] = palindrome(start+1,end) + palindrome(start,end-1) - c;
		return dp[start][end];
	}
}