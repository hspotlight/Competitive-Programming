import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00352 - The Seasonal War
	int n;
	int I[] = {-1,-1,-1,0,1,1,1,0};
	int J[] = {-1,0,1,1,1,0,-1,-1};
	char[][] map;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = 1;
		while(bfr.ready()){
			n = Integer.parseInt(bfr.readLine());
			map = new char[n][n];
			for(int i=0;i<n;i++)
				map[i] = bfr.readLine().toCharArray();
			int count = 0;
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(map[i][j]=='1'){
						count++;
						//DFS
						DFS(i,j);
					}
				}
			}
			System.out.println("Image number "+(tc++)+" contains "+count+" war eagles.");
		}
	}
	void DFS(int i, int j){
		if(!inRange(i,j) || map[i][j]=='0') return;
		map[i][j] = '0';
		for(int k=0;k<8;k++) 
			DFS(i+I[k], j+J[k]);
	}
	boolean inRange(int i, int j){
		return 0<=i && i<n && 0<=j && j<n;
	}
}