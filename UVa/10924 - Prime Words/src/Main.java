import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10924 - Prime Words
	int n = 1050;
	boolean isComposite[];
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		isComposite = new boolean[n];
		for(int i=2;i<n;i += (i==2?1:2)){
			if(!isComposite[i]){
				for(int j=i+i;j<n;j+=i)
					isComposite[j] = true;
			}
		}
		while(bfr.ready()){
			char c[] = bfr.readLine().toCharArray();
			int val = parseInt(c);
			System.out.println( isPrime(val)? "It is a prime word." : "It is not a prime word.");
		}
	}
	boolean isPrime(int val){
		return !isComposite[val];
	}
	int parseInt(char[] c){
		int sum = 0;
		for(int i=0;i<c.length;i++){
			sum += val(c[i]);
		}
		return sum;
	}
	int val(char c){
		if(c<='Z') return c - 'A' + 27;
		return c - 'a' + 1;
	}
}
