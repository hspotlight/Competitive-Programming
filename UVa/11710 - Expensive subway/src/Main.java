import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	//11710 - Expensive subway
	//Kruskal 0.570
	int N, M, parent[];
	StringTokenizer st;
	Map<String, Integer> stationDict = new HashMap<String, Integer>();
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			st = new StringTokenizer(bfr.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			
			stationDict.clear();
			for(int i=0;i<N;i++){
				String station = bfr.readLine();
				stationDict.put(station, i);
			}
			
			//add Edge to EdgeList
			edgeList.clear();
			for(int i=0;i<M;i++){
				st = new StringTokenizer(bfr.readLine());
				int u = stationDict.get(st.nextToken());
				int v = stationDict.get(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(u,v,w));
			}
			
			bfr.readLine();
			int min = kruskal();
			System.out.println(min!=-1?min:"Impossible");
		}
	}
	int kruskal(){
		parent = new int[N];
		for(int i=0;i<N;i++) parent[i] = i;
		Collections.sort(edgeList);
		int edgeCounter = 0, cost = 0;
		for(Edge edge : edgeList){
			if(union(edge)){
				cost += edge.weight;
				edgeCounter++;
			}
		}
		return edgeCounter == N-1? cost : -1;
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr==setTo) return false;
		parent[setTo] = setFr;
		return true;
	}
	int find(int node){
		return parent[node]==node?node: (parent[node]=find(parent[node]));
	}
	public class Edge implements Comparable<Edge>{
		int from, to, weight;
		public Edge(int f, int t, int w){
			from = f;
			to = t;
			weight = w;
		}
		public int compareTo(Edge e){return weight - e.weight; }
	}
}
