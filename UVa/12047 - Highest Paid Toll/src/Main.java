import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	//12047 - Highest Paid Toll
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	int N, M, S, T, P;
	ArrayList<ArrayList<Edge>> path;
	ArrayList<ArrayList<Edge>> rev;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			N = readInt(); M = readInt(); S = readInt(); T = readInt(); P = readInt();
			path = new ArrayList<ArrayList<Edge>>();
			rev  = new ArrayList<ArrayList<Edge>>();
			for(int i=0;i<=N;i++) path.add(new ArrayList<Edge>());
			for(int i=0;i<=N;i++) rev.add(new ArrayList<Edge>());
			for(int i=0;i<M;i++){
				int f = readInt(), t = readInt(), w = readInt();
				path.get(f).add(new Edge(t, w));
				rev.get(t).add(new Edge(f, w));
			}
//			System.out.println( firstSolution() );
			System.out.println( secondSolution() );
		}
	}
	int secondSolution(){
		//forward
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add( new Node(S,0,0) );
		int distance[] = new int[N+1];
		for(int i=1;i<=N;i++) distance[i] = Integer.MAX_VALUE;
		distance[S] = 0;
		while(!pq.isEmpty()){
			Node v1 = pq.poll();
			for(Edge e : path.get(v1.to)){
				int alt;
				if(distance[e.to] > (alt = v1.totalToll + e.weight)){
					pq.add(new Node(e.to, 0, distance[e.to] = alt));
				}
			}
		}
		//reverse
		pq = new PriorityQueue<Node>();
		pq.add( new Node(T,0,0) );
		int revDist[] = new int[N+1];
		for(int i=1;i<=N;i++) revDist[i] = Integer.MAX_VALUE;
		revDist[T] = 0;
		while(!pq.isEmpty()){
			Node v1 = pq.poll();
			for(Edge e : rev.get(v1.to)){
				int alt;
				if(revDist[e.to] > (alt = v1.totalToll + e.weight)){
					pq.add(new Node(e.to, 0, revDist[e.to] = alt));
				}
			}
		}
		//loop each each
		int max = -1;
		for(int i=1;i<=N;i++){
			int dist = distance[i];
			for(Edge e : path.get(i)){
				int reverse = revDist[e.to];
				if(dist==Integer.MAX_VALUE || reverse==Integer.MAX_VALUE) continue;
				if( (dist + e.weight + reverse) <= P ) 
					max = Math.max(e.weight, max);
			}
		}
		return max;
	}
	int firstSolution(){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add( new Node(S,0,0) );
		int maxWeight[] = new int[N+1];
		for(int i=1;i<=N;i++) maxWeight[i] = -1;
		maxWeight[S] = 0;
		while(!pq.isEmpty()){
			Node v1 = pq.poll();
			for(Edge e : path.get(v1.to)){
				int max = Math.max(v1.maxWeight, e.weight);
				if(v1.totalToll + e.weight <= P && max > maxWeight[e.to]){
					pq.add(new Node(e.to, maxWeight[e.to] = max, v1.totalToll + e.weight));
				}
			}
		}
		return maxWeight[T];
	}
	public class Node implements Comparable <Node>{
		int to;
		int maxWeight;
		int totalToll;
		public Node(int t, int m, int tt){
			to = t;
			maxWeight = m;
			totalToll = tt;
		}
		public int compareTo(Node another){ return totalToll - another.totalToll; }
	}
	public class Edge{
		int to, weight;
		public Edge(int t, int w){
			to = t;
			weight = w;
		}
	}
}
