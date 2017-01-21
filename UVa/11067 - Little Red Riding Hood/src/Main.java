import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11067 - Little Red Riding Hood
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	int w, h;
	int[][] map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			w = readInt(); h = readInt();
			if(w==0 && h==0) break;
			map = new int[102][102];
			int n = readInt();
			for(int i=0;i<n;i++){
				int x = readInt(), y = readInt();
				map[x][y] = -1;
			}
			int ans = traversal();
			if(ans==0) 
				System.out.println("There is no path.");
			else if (ans==1)
				System.out.println("There is one path from Little Red Riding Hood's house to her grandmother's house.");
			else 
				System.out.println("There are "+ans+" paths from Little Red Riding Hood's house to her grandmother's house.");
		}
	}
	int traversal(){
		for(int i=0;i<=w;i++){
			for(int j=0;j<=h;j++){
				if(map[i][j] == -1) continue;
				if(i==0&&j==0) map[i][j] = 1; //little red riding hood's house
				else map[i][j] = getMove(i-1,j) + getMove(i,j-1); //move up and left
			}
		}
		return map[w][h];
	}
	int getMove(int i, int j){
		if(i<0 || j<0) return 0;
		return map[i][j]==-1? 0 : map[i][j];
	}
}
