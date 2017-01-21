import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//01112 - Mice and Maze
	int N, E, T;
	int[][] map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			N = Integer.parseInt(bfr.readLine());
			E = Integer.parseInt(bfr.readLine());
			T = Integer.parseInt(bfr.readLine());
			map = new int[N+1][N+1];
			int m = Integer.parseInt(bfr.readLine());
			for(int i=0;i<m;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				map[d][s] = w;
			}
			int dist[] = dijkstra();
			//COUNT
			int count = 0;
			for(int i=1;i<dist.length;i++){
				if(dist[i] <= T) count++;
			}
			System.out.println(count);
			if(bfr.ready()) System.out.println();
		}
	}
	int[] dijkstra(){
		int[] distance = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(E, 0));
		distance[E] = 0;
		while(!pq.isEmpty()){
			Node u = pq.poll();
			if(!visited[u.block]){
				visited[u.block] = true;
				for(int v=1;v<N+1;v++){
					if(!visited[v] && map[u.block][v]!=0){
						int alt = u.dist + map[u.block][v];
						if(alt < distance[v]){
							distance[v] = alt;
							pq.add(new Node(v, distance[v]));
						}
					}
				}
			}
		}
		return distance;
	}
	public class Node implements Comparable<Node>{
		int block;
		int dist;
		public Node(int b, int d){
			block = b;
			dist = d;
		}
		public int compareTo(Node n){return dist - n.dist; }
	}
}
