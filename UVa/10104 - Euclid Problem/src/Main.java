import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//10104 - Euclid Problem
	ArrayList<Integer> list = new ArrayList<Integer>();
	StreamTokenizer stk;
	int T;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			System.out.println(calculate(A,B));
		}
	}
	String calculate(int A, int B){
		list.clear();
		if(A==0&&B==0) return "0 0 0";
		else if (A==0) return "0 1 " + B;
		else if (B==0) return "1 0 " + A;
		
		boolean swap = false;
		if(A<B){
			T = A;
			A = B;
			B = T;
			swap = true;
		}
		int D = GCD(A,B);
		long X = 0, Y = 1;
		for(int i=list.size()-1 ; i>=0 ; i--){
			long tempX = Y, tempY = X - Y*list.get(i);
			
			X = tempX; Y = tempY;	
		}
		if(swap) return Y+" "+X+" "+D;
		else     return X+" "+Y+" "+D;
	}
	int GCD(int A, int B){
		if(A%B==0) return B;
		list.add(A/B);
		return GCD(B,A%B);
	}
}