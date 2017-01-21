import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//10034 - Freckles
	ArrayList<Node> list;
	int n;
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
			list = new ArrayList<Node>();
			for(int i=0;i<n;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				list.add(new Node(st.nextToken(), st.nextToken()));
			}
			System.out.println( new DecimalFormat("0.00").format(KRUSKAL()) );
//			System.out.println( new DecimalFormat("0.00").format(PRIM()) );
			if(tc>0) System.out.println();
		}
	}
	double KRUSKAL(){
		//add edge to list
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				edgeList.add(new Edge(i,j,dist(list.get(i),list.get(j))));
			}
		}
		//sort
		Collections.sort(edgeList);
		double cost = 0;
		int index = 0;
		UnionFind uf = new UnionFind(n);
		while(index < edgeList.size()){
			Edge e = edgeList.get(index);
			//if $form and $to is not in the same set
			int fromSet = uf.FIND(e.from);
			int toSet   = uf.FIND(e.to);
			if(fromSet!=toSet){
				uf.UNION(e);
				cost += e.dist;
			}
			index++;
		}
		return cost;
	}
	class UnionFind{
		int n;
		int size;
		int[] parent;
		public UnionFind(int n){ 
			this.n = n;
			this.size = n;
			parent = new int[n];
			for(int i=0;i<n;i++) parent[i] = i;//make every vertex is not in same set
		}
		void UNION(Edge e){
			int fromSet = FIND(e.from);
			int toSet   = FIND(e.to);
			if(fromSet!=toSet) parent[toSet] = fromSet;
 		}
		int FIND(int node){
			if(parent[node]==node) return node;//parent is itself
			return parent[node] = FIND(parent[node]);
		}
	}
	double PRIM(){
		double cost = 0;
		boolean visited[] = new boolean[n];
		int count = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(0,0));
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			int v1 = cur.to;
			if(!visited[v1]){       
				visited[v1] = true; 
				count--;
				cost += cur.dist;               //work
				if(count==n) break;             //terminate
				for(int i=0;i<n;i++){
					if(!visited[i]){
						pq.add(new Edge(i, dist(list.get(cur.to),list.get(i))));
					}
				}
			}
		}
		return cost;
	}
	double dist(Node a, Node b){
		return Math.sqrt( Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}
	public class Edge implements Comparable<Edge>{
		int from;
		int to;
		double dist;
		public Edge(int from, int to, double d){
			this(to,d);
			this.from = from;
		}
		public Edge(int to, double d){
			dist = d;
			this.to = to;
		}
		public int compareTo(Edge another){
			return (this.dist - another.dist > 0)? 1 : -1;
		}
	}
	public class Node{
		double x;
		double y;
		public Node(String a,String b){
			x = Double.parseDouble(a);
			y = Double.parseDouble(b);
		}
	}
}

