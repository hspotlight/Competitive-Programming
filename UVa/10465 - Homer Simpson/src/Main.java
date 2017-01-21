import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	//10465 - Homer Simpson
	int m, n, t;
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			//
			int max = 0; //a+b
			int min = Integer.MAX_VALUE;   //c
			if(m<n){
				int tmp = m;
				m = n;
				n = tmp;
			}
			int a = 0, am = 0;
			while(t>=am){
				int left = t - am;
				int b = left/n;
				int c = left%n;
				if(min > c){
					min = c;
					max = a+b;
				}
				else if (min == c) max = Math.max(max, a+b);
				a++;
				am += m;
			}
			bfw.write(max + (min==0?"":" "+min)+"\n");
		}
		bfw.flush();
	}
}
