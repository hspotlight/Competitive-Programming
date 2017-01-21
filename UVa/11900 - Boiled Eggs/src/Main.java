import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11900 - Boiled Eggs
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
			int n = readInt(), p = readInt(), q = readInt();
			int val[] = new int[n];
			for(int i=0;i<n;i++) val[i] = readInt();
			int count = 0;
			for(int i=0;i<n;i++){
				if(i>=p) break;
				if(q-val[i]>=0){//can put egg with weight = val[i]
					q -= val[i];
					count++; 
				}
			}
			System.out.println("Case "+c+": "+count);
		}
	}
}