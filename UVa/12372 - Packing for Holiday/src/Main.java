import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//12372 - Packing for Holiday
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int i=1;i<=tc;i++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int[] A = new int[3];
			for(int j=0;j<3;j++)
				A[j] = Integer.parseInt(st.nextToken());
			System.out.print("Case "+i+": ");
			if(A[0]<=20 && A[1]<=20 && A[2]<=20) System.out.println("good");
			else System.out.println("bad");
		}
	}
}
