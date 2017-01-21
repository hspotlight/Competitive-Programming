import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//10699 - Count the factors
	int N;
	ArrayList<Integer> prime = new ArrayList<Integer>();
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int n = 1000001;
		boolean isComposite[] = new boolean[n];
		for(int i=2;i<n;i+= (i==2?1:2)){
			if(!isComposite[i]){
				prime.add(i);
				for(int j=i+i;j<n;j+=i) isComposite[j] = true;
			}
		}
		while((N = readInt())!=0){
			System.out.println(N+" : "+ans(N));
		}
	}
	int ans(int n){
		ArrayList<Integer> primeFactor = new ArrayList<Integer>();
		for(int p : prime){
			if(n%p==0){
				primeFactor.add(p);
				while(n%p==0) n /= p;
			}
			if(n==1) break;
		}
		return primeFactor.size();
	}
}