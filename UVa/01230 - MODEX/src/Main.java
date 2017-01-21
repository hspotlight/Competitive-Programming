import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//01230 - MODEX
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			BigInteger x = new BigInteger(st.nextToken());
			BigInteger y = new BigInteger(st.nextToken());
			BigInteger z = new BigInteger(st.nextToken());
			System.out.println(x.modPow(y, z));
		}
		if(bfr.ready()) bfr.readLine();
	}
}
