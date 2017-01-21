import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11934 - Magic Formula
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(true){
			int a = readInt(), b = readInt(), c = readInt();
			int d = readInt(), L = readInt();
			if(a==0&&b==0&&c==0&&d==0&&L==0) break;
			int count = 0;
			for(int i=0;i<=L;i++){
				int ans = a*i*i + b*i + c;
				if(ans%d==0) count++;
			}
			System.out.println(count);
		}
	}
}
