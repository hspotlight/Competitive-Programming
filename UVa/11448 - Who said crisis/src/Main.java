import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//11448 - Who said crisis
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			String[] token = bfr.readLine().split("\\W+");
			BigInteger A = new BigInteger(token[0]);
			System.out.println(A.subtract(new BigInteger(token[1])));
		}
	}
}
