import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	//11060 - Beverages
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	int N, M;
	int[] inDegree;
	ArrayList<String> list;
	ArrayList<ArrayList<Integer>> path;
	boolean visited[];
	StringBuilder sb;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		sb = new StringBuilder();
		int c = 1;
		while(bfr.ready()){
			N = readInt();
			inDegree = new int[N];
			visited = new boolean[N];
			list = new ArrayList<String>();
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<N;i++) { list.add( getString() ); path.add(new ArrayList<Integer>()); }
			
			M = readInt();
			for(int i=0;i<M;i++){				
				int from = list.indexOf( getString() );
				int to   = list.indexOf( getString() );
				path.get(from).add(to);
				inDegree[to]++;
			}
			bfr.readLine();
			sb.append("Case #"+(c++)+": Dilbert should drink beverages in this order:");
			print();
			sb.append(".\n\n");
		}
		bfw.write(""+sb);
		bfw.flush();
	}
	void print(){
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0;i<N;i++)
			if(inDegree[i]==0) pq.add(i);
		while(!pq.isEmpty()){
			int cur = pq.poll();
			visited[cur] = true;
			sb.append(" "+list.get(cur));
			for(Integer c : path.get(cur)){
				inDegree[c]--;
				if(!visited[c] && inDegree[c]==0){
					pq.add(c);
				}
			}
			
		}
	}
}
