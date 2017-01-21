import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//00908 - Re-connecting Computer Sites
	int n, k, m;
	ArrayList<Edge> nList, kList, mList;
	StringTokenizer st;
	int readInt(){ return Integer.parseInt(st.nextToken()); }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			//N
			nList = new ArrayList<Edge>();
			kList = new ArrayList<Edge>();
			mList = new ArrayList<Edge>();
			n = Integer.parseInt(bfr.readLine());
			for(int i=0;i<n-1;i++){
				st = new StringTokenizer(bfr.readLine());
				int s = readInt(), d = readInt(), w = readInt();
				mList.add(new Edge(s,d,w));
			}
			//K
			k = Integer.parseInt(bfr.readLine());
			for(int i=0;i<k;i++){
				st = new StringTokenizer(bfr.readLine());
				int s = readInt(), d = readInt(), w = readInt();
				kList.add(new Edge(s,d,w));
			}
			//M
			m = Integer.parseInt(bfr.readLine());
			for(int i=0;i<m;i++){
				st = new StringTokenizer(bfr.readLine());
				int s = readInt(), d = readInt(), w = readInt();
				mList.add(new Edge(s,d,w));
			}
			//KRUSKAL
			KRUSKAL(' '); KRUSKAL('K');
			if(bfr.ready()) { bfr.readLine(); System.out.println(); }
		}
	}
	void KRUSKAL(char op){
		@SuppressWarnings("unchecked")
		ArrayList<Edge> list = (ArrayList<Edge>)mList.clone();
		if(op=='K'){ for(Edge e : kList) list.add(e); }
		Collections.sort(list);
		int count = 0, index = 0;
		int cost = 0;
		UnionFind uf = new UnionFind(list.size());
		while(count < n-1){
			Edge e = list.get(index++);
			if(uf.UNION(e)){
				cost += e.weight;
				count++;
			}
		}
		System.out.println(cost);
	}
	public class UnionFind{
		int n;
		int[] parent, rank;
		public UnionFind(int n){
			this.n = n;
			parent = new int[n];
			rank = new int[n];
			for(int i=0;i<n;i++) parent[i] = i;
		}
		public boolean UNION(Edge e){
			int fromSet = FIND(e.from);
			int toSet = FIND(e.to);
			if(fromSet==toSet) return false;
			if(rank[fromSet] < rank[toSet]) parent[fromSet] = toSet; else parent[toSet] = fromSet;
			if(rank[fromSet]==rank[toSet]) rank[fromSet]++;
			return true;
		}
		public int FIND(int node){
			if(parent[node] == node) return node;
			return parent[node] = FIND(parent[node]);
		}
	}
	public class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public int compareTo(Edge another){
			return (this.weight - another.weight);
		}
	}
}
