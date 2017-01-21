import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00111 - History Grading
	int n;
	int[] sol, ans;
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bfr.readLine());
		sol = getInput( new StringTokenizer(bfr.readLine()) );
		while(bfr.ready()){
			ans = getInput( new StringTokenizer(bfr.readLine()));
			System.out.println(LCS());
		}
	}
	int[] getInput(StringTokenizer st){
		int[] s = new int[n+1];
		for(int i=1;i<=n; i++) s[Integer.parseInt(st.nextToken())] = i;
		return s;
	}

	int LCS(){
		int[][] map = new int[n+1][n+1];
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				if(sol[i]==ans[j]) map[i][j] = map[i-1][j-1] + 1;
				else map[i][j] = Math.max( map[i-1][j], map[i][j-1]);
			}
		}
		return map[n][n];
	}
}
