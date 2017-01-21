import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	//00562 - Dividing coins
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			//input
			int n = readInt();
			int[] val = new int[n+1];
			int total = 0;
			for(int i=1;i<=n;i++) total += (val[i] = readInt());
			Arrays.sort(val);
			//process
			boolean[] subsetSum = new boolean[total+1];
			subsetSum[0] = true;
			for(int i=0;i<n;i++){
				for(int j=total-val[i];j>=0;j--)
					if(subsetSum[j]) subsetSum[val[i]+j] = true;
			}
			//
			int minDiff = Integer.MAX_VALUE;
			for(int i=0;i<total+1;i++){
				if(subsetSum[i]) {
					minDiff = Math.min(minDiff, Math.abs(i-(total-i)));
				}
			}
			System.out.println(minDiff);
		}
	}
}
