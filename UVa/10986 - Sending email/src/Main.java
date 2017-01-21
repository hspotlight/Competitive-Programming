import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//10986 - Sending email
	int n,m,s,t,dist[];
	boolean visited[];
	ArrayList<ArrayList<Node>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c = 1;c<=tc;c++){
			String str = bfr.readLine();
			while(str.equals("")) str = bfr.readLine();
			StringTokenizer st = new StringTokenizer(str);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			path = new ArrayList<ArrayList<Node>>();
			dist = new int[n];
			visited = new boolean[n];
			for(int i=0;i<n;i++) {
				path.add( new ArrayList<Node>() );
				dist[i] = Integer.MAX_VALUE;
			}
			for(int i=0;i<m;i++){
				st = new StringTokenizer(bfr.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int W = Integer.parseInt(st.nextToken());
				path.get(A).add(new Node(B,W));
				path.get(B).add(new Node(A,W));
			}
			System.out.println("Case #"+c+": "+ traversal() );
		}
	}
	String traversal(){
		PriorityQueue<Node> p = new PriorityQueue<Node>();
		p.add( new Node(s,0) );
		dist[s] = 0;
		
		while(!p.isEmpty()){
			Node node  = p.poll();
			int v1 = node.node;			
			if(visited[v1]) continue;
			if(v1==t) return ""+dist[t];
			visited[v1] = true;
			for(Node next : path.get(v1)){
				int v2 = next.node;
				if(!visited[v2]){
					if(dist[v1]+next.dist < dist[v2]){
						dist[v2] = dist[v1] + next.dist;
						p.add(new Node(v2, dist[v2]));
					}
				}
			}
		}
		return "unreachable";
	}
	class Node implements Comparable<Node>{
		int node;
		int dist;
		public Node(int n,int d){
			node = n;
			dist = d;
		}
		public int compareTo(Node another){
			return this.dist - another.dist;
		}
	}
}
