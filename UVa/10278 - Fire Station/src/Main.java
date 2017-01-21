import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//10278 - Fire Station
	int I, N;
	HashSet<Integer> fireStation;
	ArrayList<Node>[] roadSegment;
	int[] dist; //distance from nearest fireStation
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();//blank line
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			I = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			fireStation = new HashSet<Integer>();
			roadSegment = new ArrayList[N+1];
			for(int i=0;i<=N;i++) roadSegment[i] = new ArrayList<Node>();
			
			for(int i=0;i<I;i++) //add fire station in the intersection
				fireStation.add(Integer.parseInt(bfr.readLine().trim()));
			
			String line;
			while( (line = bfr.readLine())!=null){
				if(line.isEmpty()) break;
				st = new StringTokenizer(line);
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				//add Edge
				roadSegment[u].add(new Node(v, w));
				roadSegment[v].add(new Node(u, w));
			}
			
			//find shortest distance from nearest fireStation
			dist = new int[N+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			for(int fs : fireStation){
				int[] d = dijkstra(fs);
				for(int i=0;i<=N;i++)
					dist[i] = Math.min(dist[i], d[i]); //DP
			}
			
			//try to put fireStation in intersection i
			int intersection = 0, min = Integer.MAX_VALUE;
			for(int i=1;i<=N;i++){
				int[] d = dijkstra(i);
				int[] tmp = new int[N+1];
				int max = -1;
				
				for(int j=1;j<=N;j++)
					max = Math.max(max, tmp[j] = Math.min(dist[j], d[j]));
				
				if(min > max){
					min = max;
					intersection = i;
				}
			}
			System.out.println(intersection);
			if(tc>0) System.out.println();
		}
	}
	public int[] dijkstra(int src){
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(src,0)); dist[src] = 0;
		
		while(!pq.isEmpty()){
			Node u = pq.poll();
			int from = u.to;
			if(!visited[from]){
				visited[from] = true;
				for(Node v : roadSegment[from]){
					int to = v.to, alt;
					if(!visited[to] && (alt = dist[from] + v.weight) < dist[to]){
						pq.add(new Node(to, dist[to] = alt));
					}
				}
			}
		}
		return dist;
	}
	public class Node implements Comparable<Node>{
		int to, weight;
		public Node(int t, int w){
			to =  t;
			weight = w;
		}
		public int compareTo(Node n){ return weight - n.weight; }
	}
}
