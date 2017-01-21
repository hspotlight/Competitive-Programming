import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimeCuts {
	//00406 - Prime Cuts
	boolean component[] = new boolean[1001];
	int prime[] = new int[169];
	public static void main(String [] args) throws Exception{
		new PrimeCuts().run();
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
			int start=0, length=0;
			if(n%2==0){
				length = C*2;
				start = (n-length)/2;
			}
			else{
				length = (C*2)-1;
				start = (n-length)/2;
			}
			if(length > n){
				start = 0;
				length = n;
			}	
			for(int j=start;j<start+length;j++) 
				System.out.print(" "+prime[j]);
			System.out.println("\n");
		}
	}
	void isComponent(){
		for(int i=2;i<1000;i += i==2?1:2){
			for(int j=i+i;j<1000;j+=i) component[j] = true;
		}
		int count = 0;
		for(int i=1;i<1000;i++){
			if(!component[i]) {
				prime[count++] = i;
			}
		}
	}
}