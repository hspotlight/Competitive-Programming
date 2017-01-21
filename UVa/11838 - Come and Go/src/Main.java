import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	//11838 - Come and Go
	StreamTokenizer stk;
	int n, m, count, SCC;
	int[] num, low;
	ArrayList<Integer>[] adjList;
	Stack<Integer> stack;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	@SuppressWarnings("unchecked")
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(true){
			n = readInt(); m = readInt();
			if(n==0 && m==0) break;
			adjList = new ArrayList[n+1];
			for(int i=0;i<=n;i++) adjList[i] = new ArrayList<Integer>();
			for(int i=0;i<m;i++){
				int f = readInt(), t = readInt(), p = readInt();
				adjList[f].add(t);
				if(p==2) adjList[t].add(f);
			}
			num = new int[n+1]; low = new int[n+1];
			count = 0; SCC = 0;
			stack = new Stack<Integer>();
			for(int i=1;i<n+1;i++){
				if(num[i]==0) {
					tarjan(i);
				}
			}
			System.out.println(SCC==1?1:0);
		}
	}
	void tarjan(int u){
		stack.push(u);
		num[u] = low[u] = ++count;
		for(int v : adjList[u]){
			if(num[v]==0){
				tarjan(v);
				low[u] = Math.min(low[u],low[v]);
			}
			else if (stack.contains(v)) low[u] = Math.min(low[u], num[v]);
		}
		if(num[u]==low[u]){
			int v = stack.pop();
			while(u!=v) v = stack.pop();
			SCC++;
		}
	}
}
