import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00350 - Pseudo-Random Numbers
	int Z, I, M, L;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		while(bfr.ready()){
			Z = readInt(); I = readInt(); M = readInt(); L = readInt();
			if(Z==0 && I==0 && M==0 && L==0) break;
			System.out.println("Case "+(c++)+": "+work(Z,I,M,L));
		}
	}
	int work(int Z,int I,int M,int L){
		boolean visited[] = new boolean[M];
		int mark[] = new int[M];
		int count = 0;
		L %= M;
		while(true){
			if(visited[L]) break;
			visited[L] = true;
			mark[L] = count++;
			L = (Z*L + I) % M;
		}
		return count - mark[L];
	}
}
