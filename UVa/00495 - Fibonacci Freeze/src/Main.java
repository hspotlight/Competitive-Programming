import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	//00495 - Fibonacci Freeze
	BigInteger[] f = new BigInteger[5001];
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		f[0] = BigInteger.ZERO;
		f[1] = BigInteger.ONE;
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			BigInteger ans = fib(n);
			bfw.write("The Fibonacci number for "+n+" is "+ans+"\n");
		}
		bfw.flush();
	}
	BigInteger fib(int n){
		if(f[n]!=null) return f[n];
		return f[n] = fib(n-1).add( fib(n-2) );
	}
}
