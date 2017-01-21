import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//10814 - Simplifying Fractions
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			BigInteger A = new BigInteger(st.nextToken());
			st.nextToken();
			BigInteger B = new BigInteger(st.nextToken());
			//find GCD
			BigInteger gcd;
			if(A.compareTo(B)==1){
				gcd = findGCD(A, B);
			}
			else gcd = findGCD( B, A);
			if(!gcd.equals(BigInteger.ONE)) A = A.divide(gcd);
			if(!gcd.equals(BigInteger.ONE)) B = B.divide(gcd);
			System.out.println(A+" / "+B);
		}
	}
	BigInteger findGCD(BigInteger A, BigInteger B){
		BigInteger R = A.remainder(B);
		if(R.equals(BigInteger.ZERO)) return B;
		else return findGCD(B, R);
	}
}
