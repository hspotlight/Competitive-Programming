import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//00355 - The Bases Are Loaded
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int baseFrom  = Integer.parseInt(st.nextToken());
			int baseTo    = Integer.parseInt(st.nextToken());
			String orgVal = st.nextToken();
			try{
				BigInteger b = new BigInteger(orgVal, baseFrom);
				StringBuilder cnvt = new StringBuilder();
				if(!b.equals(BigInteger.ZERO)){
					while(b.compareTo(BigInteger.ZERO)==1){
						BigInteger[] dr = b.divideAndRemainder(BigInteger.valueOf(baseTo));
						cnvt.insert(0, itoa(dr[1].intValue()));
						b = dr[0];
					}
				}
				else cnvt.append("0");
				
				System.out.println(orgVal+" base "+baseFrom+" = "+cnvt+" base "+baseTo);
			}
			catch(Exception e){
				System.out.println(orgVal+" is an illegal base "+baseFrom+" number");
			}
		}
	}
	char itoa(int val){
		if(val <= 9) return (char)(val+'0');
		return (char)(val - 10 + 'A');
	}
}
