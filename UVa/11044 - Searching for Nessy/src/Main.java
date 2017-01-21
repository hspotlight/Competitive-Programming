import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11044 - Searching for Nessy
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
			int n = readInt(), m = readInt();
			int count = 0;
			//sol1
//			for(int i=2;i<n;i+=3){
//				for(int j=2;j<m;j+=3){
//					count++;
//				}
//			}
			//sol2
			n--; m--;
			int op[] = {-1,-2,0};
			int r = (( n + op[n%3]) - 2)/3 + 1;
			int c = (( m + op[m%3]) - 2)/3 + 1;
			count =  r*c;
			System.out.println(count);
		}
	}
}