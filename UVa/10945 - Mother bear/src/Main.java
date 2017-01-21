import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10945 - Mother bear
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		String s;
		while(!(s = bfr.readLine()).equals("DONE")){
			s = s.replaceAll("[,.!? ]", "").toLowerCase();
			String t = new StringBuilder(s).reverse().toString();
			System.out.println(s.equals(t)?"You won't be eaten!":"Uh oh..");
		}
	}
}