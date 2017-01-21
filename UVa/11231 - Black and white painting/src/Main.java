import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11231 - Black and white painting
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
			int n = readInt(), m = readInt(), c = readInt();
			if(n==0 && m==0 && c==0) break;
			int sum = 0;
			if(c==1){
				for(int i=n;i>=8;i-=2){
					for(int j=m;j>=8;j-=2){
						sum++;
					}
				}
				for(int i=n-1;i>=8;i-=2){
					for(int j=m-1;j>=8;j-=2){
						sum++;
					}
				}
			}
			else{
				for(int i=n-1;i>=8;i-=2){
					for(int j=m;j>=8;j-=2){
						sum++;
					}
				}
				for(int i=n;i>=8;i-=2){
					for(int j=m-1;j>=8;j-=2){
						sum++;
					}
				}
			}
			System.out.println(sum);
		}
	}
}