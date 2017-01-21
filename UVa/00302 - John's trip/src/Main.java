import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//00302 - John's trip
	StreamTokenizer stk;
	ArrayList<Edge>[] eList;
	int degree[], cnt, n = 1995, m = 45;
	boolean[] visited;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	@SuppressWarnings("unchecked")
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int x,y,z;
		while(true){//each test-case
			x = readInt(); y = readInt();
			if(x==0 && y==0) break;
			z = readInt();
			/******************************************************************/
			//Vertex
			eList = new ArrayList[m]; degree = new int[m];
			//Edge
			visited = new boolean[n];
			int start = Math.min(x, y);
			for(int i=0;i<eList.length;i++) eList[i] = new ArrayList<Edge>();
			do{//each street
				cnt++;
				eList[x].add(new Edge(x,y,z)); degree[x]++;
				eList[y].add(new Edge(y,x,z)); degree[y]++;
				//END
				x = readInt(); y = readInt();
				if(x==0 && y==0) break;
				z = readInt();
			}
			while(true);
			for(int i=0;i<eList.length;i++) Collections.sort(eList[i]);//sorted by street #
			/******************************************************************/
			if(!isCircuit()) System.out.println("Round trip does not exist.");
			else EulerCircuit(start);
			System.out.println();
		}
	}
	void EulerCircuit(int u){
		ArrayList<Edge> list = new ArrayList<Edge>(); //list of edge
		int size = 0;
		list = DFS(u);
		while(size<cnt){
			//start at v' return at v'
			int v = -1, pos = -1;
			for(int i=list.size()-1;i>=0;i--){
				if(degree[ list.get(i).to ]!=0){
					v = list.get(i).to; pos = i;
					break;
				}
			}
			if(v==-1) break;
			//add path' to path
			ArrayList<Edge> pDash = DFS(v);
			for(int i=pDash.size()-1;i>=0;i--) 
				list.add(pos+1, pDash.get(i));
		}
		//output
		String s = "";
		for(int i=0;i<list.size();i++)
			s += (s.isEmpty()?"":" ")+list.get(i).street;
		System.out.println(s);
	}
	ArrayList<Edge> DFS(int start){
		ArrayList<Edge> path = new ArrayList<Edge>();
		int u = start;
		while(true){
			for(Edge e : eList[u]){
				if(!visited[e.street]){
					visited[e.street] = true;
					degree[e.from]--; degree[e.to]--;
					path.add(e);
					u = e.to;
					break;
				}
			}
			if(path.get(path.size()-1).to == start && degree[u]==0) break;
		}
		return path;
	}
	boolean isCircuit(){
		for(int i=0;i<m;i++)
			if( (degree[i]&1) == 1) return false;
		return true;
	}
	public class Edge implements Comparable<Edge>{
		int from, to;
		int street;
		public Edge(int x, int y, int z){
			this.from = x;
			this.to   = y;
			street    = z;
		}
		public int compareTo(Edge v){ return street - v.street; }
	}
}