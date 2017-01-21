import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//10324 - Zeros and Ones
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		while(bfr.ready()){
			String line = bfr.readLine();
			if(line.isEmpty()) break;
			char[] s = line.toCharArray();
			int n = Integer.parseInt(bfr.readLine());
			System.out.println("Case "+(c++)+":");
			for(int k=0;k<n;k++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				String ans = "Yes";
				if(i!=j){
					int mx = Math.max(i, j);
					int mn = Math.min(i, j);
					char pattern = s[mn];
					mn++;
					while(mn<=mx){
						if(pattern!=s[mn]){
							ans = "No";
							break;
						}
						mn++;
					}
				}
				System.out.println(ans);
			}
		}
	}
}