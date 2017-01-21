import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//10110 - Light, more light
	StreamTokenizer stk;
	long readLong() throws Exception { stk.nextToken(); return (long) stk.nval; }
	public static void main(String [] args) throws Exception{ new Main().run(); }
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		long N;
		while((N=readLong())!=0)
			bfw.write(((long)Math.pow((long)Math.sqrt(N), 2) == N )?"yes\n":"no\n");
		bfw.flush();
		bfw.close();
	}
}