import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//01583 - Digit Generator
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine().trim());
		while(tc-->0){
			String s = bfr.readLine().trim();
			System.out.println(find(Integer.parseInt(s), s.length()));
		}
	}
	int find(int n, int len){
		int start = n - 9*len;
		if(start<0) start = 0;
		while(start<n){
			if(start+sumDigit(start)==n) return start;
			start++;
		}
		return 0;
	}
	int sumDigit(int n){
		int sum = 0;
		while(n>0){
			sum += n%10;
			n/=10;
		}
		return sum;
	}
}