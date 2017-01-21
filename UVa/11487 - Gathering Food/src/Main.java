import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//11487 - Gathering Food
	Node cur;
	char[][] map;
	long[][] ways;
	int N, highestValue;
	boolean[][] visited;
	int[] I = {-1,0,1,0};
	int[] J = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(true){
			N = Integer.parseInt(bfr.readLine());
			if(N==0) break;
			//terminate case
			highestValue = 0;
			map = new char[N][N];
			for(int i=0;i<N; i++){
				map[i] = bfr.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					if('A'<=map[i][j] && map[i][j]<='Z'){
						highestValue = Math.max(highestValue, map[i][j]);
						if(map[i][j]=='A') cur = new Node(i,j);
					}
				}
			}
			/*******************************************/
			long[] ans = gatheringFood(cur);
			System.out.println( "Case "+(tc++)+": "+(ans == null? "Impossible" : ans[0]+" "+ans[1]) );
		}
	}
	long[] gatheringFood(Node cur){
		long[] ans = new long[2]; //ans[0] = BFS, ans[1] = total of ways
		ans[1] = 1;
		//collect all foods
		for(int i='A'; i<highestValue;i++){
			//start from A
			Node next = BFS(cur.i, cur.j);
			if(next == null) return null;
			else{
				//find total path
				ans[0] += next.move;
				ans[1] = (ans[1] * ways[next.i][next.j]) % 20437;
			}
			cur = next;
		}
		return ans;
	}
	Node BFS(int SI, int SJ){
		ways = new long[N][N];
		visited = new boolean[N][N];
		char target = (char)(map[SI][SJ] + 1);
		Queue<Node> qe = new LinkedList<Node>();
		map[SI][SJ] = '.';
		qe.add(new Node(SI, SJ, 0)); visited[SI][SJ] = true;
		ways[SI][SJ] = 1;
		while(!qe.isEmpty()){
			Node u = qe.poll();
			int VI, VJ;
			long sum = 0;
			for(int k=0;k<4;k++){
				if(inRange(VI = u.i + I[k], VJ = u.j + J[k])){
					if(!visited[VI][VJ]){
						Node v = new Node(VI, VJ, u.move + 1);
						if(map[VI][VJ]=='.' || map[VI][VJ]==target){
							qe.add(v); visited[VI][VJ] = true;
						}
					}
					else sum += ways[VI][VJ];
				}
			}
			if(!(u.i==SI && u.j==SJ)) ways[u.i][u.j] = sum;
			if(map[u.i][u.j] == target) return u;
		}
		return null;
	}
	boolean inRange(int i, int j){ return 0<=i && i<N && 0<=j && j<N; }
	public class Node{
		int i, j, move;
		public Node(int i, int j){
			this.i = i;
			this.j = j;
		}
		public Node(int i, int j, int move){
			this(i, j);
			this.move = move;
		}
	}
}
