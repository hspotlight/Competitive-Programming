import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//00341 - Non-Stop Travel
	String line;
	int n, minDelay,delay[];
	int track[];
	ArrayList<ArrayList<Node>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			while(st.countTokens()==0){
				st = new StringTokenizer(bfr.readLine());
			}
			n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			path = new ArrayList<ArrayList<Node>>();
			for(int i=0;i<=n;i++){
				path.add(new ArrayList<Node>());
			}
			delay = new int[n+1];
			track = new int[n+1];
			for(int i=1;i<=n;i++){
				delay[i] = Integer.MAX_VALUE;
				st = new StringTokenizer(bfr.readLine());
				int m = Integer.parseInt(st.nextToken());
				for(int j=0;j<m;j++){// m/2 round
					int des = Integer.parseInt(st.nextToken());
					int del = Integer.parseInt(st.nextToken());
					path.get(i).add(new Node(des,del)); // i -> des
				}
			}
			/***********************************************/
			//query
			st = new StringTokenizer(bfr.readLine());
			int source = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			System.out.print("Case "+(c++)+": Path =");
			dijkstra(source,target);
			System.out.println("; "+minDelay+" second delay");
		}
	}
	void dijkstra(int source, int target){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean visited[] = new boolean[n+1];
		pq.add(new Node(source,0));
		delay[source] = 0;
		track[source] = -1;//null
		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int v1 = cur.id;
			visited[v1] = true;
			if(v1==target){//finished
				int x = track[v1];
				ArrayList<Integer> t = new ArrayList<Integer>();
				t.add(v1);
				while(x!=-1){
					t.add(x);
					x = track[x];
				}
				for(int i=t.size()-1;i>=0;i--){
					System.out.print(" "+t.get(i));
				}
				minDelay = delay[v1];
				break;
			}
			for(Node next : path.get(v1)){
				int v2 = next.id;
				if(!visited[v2]){
					if(delay[v1] + next.delay < delay[v2]){//new dist < memory 
						delay[v2] = delay[v1] + next.delay;
						pq.add(new Node(v2,delay[v2]));
						track[v2] = v1;//source -> v2
					}
				}
			}
		}
	}
	public class Node implements Comparable<Node>{
		int id;
		int delay;
		public Node(int id, int delay){
			this.id = id;
			this.delay = delay;
		}
		public int compareTo(Node another){
			return this.delay - another.delay;
		}
	}
}
