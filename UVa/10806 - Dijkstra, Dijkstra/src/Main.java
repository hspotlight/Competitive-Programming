import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class Main {
	//10806 - Dijkstra, Dijkstra
	int n, m;
	int map[][];
	int prev[];
	StreamTokenizer stk;
	int distance[];
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			n = readInt();
			if(n==0) break;
			map = new int[n][n];
			distance = new int[n];
			m = readInt();
			for(int i=0;i<m;i++){
				int s = readInt()-1, d = readInt()-1, w = readInt();
				map[s][d] = w;
				map[d][s] = w;
			}
			prev = new int[n];
			int bell = dijkstra(); //friend go first
			int c = n-1;
			while(c!=0){// p -> c
				int p = prev[c];
				map[c][p] = -map[c][p];//negative edge
				map[p][c] = 0;         //remove edge
				c = p;
			}
			int hong = bellmanFord();
			if(bell==Integer.MAX_VALUE|| hong==Integer.MAX_VALUE) System.out.println("Back to jail");
			else System.out.println(bell+hong);
		}
	}
	int bellmanFord(){
		for(int i=0;i<n; distance[i++] = Integer.MAX_VALUE);
		distance[0] = 0;
		for(int i=1;i<n;i++){//iterator
			for(int u=0;u<n;u++){
				for(int v=0;v<n;v++){
					if(map[u][v]==0 || distance[u]==Integer.MAX_VALUE) continue;
					int alt;
					if((alt = distance[u]+map[u][v]) < distance[v]) distance[v] = alt;
				}
			}
		}
		return distance[n-1];
	}
	int dijkstra(){
		for(int i=0;i<n; distance[i++] = Integer.MAX_VALUE);
		boolean visited[] = new boolean[n];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(0,distance[0] = 0));
		while(!pq.isEmpty()){
			Node u = pq.poll();
			if(visited[u.id]) continue;
			visited[u.id] = true;
			for(int v=0;v<n;v++){
				int alt;
				if(map[u.id][v]!=0 && (alt = distance[u.id]+map[u.id][v]) < distance[v]){
					prev[v] = u.id;
					pq.add(new Node(v,distance[v] = alt));
				}
			}
		}
		return distance[n-1];
	}
	class Node implements Comparable<Node>{
		int id;
		int dist;
		public Node(int id, int d){
			this.id = id;
			dist = d;
		}
		public int compareTo(Node another){
			return dist - another.dist;
		}
	}
}
