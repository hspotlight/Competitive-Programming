import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//10106 - Product
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			System.out.println(new BigInteger(bfr.readLine()).multiply(new BigInteger(bfr.readLine())));
		}
	}
}
