import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//10004 - Bicoloring
	final int RED = 1;
	final int BLUE = 2;
	int N, M;
	ArrayList<Integer> list[];
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(true){
			N = readInt();
			if(N==0) break;
			M = readInt();
			list = new ArrayList[N];
			for(int i=0;i<N;i++) list[i] = new ArrayList<Integer>();
			for(int i=0;i<M;i++){
				int u = readInt(), v = readInt();
				list[u].add(v); list[v].add(u);
			}
			System.out.println( isBipartite() ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}
	}
	boolean isBipartite(){
		int color[] = new int[N];
		for(int i=0;i<N;i++){
			if(color[i]==0 && !bipartiteCheck(color,i)) return false;
		}
		return true;
	}
	boolean bipartiteCheck(int color[], int start){//check sub-graph
		Queue<Integer> qe = new LinkedList<Integer>();
		qe.add(start);
		color[start] = RED;
		while(!qe.isEmpty()){
			int u = qe.poll();
			for(int v : list[u]){
				if(color[v]==0){
					color[v] = (color[u]==RED) ? BLUE: RED;
					qe.add(v);
				}
				else if (color[u] == color[v]) return false;
			}
		}
		return true;
	}
}