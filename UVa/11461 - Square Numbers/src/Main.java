import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11461 - Square Numbers
	int N, M;
	long count[] = new long[100001];
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int root = 1;
		int square = 1;
		for(int i=1;i<100001;i++){
			count[i] = count[i-1];
			if(square<=i){
				count[i]++;
				root++;
				square = root * root;
			}
		}
		while((N=readInt())!=0 && (M=readInt())!=0){
			System.out.println(count[M]-count[N-1]);
		}
	}
}