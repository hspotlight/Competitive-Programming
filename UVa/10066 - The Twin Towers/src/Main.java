import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//10066 - The Twin Towers
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(n1==0&&n2==0) break;
			int[] s = new int[n1+1];
			int[] t = new int[n2+1];
			st = new StringTokenizer(bfr.readLine());
			for(int i=1;i<=n1;s[i++] = Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(bfr.readLine());
			for(int i=1;i<=n2;t[i++] = Integer.parseInt(st.nextToken()));
			System.out.println("Twin Towers #"+(c++));
			System.out.println("Number of Tiles : "+LCS(s,t));
			System.out.println();
		}
	}
	int LCS(int[] s, int[] t){
		int map[][] = new int[s.length][t.length];
		for(int i=1;i<s.length;i++){
			for(int j=1;j<t.length;j++){
				if(s[i]==t[j]) map[i][j] = map[i-1][j-1]+1;
				else{
					map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
				}
			}
		}
		return map[s.length-1][t.length-1];
	}
}
