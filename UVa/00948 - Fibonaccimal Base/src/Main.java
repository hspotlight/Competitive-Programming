import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00948 - Fibonaccimal Base
	StreamTokenizer stk;
	int[] fib = { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141};
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
			int n = readInt();
			String ans = "";
			int tmp = n;
			int upper = 0;
			while(tmp>fib[upper]) upper++;
			if(tmp < fib[upper]) upper--;
			while(upper>=0){
				if(tmp>=fib[upper]) {
					tmp-= fib[upper];
					ans += "1";
				}
				else ans += "0";
				upper--;
			}
			System.out.println(n+" = "+ans+" (fib)");
		}
	}
}
