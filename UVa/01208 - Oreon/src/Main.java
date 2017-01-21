import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//01208 - Oreon
	int N, parent[];
	ArrayList<Edge> list;
	ArrayList<Link> link;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c = 1; c<=tc;c++){
			N = Integer.parseInt(bfr.readLine());
			list = new ArrayList<Edge>();
			for(int i=0;i<N;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine().replaceAll(",", ""));
				for(int j=0;j<N;j++){
					int w = Integer.parseInt(st.nextToken());
					if(i<=j) continue;
					else if(w!=0) list.add(new Edge(i,j,w));
				}
			}
			kruksal();
			Collections.sort(link);
			System.out.println("Case "+c+":");
			for(Link l : link){
				System.out.println(l);
			}
		}
	}
	void kruksal(){
		Collections.sort(list);
		parent = new int[N];
		for(int i=0;i<N;i++) parent[i] = i;
		link = new ArrayList<Link>();
		int count, index;
		count = index = 0;
		while(index < list.size() && count < N-1){
			Edge e = list.get(index);
			if(union(e)){
				count++;
				link.add(new Link(e));
			}
			index++;
		}
	}
	boolean union(Edge e){
		int setFr = find(e.from);
		int setTo = find(e.to);
		if(setFr==setTo) return false;
		parent[setTo] = setFr;
		return true;
	}
	public int find(int node){
		return parent[node]==node? node: (parent[node] = find(parent[node]));
	}
	class Link implements Comparable<Link>{
		String s;
		int weight;
		public Link(Edge e){
			if(e.from<e.to) s = ""+(char)(e.from+'A')+"-"+(char)(e.to+'A');
			else s = ""+(char)(e.to+'A')+"-"+(char)(e.from+'A');
			weight = e.weight;
		}
		public int compareTo(Link l) {
			if(weight != l.weight) return weight - l.weight;
			return (s).compareTo(l.s); 
		}
		public String toString(){ return s+" "+weight; }
	}
	class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from,int to,int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public int compareTo(Edge e){ return weight - e.weight; }
	}
}
