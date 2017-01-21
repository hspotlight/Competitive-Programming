import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;

public class Main {
	//00324 - Factorial Frequencies
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int n;
		while((n = readInt()) != 0){
			System.out.println(n+"! --");
			String s = ""+fac(BigInteger.valueOf(n));
			countDigit(s.toCharArray());
		}
	}
	BigInteger fac(BigInteger val){
		if(val.equals(BigInteger.ZERO)) return BigInteger.ONE;
		return val.multiply(fac(val.subtract(BigInteger.ONE)));
	}
	void countDigit(char[] s){
		int count[] = new int[10];
		for(int i=0;i<s.length;i++){
			count[ s[i] - '0']++;
		}
		System.out.printf("   (0)%5d    (1)%5d    (2)%5d    (3)%5d    (4)%5d\n",count[0],count[1],count[2],count[3],count[4]);
		System.out.printf("   (5)%5d    (6)%5d    (7)%5d    (8)%5d    (9)%5d\n",count[5],count[6],count[7],count[8],count[9]);
	}
}
