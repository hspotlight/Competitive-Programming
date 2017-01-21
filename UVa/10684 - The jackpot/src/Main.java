import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10684 - The jackpot
	int N;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while((N = readInt())!=0){
			int[] data = new int[N];
			for(int i=0;i<N;data[i++] = readInt());
			int max = Integer.MIN_VALUE;
			for(int start = 0;start<N;start++){
				int sum = 0;
				for(int end = start; end<N; end++){
					sum += data[end];
					max = Math.max(max, sum);
				}
			}
			if(max<=0) System.out.println("Losing streak.");
			else System.out.println("The maximum winning streak is "+max+".");
		}
	}
}