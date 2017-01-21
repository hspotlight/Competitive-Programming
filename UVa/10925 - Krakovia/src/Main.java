import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//10925 - Krakovia
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		int N = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		int c = 1;
		while(!(N==0&&F==0)){
			BigInteger sum = BigInteger.ZERO;
			for(int i=0;i<N;i++)
				sum = sum.add(new BigInteger(bfr.readLine()));
			System.out.println("Bill #"+c+" costs "+sum+": each friend should pay "+sum.divide(BigInteger.valueOf(F)));
			System.out.println();
			st = new StringTokenizer(bfr.readLine());
			N = Integer.parseInt(st.nextToken());
			F = Integer.parseInt(st.nextToken());
			c++;
		}
	}
}
