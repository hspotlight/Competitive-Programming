import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	//10474 - Where is the Marble
	StreamTokenizer stk;
	int readInt() throws IOException{ stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		while(bfr.ready()){
			int N = readInt();
			int Q = readInt();
			if( N==0 && Q==0 ) break;
			int[] A = new int[N];
			for(int i=0;i<N;i++) A[i] = readInt();
			Arrays.sort(A);
			System.out.println("CASE# "+(c++)+":");
			for(int i=0;i<Q;i++){
				int x = readInt();
				int index = indexOf(x,A);
				if(index==-1)System.out.println(x+" not found");
				else System.out.println(x+" found at "+index);
			}
		}
	}
	int indexOf(int x, int[] A){
		for(int i=0;i<A.length;i++){
			if(A[i]==x) return i+1;
		}
		return -1;
	}
}
