import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11547 - Automatic Answer
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return (int)stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			int x = readInt();
			String s = ""+ ((( (x*63) + 7492)*5) - 498);
			System.out.println(s.charAt(s.length()-2));
		}
	}
}
