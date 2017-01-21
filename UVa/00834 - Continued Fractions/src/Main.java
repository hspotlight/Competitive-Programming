import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	//834 - Continued Fractions
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(true){
			stk.nextToken();
			if(stk.ttype == StreamTokenizer.TT_EOF) break;
			int a = (int)stk.nval, b = readInt();
			if(a==0) System.out.println("[-1;1]");
			else{
				int i=0;
				while(b!=0){
					int div = a/b;
					int rem = a%b;
					String s = i==0? "[" : i==1? ";" : ",";
					System.out.print(s+div);
					a = b;
					b = rem;
					i++;
				}
				System.out.println("]");
			}
		}
	}
}