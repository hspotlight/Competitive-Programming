import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Main {
	//10370 - Above Average
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int n = Integer.parseInt(st.nextToken());
			int A[] = new int[n];
			int average = 0;
			for(int i=0;i<n;i++){
				A[i] = Integer.parseInt(st.nextToken());
				average += A[i];
			}
			average /= n;
			int count = 0;
			for(int i=0;i<n;i++){
				if(A[i]>average) count++;
			}
			System.out.println( new DecimalFormat("0.000").format((count*100.0)/n)+"%");
			
		}
	}
}
