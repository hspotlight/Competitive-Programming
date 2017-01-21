import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//00382 - Perfection
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		//pre-processing
		int n = 60001;
		int sum[] = new int[n];
		for(int i=2;i<n;i++){
			for(int multiple=i+i;multiple<n;multiple+=i){
				sum[multiple] += i;
			}
		}
		//process
		System.out.println("PERFECTION OUTPUT");
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			while(st.hasMoreTokens()){
				int x = Integer.parseInt(st.nextToken());
				if(x==0) break;
				String s ="";
				
				if(x==sum[x]+1) s = "PERFECT";
				else if (x < sum[x]+1) s = "ABUNDANT";
				else s = "DEFICIENT";
				if(x==1)s = "DEFICIENT";
				System.out.printf("%5d  %s\n",x,s);
			}
		}
		System.out.println("END OF OUTPUT");
	}
}
