import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//01226 - Numerical surprises
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			BigInteger n = new BigInteger(bfr.readLine());
			BigInteger p = new BigInteger(bfr.readLine());
			System.out.println(p.remainder(n));
		}
	}
}
