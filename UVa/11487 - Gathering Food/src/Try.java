import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Try {
	//11487 - Gathering Food
	int N;
	char[][] grid;
	int I[] = {-1,0,1,0};
	int J[] = {0,1,0,-1};
	int posI[], posJ[];
	long counter[];
	boolean visited[][];
	int dist[];
	boolean possible;
	public static void main(String[] args) throws Exception{
		new Try().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(true){
			N = Integer.parseInt(bfr.readLine());
			if(N==0) break;
			grid = new char[N][N];
			posI = new int[N]; posJ = new int[N];
			counter = new long[N];
			dist = new int[N];
			Arrays.fill(dist, Integer.MAX_VALUE);
			for(int i=0;i<N;i++){
				grid[i] = bfr.readLine().toCharArray();
				for(int j=0;j<N;j++){
					if('A'<=grid[i][j] && grid[i][j]<='Z'){
						int index = grid[i][j] - 'A';
						posI[index] = i; posJ[index] = j;
					}
				}
			}
			//input clear
			possible = false;
			for(int i=0;i<N-1;i++){
				visited = new boolean[N][N];
				int src = i, des = i+1;
				DFS( posI[i], posJ[i], (char)(des+'A'), 0);
				//i -> i+1
				//keep in counter
				//if unreachable break, then Impossible
			}
			System.out.print("Case "+(tc++)+": ");
			if(!possible) System.out.println("Impossible");
			else{
				int totalDist = 0;
				long ways = 1;
				for(int i=0;i<N-1;i++){
					totalDist+= dist[i];
					ways = (ways*counter[i])%20437;
				}
				System.out.println(totalDist+" "+ways);
			}
		}
	}
	void DFS(int i, int j, char target, int move){
		if(!inRange(i,j) || grid[i][j]=='#') return;
		if(grid[i][j]==(char)(N+'A'-1)) possible = true;
		if(grid[i][j]==target){
//			counter[target-'A'-1]++;
			if(move<dist[target-'A'-1]){//reset freq
				dist[target-'A'-1] = move;//min
				counter[target-'A'-1] = 1;
			}
			else if(move==dist[target-'A'-1])
				counter[target-'A'-1]++;//increase freq
			return;
		}
		if(!visited[i][j]){
			visited[i][j] = true;
			for(int k=0;k<4;k++){
				DFS(i+I[k], j+J[k], target, move+1);
			}
			visited[i][j] = false;
		}
	}
	boolean inRange(int i, int j){
		return 0<=i&&i<N && 0<=j && j<N;
	}
}
