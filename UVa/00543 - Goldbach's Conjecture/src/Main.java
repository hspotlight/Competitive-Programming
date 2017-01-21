import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//00543 - Goldbach's Conjecture
	int N;
	int n = 1000001;
	StreamTokenizer stk;
	boolean isComposite[] = new boolean [n];
	ArrayList<Integer> prime = new ArrayList<Integer>();
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		for(int i=2;i<n;i += (i==2?1:2)){
			if(!isComposite[i]){
				prime.add(i);
				for(int j=i+i;j<n;j+=i) isComposite[j] = true;
			}
		}
		while((N=readInt()) != 0){
			int p = getP();
			if(p == -1) System.out.println("Goldbach's conjecture is wrong.");
			else System.out.println(N+" = "+p+" + "+(N-p));
		}
	}
	int getP(){
		for(int p : prime){
			if(p>=n) break;
			if(!isComposite[N-p]) return p;
		}
		return -1;
	}
}