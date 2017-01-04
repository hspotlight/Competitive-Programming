import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//744A - Hongcow Builds A Nation
	int N, M, K, k;
	ArrayList<Integer> countries = new ArrayList<Integer>();
	ArrayList<Integer>[] adjList;
	int[] nNodes;
	int[] nEdges;
	boolean[] visited;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int) stk.nval; }
	public static void main(String[] arga) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		N = readInt();	M = readInt();	K = readInt();
		
		nNodes = new int[K+1];		nEdges = new int[K+1];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i=0;i<K;i++){
			countries.add( readInt() );
		}
		
		for(int i=0;i<M;i++){
			int u = readInt(), v = readInt();
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		k = 1;
		int max = -1, largest = 0;
		for(int root : countries){
			
			DFS(root);
			if(nNodes[k] > max){
				max = nNodes[k];
				largest = k;
			}
			k++;
		}
		
		int cnt = 0, already = 0;
		for(int i=1;i<N+1;i++){
			if(!visited[i]){
				already += adjList[i].size();
				cnt++;
			}
		}
		
		nNodes[largest] += cnt;
		int sum = 0;
		for(int i=1;i<K+1;i++){
			int n = nNodes[i];
			int edges = n*(n-1) - nEdges[i];
			sum += (edges>>1);
		}
		System.out.println(sum - (already>>1));
		
	}
	void DFS(int u){
		if(!visited[u]){
			visited[u] = true;
			for(int v : adjList[u]){
				DFS(v);
			}
			nEdges[k] += adjList[u].size();
			nNodes[k] += 1;
		}
	}
}	
