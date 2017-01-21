import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//12527 - Different Digits
	StreamTokenizer stk;
	int[] count;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		preprocess();
		while(bfr.ready()){
			int n = readInt(), m = readInt();
			System.out.println(count[m]-count[n-1]);
		}
	}
	void preprocess(){
		int N = 5001;
		count = new int[N];
		count[1] = 1;
		for(int i=2;i<N;i++)
			count[i] = count[i-1] + check(i);
	}
	int check(int n){
		int digits[] = new int[10];
		while(n>0){
			int d = n%10;
			digits[d]++;
			if(digits[d]==2) return 0;
			n/=10;
		}
		return 1;
	}
}
