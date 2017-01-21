import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class Main {
	//00929 - Number Maze
	StreamTokenizer stk;
	int N, M;
	int[] I = {-1,0,1,0};
	int[] J = {0,1,0,-1};
	int[][] map, distance;
	boolean[][] visited;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			N = readInt(); M = readInt();
			map = new int[N][M];
			distance = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0;i<N;i++){
				for(int j=0;j<M;j++){
					map[i][j] = readInt();
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
			dijkstra();
			System.out.println(distance[N-1][M-1]);
		}
	}
	void dijkstra(){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(0,0, map[0][0]));
		while(!pq.isEmpty()){
			Node u = pq.poll();
			if(visited[u.i][u.j]) continue;
			visited[u.i][u.j] = true;
			distance[u.i][u.j] = u.weight;
			for(int k=0;k<4;k++){
				int vI = u.i + I[k], vJ = u.j + J[k];
				if(inRange(vI,vJ)){
					if(distance[vI][vJ] > u.weight + map[vI][vJ]){
						distance[vI][vJ] = u.weight + map[vI][vJ];
						pq.add(new Node(vI,vJ,distance[vI][vJ]));
					}
				}
			}
		}
	}
	boolean inRange(int i, int j){
		return 0<=i && i<N && 0<=j && j<M;
	}
	public class Node implements Comparable<Node>{
		int i;
		int j;
		int weight;
		public Node(int i, int j, int weight){
			this.i = i;
			this.j = j;
			this.weight = weight;
		}
		public int compareTo(Node v){
			return weight - v.weight;
		}
	}
}
