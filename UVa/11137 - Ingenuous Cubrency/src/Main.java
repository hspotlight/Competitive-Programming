import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11137 - Ingenuous Cubrency
	long ways[];
	int cube[] = {0,1,8,27,64,125,216,343,512,729,1000,1331,1728,2197,2744,3375,4096,4913,5832,6859,8000,9261};
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		ways = new long[10001];
		ways[0] = 1;
		for(int j=1;j<10001;j++) ways[j] = 0;
		for(int i=1;i<=21;i++){;
			for(int j=1;j<10001;j++) {
				if(j-cube[i]>=0) ways[j] += ways[j - cube[i]];
			}
		}
		while(bfr.ready()){
			String s = bfr.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int x = Integer.parseInt(st.nextToken());//Have an Exception in this line =__=
			System.out.println(ways[x]);
		}
	}
}
