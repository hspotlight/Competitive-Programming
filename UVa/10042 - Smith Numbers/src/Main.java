import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//10042 - Smith Numbers
	StreamTokenizer stk;
	ArrayList<Integer> prime;
	int max = 100000;
	boolean isComposite[];
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		//preProcess
		preprocess();
		System.out.println(prime);
		int tc = readInt();
		while(tc-->0){
			int n = readInt();
			while(true){
				if(isPrime(++n)) continue;
				if(sumDigits(n) == sumFactor(n)) break;
			}
			System.out.println(n);
		}
	}
	boolean isPrime(int n){
		if(n<max) return !isComposite[n];
		for(int p : prime){
			if(n==p) return true;
			if(n%p==0) return false;
		}
		return true;
	}
	int sumDigits(int n){
		int sum = 0;
		while(n>0){
			sum += n%10;
			n/=10;
		}
		return sum;
	}
	int sumFactor(int n){
		int sum = 0;
		for(int p : prime){
			while(n%p==0){
				sum += sumDigits(p);
				n /= p;
			}
		}
		if(n > 1) sum += sumDigits(n);
		return sum;
	}
	void preprocess(){
		prime = new ArrayList<Integer>();
		isComposite = new boolean[max];
		for(int i=2;i<max; i = (i==2?3:i+2) ){
			if(!isComposite[i]){
				prime.add(i);
				for(int j=i+i;j<max;j+=i) isComposite[j] = true;
			}
		}
	}
}
