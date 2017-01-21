import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//00568 - Just the Facts
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			int N = readInt();
			int n=N, five=0, two=0, prod=1;
			while (n>0) {
				int m = n--;
				while (m%5==0) { m/=5; five++; }
				while (m%2==0) { m/=2; two++; }
				prod = (prod*(m%10))%10;
			}
			//last digits[ 1 3 7 9 ] then multiply by remaining factor
			if (five>two) while (five-->two) prod=(prod*5)%10;
			while (two-->five) prod=(prod*2)%10;
			bfw.write(String.format("%5d",N) + " -> " + prod + "\n");
		}
		bfw.close();
	}
}
