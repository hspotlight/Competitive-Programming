import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//12503 - Robot Instructions
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			int n = Integer.parseInt(bfr.readLine());
			int mem[] = new int[n+1];
			int pos = 0;
			for(int i=1;i<=n;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				String cmd = st.nextToken();
				if(cmd.equals("LEFT")) mem[i] = -1;
				else if (cmd.equals("RIGHT")) mem[i] = 1;
				else{
					st.nextToken();
					mem[i] = mem[Integer.parseInt(st.nextToken())];
				}
				pos += mem[i];
			}
			System.out.println(pos);
		}
	}
}