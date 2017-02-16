import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	//10510 - Cactus
	int N, M;
	int num[], low[];
	int counter, countComponent;
	
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Edge> eStack = new Stack<Edge>();
	ArrayList<Edge>[] edgeList;
	
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		new Main().run();
	}
	void run() throws Exception{
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int tc = readInt();
		while(tc-->0){
			N = readInt(); M = readInt();
			
			edgeList = new ArrayList[N];
			for(int i=0;i<N;i++) edgeList[i] = new ArrayList<Edge>();
			
			for(int i=0;i<M;i++){
				int u = readInt(), v = readInt();
				edgeList[u].add(new Edge(u, v));
			}
			
			if( isSCC() && isCactus() ) System.out.println("YES");
			else System.out.println("NO");
		}
	}
	
	boolean isSCC(){
		num = new int[N]; low = new int[N];
		counter = countComponent = 0;
		stack.clear();
		
		for(int i=0;i<N;i++){
			if(num[i]==0) tarjan(i);
		}
		
		return countComponent == 1 ? true : false;
	}
	
	void tarjan(int u){
		stack.push(u);
		num[u] = low[u] = ++counter;
		
		for(Edge e : edgeList[u]){
			int v = e.to;
			if(num[v] == 0){
				tarjan(v);
				low[u] = Math.min(low[u], low[v]);
			}
			else if (stack.contains(v)) low[u] = Math.min(low[u], num[v]);
		}
		
		if(num[u]==low[u]){
			int v = stack.pop();
			while(u!=v) v = stack.pop();
			countComponent++;
		}
	}
	
	boolean isCactus(){
		counter = 0;
		eStack.clear();
		num = new int[N]; low = new int[N];
		
		for(int i=0;i<N;i++){
			if(num[i] == 0){
				boolean isNotCactus = DFS(i);
				if(isNotCactus) return false;
			}
		}
		
		return true;
	}
	
	boolean DFS(int u) {
		boolean child = false;
		num[u] = low[u] = ++counter;
		
		for(Edge edge : edgeList[u]){
			if(!edge.used){
				int v = edge.to;
				if(num[v] == 0){
					eStack.push(edge);
					child |= DFS(v);
				}
				else{ //found a cycle
					edge.used = true;
					int target = edge.to;
					Edge e;
					do{
						if(eStack.isEmpty()) return true;
						e = eStack.pop();
					}
					while(target != e.from);
				}
			}
		}
		
		return child;
	}
	
	public class Edge{
		int from, to;
		boolean used;
		public Edge(int f, int t){
			from = f;
			to = t;
			used = false;
		}
	}
}
