import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//10669 - Three powers
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BigInteger[]S = new BigInteger[64];
		S[0] = BigInteger.ONE;
		for(int i=1;i<S.length;i++) S[i] = S[i-1].multiply(BigInteger.valueOf(3));
		long in = Long.parseLong(bfr.readLine());
		while(in!=0){
			if(in==1) System.out.println("{ }");
			else {
				in--;
				StringBuilder sb = new StringBuilder("{");
				boolean first = true;
				for(int i=0;in!=0;i++, in = in>>1){
					if((in&1)==1){
						sb.append((!first? "," : "") + " "+S[i]);
						first = false;
					}
				}
				sb.append(" }");
				System.out.println(sb);
			}
			in = Long.parseLong(bfr.readLine());
		}
	}
}
