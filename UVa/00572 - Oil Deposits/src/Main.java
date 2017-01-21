import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00572 - Oil Deposits
	int r, c;
	int I[] = {-1,-1,-1,0,1,1,1,0};
	int J[] = {-1,0,1,1,1,0,-1,-1};
	char map[][];
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
			map = new char[r][];
			for(int i=0;i<r;i++){
				map[i] = bfr.readLine().toCharArray();
			}
			int count = 0;
			for(int i=0;i<r;i++){
				for(int j=0;j<c;j++){
					if(map[i][j]=='@'){
						DFS(i,j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}
	void DFS(int i, int j){
		map[i][j]='*';
		for(int k=0;k<8;k++){
			if(0<= i+I[k] && i+I[k]<r && 0<=j+J[k] &&j+J[k]<c && map[i+I[k]][j+J[k]]=='@')
				DFS(i+I[k], j+J[k]);
		}
	}
}
