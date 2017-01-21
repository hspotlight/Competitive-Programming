import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//10048 - Audiophobia
	int C, S, Q;
	int max;
	boolean visited[];
	ArrayList<ArrayList<Edge>> path;
	ArrayList<ArrayList<Edge>> map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		boolean first = true;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			C = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			if(C==0 && S==0 && Q==0) break;
			if(!first) System.out.println();
			first = false;
			//add street
			path = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<=C;i++) path.add(new ArrayList<Edge>());
			for(int i=0;i<S;i++){
				st = new StringTokenizer(bfr.readLine());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				path.get(s).add(new Edge(s,d,w));
				path.get(d).add(new Edge(d,s,w));
			}
			map = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<=C;i++) map.add(new ArrayList<Edge>());
			//MST
			visited = new boolean[C+1];
			for(int i=1;i<=C;i++) if(!visited[i]) MST(i);
			/*******************************************************/
			System.out.println("Case #"+(c++));
			for(int i=0;i<Q;i++){
				st = new StringTokenizer(bfr.readLine());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				visited = new boolean[C+1];
				max = Integer.MIN_VALUE;
				traversal(s,d,max);
				int decibel = max; 
				if(decibel==Integer.MIN_VALUE) System.out.println("no path");
				else System.out.println(decibel);
			}
		}
	}
	void traversal(int cur, int target, int max){
		if(cur==target) this.max = max;	
		if(this.max!=Integer.MIN_VALUE) return;
		if(visited[cur]) return;
		visited[cur] = true;
		for(Edge e : map.get(cur)){
			traversal(e.des,target, Math.max(max, e.weight));
		}
	}
	void MST(int i){
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(-1,i,0)); 
		while(!pq.isEmpty()){
			Edge v1 = pq.poll();
			if(visited[v1.des]) continue;
			visited[v1.des] = true;
			if(v1.src!=-1){
				map.get(v1.src).add(new Edge(v1.src, v1.des, v1.weight));
				map.get(v1.des).add(new Edge(v1.des, v1.src, v1.weight));
			}
			for(Edge v2 : path.get(v1.des)){
				if(!visited[v2.des]){
					pq.add(new Edge(v1.des,v2.des,v2.weight));
				}
			}
		}
	}
	public class Edge implements Comparable<Edge>{
		int src;
		int des;
		int weight;
		public Edge(int s, int d, int w){
			src = s;
			des = d;
			weight = w;
		}
		public int compareTo(Edge another){
			return this.weight - another.weight;
		}
		public String toString(){
			return "("+des+", "+weight+")";
		}
	}
}
