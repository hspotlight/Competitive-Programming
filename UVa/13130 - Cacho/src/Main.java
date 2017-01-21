import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//13130 - Cacho
	static StreamTokenizer stk;
	static int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			int n = 5;
			int A[] = new int[n];
			for(int i=0;i<n;i++) A[i] = readInt();
			System.out.println( isEscala(A) ? "Y" : "N" );
		}
	}
	static boolean isEscala(int A[]){
		for(int i=0;i<4;i++){
			if(A[i] + 1 != A[i+1]) return false;
		}
		return true;
	}
}
