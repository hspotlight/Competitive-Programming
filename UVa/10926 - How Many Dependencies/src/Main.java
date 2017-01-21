import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10926 - How Many Dependencies
	int n;
	int[][] map;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while((n = readInt())!=0){
			map = new int[n+1][n+1];
			for(int i=1;i<=n;i++){
				int c = readInt();
				for(int j=0;j<c;j++){
					int des = readInt();
					map[i][des] = 1;
				}
			}
			FW();
			int index = -1;
			int max = -1;
			for(int i=1;i<=n;i++){
				int count = 0;
				for(int j=1;j<=n;j++){
					if(map[i][j]!=0) count++;
				}
				if(count > max) { max = count; index = i; }
			}
			System.out.println(index);
		}
	}
	void FW(){
		for(int k=1;k<=n;k++){
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					if(map[i][k]==0 || map[k][j]==0) continue;
					if(map[i][j] < map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
	}
}
