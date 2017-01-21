import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//00825 - Walking on the Safe Side
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	int n, m;
	int map[][];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n+1][m+1];
			String s = bfr.readLine();
			while(s!=null){
				if(s.equals("")) break;
				st = new StringTokenizer(s);
				int i = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens()){
					int j = Integer.parseInt(st.nextToken());
					map[i][j] = -1;
				}
				s = bfr.readLine();
			}
			for(int i=1;i<=n;i++){
				for(int j=1;j<=m;j++){
					if(map[i][j]==-1) continue;//not yet visited
					if(i==1 && j==1) map[i][j] = 1;
					else map[i][j] = getVal(i-1,j) + getVal(i,j-1);
				}
			}
			System.out.println(map[n][m]);
			if(bfr.ready()) System.out.println();
		}
	}
	int getVal(int i,int j){
		return map[i][j]==-1? 0 : map[i][j];
	}
}
