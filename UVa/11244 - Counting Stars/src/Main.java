import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11244 - Counting Stars
	int r, c;
	int[] I = {-1,-1,-1,0,1,1,1,0};
	int[] J = {-1,0,1,1,1,0,-1,-1};
	char[][] map;
	boolean[][] visited;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(r==0 && c==0) break;
			map = new char[r][c];
			visited = new boolean[r][c];
			for(int i=0;i<r;i++)
				map[i] = bfr.readLine().toCharArray();
			int count = 0;
			for(int i=0;i<r;i++){
				for(int j=0;j<c;j++){
					if(map[i][j] == '*'){
						int c = DFS(i,j);
						if(c==1) count++;
					}
				}
			}
			System.out.println(count);
		}
	}
	boolean inRange(int i, int j){
		return 0<=i && i<r && 0<=j && j<c;
	}
	int DFS(int i, int j){
		if(!inRange(i,j) || map[i][j]!='*' || visited[i][j]) return 0;
		visited[i][j] = true;
		int count = 1;
		for(int k=0;k<8;k++)
			count += DFS(i+I[k], j+J[k]);
		return count;
	}
}
