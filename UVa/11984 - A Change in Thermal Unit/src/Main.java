import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;

public class Main {
	//11984 - A Change in Thermal Unit
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		int tc = readInt();
		while(tc-->0){
			double C = readInt(), d = readInt();
			double F = 9*C/5 + d;
			double ans = (F)*5/9;
			System.out.println("Case "+c+": "+new DecimalFormat("0.00").format(ans));
			c++;
		}
	}
}
