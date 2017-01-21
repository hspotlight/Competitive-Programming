import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//10099 - The Tourist Guide
	int N,R,S,T,P;
	int parent[], minimum[];
	ArrayList<ArrayList<Road>> path;
	ArrayList<Road> road;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		while(bfr.ready()){
			N = readInt(); R = readInt();
			if(N==0 && R==0) break;
			//initialize
			road = new ArrayList<Road>();
			for(int i=0;i<R;i++){
				int f = readInt(), t = readInt(), w = readInt();
				road.add(new Road(f,t,w));
			}
			parent = new int[N+1];
			for(int i=1;i<=N;i++) parent[i] = i;
			kruskal();
			minimum = new int[N+1];
			for(int i=1;i<=N;i++) minimum[i] = Integer.MAX_VALUE;
			S = readInt(); T = readInt(); P = readInt();
			traversal(S,Integer.MAX_VALUE);;
			minimum[S] = 0; //itself
			int nPersonPerTrips = minimum[T] - 1;// 1 for guide
			int nTrips = (int)(Math.ceil((P*1.0)/nPersonPerTrips));
			System.out.println("Scenario #"+c);
			System.out.println("Minimum Number of Trips = "+nTrips);
			System.out.println();
			c++;
		}
	}
	public void traversal(int u, int min){
		minimum[u] = min;
		for(Road r : path.get(u)){
			int v = r.to;
			if(minimum[v]==Integer.MAX_VALUE && v!=S){
				traversal(v, Math.min(min, r.weight));
			}
		}
	}
	public void kruskal(){
		path = new ArrayList<ArrayList<Road>>();
		for(int i=0;i<=N;i++) path.add(new ArrayList<Road>());
		Collections.sort(road);
		int index = 0, count = 0;
		while(index < road.size() && count < N-1){
			Road r = road.get(index);
			if(union(r)){
				path.get(r.from).add(new Road(r.from,r.to,r.weight));
				path.get(r.to).add(new Road(r.to,r.from,r.weight));
				count++;
			}
			index++;
		}
	}
	public boolean union(Road r){
		int setFr = find(r.from);
		int setTo = find(r.to);
		if(setFr==setTo) return false;
		parent[setTo] = setFr;
		return true;
	}
	public int find(int node){
		return parent[node] == node ? node : (parent[node] = find(parent[node]));
	}
	public class Road implements Comparable<Road>{
		int from;
		int to;
		int weight;
		public Road(int f, int t, int w){
			from = f;
			to = t;
			weight = w;
		}
		public int compareTo(Road another){
			return -(this.weight - another.weight);
		}
		public String toString(){
			return "("+to+", "+weight+")";
		}
	}
}
