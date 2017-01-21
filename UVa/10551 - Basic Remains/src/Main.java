import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//10551 - Basic Remains
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			String line = bfr.readLine();
			if(line.equals("0")) break;
			
			StringTokenizer st = new StringTokenizer(line);
			int base = Integer.parseInt(st.nextToken());
			BigInteger p = new BigInteger(st.nextToken(), base);
			BigInteger m = new BigInteger(st.nextToken(), base);
			BigInteger res = p.mod(m);
			System.out.println( Integer.toString(res.intValue(), base));
		}
	}
}
