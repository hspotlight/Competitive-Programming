import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	//11462 - Age Sort
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
		int n;
		while((n = readInt())!=0){
			int a[] = new int[n];
			for(int i=0;i<n;i++){
				a[i] = readInt();
			}
			Arrays.sort(a);
			for(int i=0;i<n;i++){
				bfw.write((i==0?"":" ")+a[i]);
			}
			bfw.write("\n");
		}
		bfw.flush();
	}
}