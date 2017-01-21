import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	int n;
	char map[][];
	int I[] = {-1,0,1,0};
	int J[] = {0,1,0,-1};
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c=1;c<=tc;c++){
			n = Integer.parseInt(bfr.readLine());
			map = new char[n][];
			int count = 0;
			for(int i=0;i<n; map[i++] = bfr.readLine().toCharArray());
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(map[i][j]=='x'){
						DFS(i,j);
						count++;
					}
				}
			}
			System.out.println("Case "+c+": "+count);
		}
	}
	void DFS(int i, int j){
		if(!inRange(i,j) || map[i][j]=='.' || map[i][j]=='V') return;
		map[i][j] = 'V';
		for(int k=0;k<4;k++) DFS(i+I[k], j+J[k]);
	}
	boolean inRange(int i, int j){
		return 0<=i && i<n && 0<=j && j<n;
	}
}
