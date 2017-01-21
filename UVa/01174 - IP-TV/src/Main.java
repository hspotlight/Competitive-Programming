import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//01174 - IP-TV
	ArrayList<ArrayList<Edge>> list;
	int s,d;
	int min;
	int n, m;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			n = Integer.parseInt(bfr.readLine());
			list = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<n;i++) list.add(new ArrayList<Edge>());
			m = Integer.parseInt(bfr.readLine());
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			int index = 0;
			min = Integer.MAX_VALUE;
			for(int i=0;i<m;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				String src = st.nextToken();
				String des = st.nextToken();
				int cost = Integer.parseInt(st.nextToken());
				if(!map.containsKey(src)) map.put(src, index++);
				if(!map.containsKey(des)) map.put(des, index++);
				if(cost < min){
					min = cost;
					s = map.get(src);
					d = map.get(des);
				}
				list.get( map.get(src) ).add(new Edge(map.get(des), cost));
				list.get( map.get(des) ).add(new Edge(map.get(src), cost));
			}
			prim();
			if(bfr.ready()) System.out.println();
		}
	}
	void prim(){//undirected
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(Edge e : list.get(s)) if(e.id!=d) pq.add(e);
		for(Edge e : list.get(d)) if(e.id!=s) pq.add(e);
		boolean visited[] = new boolean[n];
		visited[d] = visited[s] = true;
		int cost = min;		
		int nodeLeft = n-2;
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			if(visited[cur.id]) continue;
			visited[cur.id] = true;
			cost += cur.cost;
			nodeLeft--;
			if(nodeLeft==0) break;
			for(Edge e : list.get(cur.id)){
				if(!visited[e.id]) pq.add(e);
			}
		}
		System.out.println(cost);
		
	}
	class Edge implements Comparable<Edge>{
		int id;
		int cost;
		public Edge(int id, int cost){
			this.id = id;
			this.cost = cost;
		}
		public int compareTo(Edge another){
			return this.cost - another.cost;
		}
	}
}