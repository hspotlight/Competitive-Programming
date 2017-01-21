import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//10285 - Longest Run on a Snowboard
	String name;
	int R,C;
	int map[][];
	int longest[][];
	int max;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			name = st.nextToken();
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			longest = new int[R][C];
			for(int i=0;i<R;i++){
				st = new StringTokenizer(bfr.readLine());
				for(int j=0;j<C;j++){
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = Integer.MIN_VALUE;
			for(int i=0;i<R;i++){
				for(int j=0;j<C;j++){
					int length = DFS(i,j);
					if(length>max) max = length;
				}
			}
			System.out.println(name+": "+max);
		}
	}
	int DFS(int i,int j){
		if(longest[i][j]!=0) return longest[i][j];
		int max = 0;
		int I[] = {-1,0,1,0};
		int J[] = {0,1,0,-1};
		for(int k=0;k<4;k++){
			int newI = i + I[k];
			int newJ = j + J[k];
			if(0<=newI&&newI<R&&0<=newJ&&newJ<C && map[i][j]>map[newI][newJ]){
				int x = DFS(newI,newJ);
				if(x>max) max = x;
			}
		}
		return longest[i][j] = (max + 1);
	}
}
