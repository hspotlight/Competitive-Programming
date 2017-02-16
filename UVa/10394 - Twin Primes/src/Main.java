import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	//10394 - Twin Primes
	int N = 20000000;
	boolean isComposite[];
	ArrayList<Integer> twinPrimes = new ArrayList<Integer>();
	ArrayList<Integer> primes = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		new Main().run();
	}
	public void run() throws Exception{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		isComposite = new boolean[N];
		int lastPrime = -1;
		for(int i=2;i<N;i = i + (i==2?1:2)){
			if(!isComposite[i]){
				primes.add(i);
				for(int j=i+i;j<N;j+=i) isComposite[j] = true;
				if(i == lastPrime+2) twinPrimes.add(lastPrime);
				lastPrime = i;	
			}
		}
		while(bfr.ready()){
			int xth = Integer.parseInt(bfr.readLine());
			bfw.write(outputFormat(twinPrimes.get(xth-1))+"\n");
		}
		bfw.flush();
	}
	public String outputFormat(int i){
		return "("+i+", "+(i+2)+")";
	}
}
