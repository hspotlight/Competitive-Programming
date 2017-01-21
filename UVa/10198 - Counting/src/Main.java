import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//10198 - Counting
	BigInteger A[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		A = new BigInteger[1001];
		A[0] = BigInteger.valueOf(1);
		A[1] = BigInteger.valueOf(2);
		A[2] = BigInteger.valueOf(5);
		A[3] = BigInteger.valueOf(13);
		for(int i=4;i<1001;i++)
			A[i] = A[i-1].add(A[i-1]).add(A[i-2]).add(A[i-3]);
		while(bfr.ready()){
			System.out.println(A[Integer.parseInt(bfr.readLine())]);
		}
	}
}
