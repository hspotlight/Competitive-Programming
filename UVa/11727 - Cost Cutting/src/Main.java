import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//11727 - Cost Cutting
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c = 1; c<=tc;c++){
			int salary[] = new int[3];
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			salary[0] = Integer.parseInt(st.nextToken());
			salary[1] = Integer.parseInt(st.nextToken());
			salary[2] = Integer.parseInt(st.nextToken());
			Arrays.sort(salary);
			System.out.println("Case "+c+": "+salary[1]);
		}
	}
}
