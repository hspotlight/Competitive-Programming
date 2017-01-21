import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;

public class Main {
	//00623 - 500!
	BigInteger[] val;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		val = new BigInteger[1111];
		val[0] = val[1] = BigInteger.ONE;
		while(bfr.ready()){
			int line = Integer.parseInt(bfr.readLine().trim());
			System.out.println(line+"!\n"+fac(line));
		}
	}
	BigInteger fac(int n){
		if(val[n]!=null) return val[n];
		return val[n] = BigInteger.valueOf(n).multiply(fac(n-1));
	}
}