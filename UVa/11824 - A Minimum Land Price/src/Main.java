import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//11824 - A Minimum Land Price
	StreamTokenizer stk;
	ArrayList<BigInteger> list = new ArrayList<BigInteger>();
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			list.clear();
			int n;
			BigInteger sum = BigInteger.ZERO;
			while((n=readInt())!=0) list.add(BigInteger.valueOf(n));
			Collections.sort(list);
			Collections.reverse(list);
			
			for(int i=0;i<list.size();i++)
				sum = sum.add( BigInteger.valueOf(2).multiply(list.get(i).pow(i+1)) );
			System.out.println(sum.compareTo(BigInteger.valueOf(5000000))>0?"Too expensive":""+sum);
		}
	}
}