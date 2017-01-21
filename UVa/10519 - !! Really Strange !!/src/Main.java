import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//10519 - !! Really Strange !!
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			BigInteger n = new BigInteger(bfr.readLine());
			if(n.equals(BigInteger.ZERO)) System.out.println(1);
			else System.out.println(n.multiply(n.subtract(BigInteger.ONE)).add(BigInteger.valueOf(2)) );
		}
	}
}
