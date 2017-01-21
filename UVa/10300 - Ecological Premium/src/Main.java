import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	//10300 - Ecological Premium
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
			int n = readInt();
			int sum = 0;
			for(int i=0;i<n;i++){
				int a = readInt(), b = readInt(), c = readInt();
				int ans = a	* c;
				sum += ans;
			}
			System.out.println(sum);
		}
	}
}