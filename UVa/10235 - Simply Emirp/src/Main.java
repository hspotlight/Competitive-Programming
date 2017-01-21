import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	//10235 - Simply Emirp
	int n = 1000;
	boolean isComposite[];
	ArrayList<Integer> prime = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		initial();
		while(bfr.ready()){
			int val = Integer.parseInt(bfr.readLine());
			if( !isPrime(val) ) System.out.println(val+" is not prime.");
			else{
				//val = prime
				int rev = Integer.parseInt(new StringBuilder(""+val).reverse().toString());
				if( val == rev || !isPrime(rev) ) System.out.println(val+" is prime.");
				else System.out.println(val+" is emirp.");
			}
		}
	}
	boolean isPrime(int val){
		if(val < n) return !isComposite[val];
		else{
			for(int p : prime){
				if(val%p == 0) return false;
			}
			return true;
		}
	}
	void initial(){
		isComposite = new boolean[n];
		for(int i=2;i<n;i += (i==2?1:2)){
			if(!isComposite[i]){
				prime.add(i);
				for(int j=i+i;j<n;j+=i)
					isComposite[j] = true;
			}
		}
	}
}
