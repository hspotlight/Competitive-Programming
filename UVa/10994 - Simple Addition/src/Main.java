import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10994 - Simple Addition
	int A, B;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		while( (A = readInt())>=0 && (B = readInt())>=0)
			System.out.println(sum(B) - sum(A-1));
	}
	long sum(long n){
		long sum = 0, unit = 1;
		while(n>=unit){
			long div = n/(unit*10);
			long rem = (n%(unit*10))/unit;
			sum += 45*div + ((rem)*(rem+1))/2;
			n -= rem * unit;
			unit = unit * 10;
		}
		return sum;
	}
}
