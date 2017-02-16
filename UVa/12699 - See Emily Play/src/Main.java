import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;

public class Main {
	//12699 - See Emily Play
	int N = 1000001;
	double[] D, S, A;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		
		D = new double[N]; S = new double[N]; A = new double[N];
		D[1] = S[1] = A[1] = 1;
		for(int i=2;i<N;i++){
			if((i&1)==0){//even
				D[i] = (D[i>>1] + 1);
				S[i] = S[i>>1];
				A[i] = A[i>>1];
			}
			else{//odd
				D[i] = 0.5*(D[i-1] + (D[(i+1)>>1] + 1));
				S[i] = 0.5*(S[i-1] + (S[(i+1)>>1] + 1));
				A[i] = 0.5*(A[i-1] + (A[(i+1)>>1] + 1));
			}
		}
		
		int tc = readInt();
		while(tc-->0){
			int N = readInt(), d = readInt(), s = readInt(), a = readInt();
			bfw.write(new DecimalFormat("0.000").format(func(N, d, s, a))+"\n");
		}
		bfw.flush();
	}
	double func(int N, int d, int s, int a){
		return D[N]*d + S[N]*s + A[N]*a;
	}
}
