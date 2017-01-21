import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00406 - Prime Cuts
	boolean component[] = new boolean[1001];
	int prime[] = new int[169];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		isComponent();
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int N = Integer.parseInt(st.nextToken());
			int C   = Integer.parseInt(st.nextToken());
			System.out.print(N+" "+C+":");
			
			int n = 0;
			while(n<169 && prime[n]<=N) n++;
			int center = n/2;
			int start=0, end=0;
			
			if(n%2==1){
				if(C*2-1 > n){ start = 0;end = n; }
				else { start = center-(C-1); end = center+(C-1); }
			}
			else{
				if(C*2 > n){start = 0;end = n; }
				else { start = center-C; end = center+(C-1); }
			}
			if(end==169) end--;
			for(int j=start;j<=end;j++) 
				System.out.print(" "+prime[j]);
			System.out.println();
			System.out.println();
		}
	}
	void isComponent(){
		for(int i=2;i<1000;i += i==2?1:2){
			for(int j=i+i;j<1000;j+=i) component[j] = true;
		}
		int count = 0;
		
//		for(int i=0;i<169;i++) System.out.print(i+" ");
//		System.out.println();
		
		for(int i=1;i<1000;i++){
			if(!component[i]) {
				prime[count++] = i;
				
//				System.out.print(i+" ");
				
			}
		}
//		System.out.println();
	}
}
