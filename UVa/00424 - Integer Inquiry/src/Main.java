import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;

public class Main {
	//00424 - Integer Inquiry
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		BigInteger total = BigInteger.ZERO;
		while(!(s=bfr.readLine()).equals("0")){
			total = total.add(new BigInteger(s));
		}
		System.out.println(total);
	}
}