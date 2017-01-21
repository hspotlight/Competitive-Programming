import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	//01210 - Sum of Consecutive Prime Numbers
	int N = 10001;
	boolean isComposite[] = new boolean[N];
	ArrayList<Integer> prime = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		for(int i=2;i<N;i+=(i==2?1:2)){
			if(!isComposite[i]){
				prime.add(i);
				for(int j=i+i;j<N;j+=i){
					isComposite[j] = true;
				}
			}
		}
		
		int[] freq = new int[N];
		for(int i=0;i<prime.size();i++){//start
			int sum = 0;
			for(int j=i;j<prime.size();j++){//end
				sum += prime.get(j);
				if(sum < N) freq[sum]++;
			}
		}
		
		while(bfr.ready()){
			int x = Integer.parseInt(bfr.readLine());
			if(x==0) break;
			System.out.println(freq[x]);
		}
	}
}
