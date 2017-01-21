import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11094 - Continents 
	int R, C;
	char[][] map;
	int visited[][];
	int I[] = {-1,0,1,0};
	int J[] = {0,1,0,-1};
	char water;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][];
			visited = new int[R][C];
			for(int i=0;i<R;i++) map[i] = bfr.readLine().toCharArray();
			//query
			st = new StringTokenizer(bfr.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char c = map[x][y];
			water = 0;
			traversal(x,y,c);
			int max = 0;
			for(int i=0;i<R;i++){
				for(int j=0;j<C;j++){
					if(visited[i][j]== 0 && map[i][j]!=water){
						int n = traversal(i,j,map[i][j]);
//						System.out.println(i+" "+j);
//						System.out.println("total land = "+n);
						if(n > max) max = n;
					}
				}
			}
//			for(int i=0;i<R;i++){
//				for(int j=0;j<C;j++){
//					System.out.print(visited[i][j]);
//				}
//				System.out.println();
//			}
			System.out.println(max);
//			System.out.println("************************");
			bfr.readLine();
		}
	}
	public int traversal(int x,int y, char c){
		if( ( !inRange(x,y) ) ) return 0;
		if(y==-1) y = C-1;
		if(y==C) y = 0;
		if (map[x][y] != c) {
			if(water==0) water = map[x][y];
			return 0;
		}
		if(visited[x][y]==1) return 0;
		visited[x][y] = 1;
		int sum = 1;
		for(int k=0;k<4;k++){
			sum += traversal(x+I[k], y+J[k],c);
		}
		return sum;
	}
	public boolean inRange(int i, int j){
		if(j==-1 || j==C) j = 0;
		return 0 <= i && i < R && 0 <= j && j < C;
	}
}
