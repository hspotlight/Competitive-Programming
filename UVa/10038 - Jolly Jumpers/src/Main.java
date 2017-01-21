import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {
	//10038 - Jolly Jumpers
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int n = Integer.parseInt(st.nextToken());
			boolean jolly = check(n,st);
			if(jolly) System.out.println("Jolly");
			else System.out.println("Not jolly");
		}
	}
	public boolean check(int n, StringTokenizer st){
		boolean[] check = new boolean[n+1];
		if(n>1){
			int prev = Integer.parseInt(st.nextToken());
			for(int i=2;i<n;i++){
				int cur = Integer.parseInt(st.nextToken());
				int diff = cur - prev;
				if(diff<0) diff = -diff;
				if(1<=diff && diff<n){
					if(check[diff]) return false;
					else check[diff] = true;
				}
				else return false;
				prev = cur;
			}
		}
		return true;
	}
}
