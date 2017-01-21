import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11805 - Bafana Bafana
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		BufferedReader bfr =  new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(bfr.readLine());
		for(int tc=1;tc<=TC;tc++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken()) - 1;
			int P = Integer.parseInt(st.nextToken());
			int pos = ((P+K) % N) + 1;
			System.out.println("Case "+tc+": "+pos);
		}
	}
}
