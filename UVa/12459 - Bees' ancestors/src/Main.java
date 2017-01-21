import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	//12459 - Bees' ancestors
	public static void main(String[] args) throws Exception{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		long ancestor[] = new long[81];
		ancestor[0] = ancestor[1] = 1;
		for(int i=2;i<=80;i++){
			ancestor[i] = ancestor[i-1] + ancestor[i-2];
		}
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			if(n==0) break;
			
			System.out.println(ancestor[n]);
		}
	}
}
