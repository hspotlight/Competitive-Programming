import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//00544 - Heavy Cargo
	boolean visited[];
	int n, r, index, minimum;
	HashMap<String,Integer> map;
	ArrayList<ArrayList<Edge>> list;
	ArrayList<ArrayList<Edge>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	int getIndex(String s){
		if(map.containsKey(s)) return map.get(s);
		map.put(s, index);
		return index++;
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			if(n==0 && r==0) break;
			map = new HashMap<String,Integer>();
			list = new ArrayList<ArrayList<Edge>>();
			path = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<n;i++) list.add(new ArrayList<Edge>());
			for(int i=0;i<n;i++) path.add(new ArrayList<Edge>());
			index = 0;
			while(r-->0){
				st = new StringTokenizer(bfr.readLine());
				int s = getIndex(st.nextToken());
				int d = getIndex(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				list.get(s).add(new Edge(d,w));
				list.get(d).add(new Edge(s,w));
			}
			st = new StringTokenizer(bfr.readLine());
			int source = getIndex(st.nextToken());
			int target = getIndex(st.nextToken());
			/*******************************************************/
			//traversal
			MST();
			visited = new boolean[n];
			minimum = Integer.MAX_VALUE;
			traversal(source, target, minimum);
			System.out.println("Scenario #"+(c++));
			System.out.println(minimum+" tons");
			System.out.println();
		}
	}
	void traversal(int current, int target, int min){
		if(minimum!=Integer.MAX_VALUE) return;
		if(current == target) { minimum = min; return; }
		visited[current] = true;
		for(Edge e : path.get(current)){
			if(!visited[e.des]){
				traversal(e.des,target, e.load > min? min : e.load);
			}
		}
	}
	void MST(){ //maximum spanning tree
		visited = new boolean[n];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(-1,0,0));
		int nLeft = n;
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			if(visited[cur.des]) continue;
			visited[cur.des] = true;
			nLeft--;
			//work
			if(cur.src!=-1){
				path.get(cur.src).add(new Edge(cur.des,cur.load));
				path.get(cur.des).add(new Edge(cur.src,cur.load));
			}
			if(nLeft==0) break; //terminate
			for(Edge e : list.get(cur.des)){
				if(!visited[e.des]){
					pq.add(new Edge(cur.des,e.des,e.load));
				}
			}
		}
	}
	public class Edge implements Comparable<Edge>{
		int src;
		int des;
		int load;
		public Edge(int s, int d, int l){
			this(d,l);
			src = s;
		}
		public Edge(int d, int l){
			des  = d;
			load = l;
		}
		public int compareTo(Edge another){//maximum load
			return -(this.load - another.load);
		}
	}
}
