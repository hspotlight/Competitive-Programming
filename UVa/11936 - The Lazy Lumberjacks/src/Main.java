import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11936 - The Lazy Lumberjacks
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
		while(tc-->0){
			int a = readInt(), b = readInt(), c = readInt();
			if(a+b>c && a+c>b && b+c>a) System.out.println("OK");
			else System.out.println("Wrong!!");
		}
	}
}