import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//10494 - If We Were a Child Again
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			BigInteger dividend = new BigInteger(st.nextToken());
			char op = st.nextToken().charAt(0);
			BigInteger divisor = new BigInteger(st.nextToken());
			if(op=='/') System.out.println(dividend.divide(divisor));
			else System.out.println(dividend.mod(divisor));
		}
	}
}
