import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.border.EtchedBorder;

public class Main {
	int n;
	Node target;
	ArrayList<Node> set;
	boolean visited[];
	double distance[];
	int track[];
	double minimax;
	ArrayList<ArrayList<Edge>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			n = Integer.parseInt(bfr.readLine());
			if(n==0)break;
			set  = new ArrayList<Node>();
			track = new int[n];
			distance = new double[n];
			path = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<n;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				set.add(new Node(x,y));
				distance[i] = Double.MAX_VALUE;
			}
			/**************************************************************/
			MST();
			minimax = Double.MAX_VALUE;
			visited = new boolean[n];
			visited[0] = true;
			traversal(0, 0);
			System.out.println("Scenario #"+(c++));
			System.out.println("Frog Distance = "+new DecimalFormat("0.000").format(minimax));
			System.out.println();      //blank line between consecutive test case
			bfr.readLine();
		}
	}
	void traversal(double min,int index){
		if(minimax!=Double.MAX_VALUE) return;
		if(index==1) { minimax = min; return; }
		for(Edge e : path.get(index)){
			if(visited[e.tar]) continue;
			visited[e.tar] = true;
			double dist = e.dist;
			traversal( (dist < min? min : dist), e.tar);
		}
		
	}
	void MST(){	
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int i=0;i<n;i++) path.add(new ArrayList<Edge>());
		visited = new boolean[n];
		pq.add(new Edge(-1,0,0));
		int nLeft = n;
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			int next = cur.tar;
			if(visited[next]) continue;
			visited[next] = true;
			nLeft--;
			if(cur.src!=-1) { // add path
				path.get(cur.src).add(new Edge(cur.tar,cur.dist));
				path.get(cur.tar).add(new Edge(cur.src,cur.dist));
			}
			if(nLeft==0) break;//terminate
			
			for(int i=0;i<n;i++){
				if(!visited[i]){
					pq.add(new Edge(next,i,dist(set.get(next), set.get(i))));
				}
			}
		}
	}
	double dist(Node a, Node b){
		return Math.sqrt( Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	public class Edge implements Comparable<Edge>{
		int src, tar;
		double dist;
		public Edge(int s, int t, double d){
			this(t,d);
			src  = s;
		}
		public Edge(int t, double d){
			tar  = t;
			dist = d;
		}
		public int compareTo(Edge another){
			return (this.dist - another.dist) > 0? 1 : -1;
		}
	}
	public class Node {
		int x, y;
		Node(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
}
