import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//12611 - Beautiful Flag
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		for(int c=1;c<=tc;c++){
			int r = readInt();
			int length = r*5;
			int width  = r*3;
			int half = width/2;
			int left = length*45/100;
			int right = length*55/100;
			System.out.println("Case "+c+":");
			System.out.println(-left+" "+half);
			System.out.println(right+" "+half);
			System.out.println(right+" "+(-half));
			System.out.println(-left+" "+(-half));
		}
	}
}