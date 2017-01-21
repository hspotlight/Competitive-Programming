import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//10190 - Divide, But Not Quite Conquer!
	StreamTokenizer stk;
	BigInteger N, M;
	ArrayList<BigInteger> list;
	//M-base - base 0 and 1
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			N = new BigInteger(st.nextToken());
			M = new BigInteger(st.nextToken());
			list = new ArrayList<BigInteger>();
			if(M.compareTo(BigInteger.valueOf(2))<0 || N.compareTo(M)<0) System.out.println("Boring!");
			else{
				boolean boring = !traversal(N);
				if(boring) System.out.println("Boring!");
				else {
					String s = "";
					for(BigInteger each : list){
						if(s.equals("")) s += each;
						else s += " "+each;
					}
					System.out.println(s);
				}
			}
		}
	}
	boolean traversal(BigInteger N){
		while(N.compareTo(BigInteger.ONE)>0){
			BigInteger[] DR = N.divideAndRemainder(M);
			list.add(N);	
			if(DR[1].compareTo(BigInteger.ZERO)>0) return false;
			N = DR[0];
		}
		list.add(N);
		return true;
	}
}
