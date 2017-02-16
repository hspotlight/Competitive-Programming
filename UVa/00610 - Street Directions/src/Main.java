import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	int N, M, cnt;
	int[] num, low, parent;
	ArrayList<Integer>[] edgeList;
	ArrayList<String> bridges;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int) stk.nval;}
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = 1;
		while((N=readInt())!=0 && (M=readInt())!=0){
			
			bridges = new ArrayList<String>();
			edgeList = new ArrayList[N+1];
			for(int i=0;i<=N;i++) edgeList[i] = new ArrayList<Integer>();
			for(int i=0;i<M;i++){
				int u = readInt(), v = readInt();
				edgeList[u].add(v);
				edgeList[v].add(u);
			}
			
			cnt = 0;
			num = new int[N+1]; low = new int[N+1]; parent = new int[N+1];
			for(int i=1;i<=N;i++){
				if(num[i]==0){
					trajan(i);
				}
			}
			
			bfw.write(tc+++"\n\n");
			for(String edge : bridges)
				bfw.write(edge+"\n");
			bfw.write("#\n");
		}
		bfw.flush();
	}
	void trajan(int u){
		num[u] = low[u] = ++cnt;
		for(int v: edgeList[u]){
			if(num[v]==0){ //tree edge
				parent[v] = u;
				trajan(v);
				low[u] = Math.min(low[u], low[v]);
				//bridge
				if(num[v] == low[v]) bridges.add(u+" "+v);
				bridges.add(v+" "+u);
			}
			else if (parent[u] != v){ //backward edge 
				low[u] = Math.min(low[u], num[v]);
				if(num[v]<num[u])bridges.add(v+" "+u);
			}
		}
	}
}
