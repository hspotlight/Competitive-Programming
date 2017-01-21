import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00155 - All Squares
	StreamTokenizer stk;
	int count = 0;
	int X, Y;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			int k = readInt(); X = readInt(); Y = readInt();
			if(k==0) break;
			count = 0;
			traversal(k,1024,1024);
			System.out.printf("%3d\n",count);
		}
	}
	void traversal(int k, int x, int y){//X topdown, Y leftright
		if( Math.abs(X-x)<=k && Math.abs(Y-y)<=k) count++;
		if(k<1) return;
		if(X<x && Y<y) traversal(k/2, x-k,y-k);
		if(X<x && Y>=y) traversal(k/2, x-k,y+k);
		if(X>x && Y<y) traversal(k/2, x+k,y-k);
		if(X>x && Y>=y) traversal(k/2, x+k,y+k);
	}
}
