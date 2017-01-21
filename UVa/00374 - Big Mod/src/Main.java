import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;

public class Main {
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
		while(bfr.ready()){
			int B = readInt(); int P = readInt(); int M = readInt();
			System.out.println(BigInteger.valueOf(B).modPow(BigInteger.valueOf(P), BigInteger.valueOf(M)));
		}
	}
}
