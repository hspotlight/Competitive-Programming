import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	//12455 - Bars
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			int target = readInt();
			int p = readInt();
			int val[] = new int[p];
			for(int i=0;i<p;i++) val[i] = readInt();
			Arrays.sort(val);
			
			boolean subsetSum[] = new boolean[target+1];
			subsetSum[0] = true;
			//sol1
//			for(int i=0; i<p;i++){//val
//				boolean[] subset = subsetSum.clone();
//				for(int j=val[i];j<=target;j++){
//					if(subsetSum[ j-val[i] ]) subset[j] = true;
//				}
//				subsetSum = subset;
//			}
			//sol2
			for(int i=0; i<p;i++){//val
				for(int j=target-val[i];j>=0;j--){
					if(subsetSum[j] && val[i]+j < target+1) subsetSum[val[i]+j] = true;
				}
			}
			if(subsetSum[target]) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}