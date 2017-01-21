import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//10523 - Very Easy !!!
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			double sum = 0;
			BigInteger total = BigInteger.ZERO;
			BigInteger a = BigInteger.valueOf(A);
			for(int i=1;i<=N;i++){
				sum  += i * Math.pow(A, i);
				total = total.add(BigInteger.valueOf(i).multiply(a.pow(i)));
			}
			System.out.println(total);
		}
	}
}
