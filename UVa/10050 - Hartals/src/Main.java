import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10050 - Hartals
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
			boolean day[] = new boolean[n+1];
			int p = readInt();
			for(int i=0;i<p;i++){
				int d = readInt();
				for(int j=d;j<n+1;j+=d) day[j] = true;
			}
			int count = 0;
			for(int i=1;i<n+1;i++){
				if(day[i]){
					if((i+1)%7<2) continue;
					count++;
				}
			}
			System.out.println(count);
		}
	}
}