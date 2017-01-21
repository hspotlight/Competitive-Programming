import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	//10773 - Back to Intermediate Math
	StreamTokenizer stk;
	double readDouble() throws Exception { stk.nextToken(); return stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = (int) readDouble();
		for(int i=1;i<=tc;i++){
			double d = readDouble(), v = readDouble(), u = readDouble();
			System.out.print("Case "+i+": ");
			if(v==0 || u==0 || v >= u) System.out.println("can't determine");
			else{
				double uCos = v;
				double uSin = Math.sqrt(u*u - uCos*uCos);
				double shrt = d/uSin;
				double fast = d/u;
				System.out.println(String.format("%.3f", Math.abs(fast-shrt)));
			}
		}
	}
}