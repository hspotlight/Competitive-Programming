import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//10168 - Summation of Four Primes
	StreamTokenizer stk;
	ArrayList<Integer> primes;
	int N = 1000000;
	boolean isComposite[];
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		primes = new ArrayList<Integer>();
		isComposite = new boolean[N+1];
		for(int i=2;i<N+1; i+= (i==2?1:2)){
			if(!isComposite[i]){
				primes.add(i);
				for(int j=i+i;j<N+1;j+=i){
					isComposite[j] = true;
				}
			}
		}
		while(bfr.ready()){
			int in = Integer.parseInt(bfr.readLine().trim());
			if(in < 8) System.out.println("Impossible.");
			else if(in == 8) System.out.println("2 2 2 2");
			else if (in%2==0){
				int third = in - 4;
				System.out.print("2 2 ");
				findPrime(third);
				System.out.println();
			}
			else{
				int third = in - 5;
				System.out.print("2 3 ");
				findPrime(third);
				System.out.println();
			}
		}
	}
	void findPrime(int third){
		for(int i=0;i<primes.size();i++){
			if(primes.get(i)>third) return;
			int fourth = third - primes.get(i);
			if(isPrime(fourth)){
				System.out.print(primes.get(i)+" "+fourth);
				return;
			}
		}
	}
	boolean isPrime(int p){
		if(p < N) return !isComposite[p];
		for(int prime : primes){
			if(p%prime==0) return false;
		}
		return true;
	}
}