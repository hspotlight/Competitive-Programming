import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//1585 - Score
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine().trim());
		while(tc-->0){
			char[] s = bfr.readLine().trim().toCharArray();
			int sum = 0, val = 0;
			for(int i=0;i<s.length;i++){
				if(s[i]=='X') val = 0;
				if(s[i]=='O') val++;
				sum += val;
			}
			System.out.println(sum);
		}
	}
}