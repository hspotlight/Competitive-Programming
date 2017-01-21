import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//00924 - Spreading The News
	int N;
	int freq[];
	StreamTokenizer stk;
	ArrayList<Integer>[] map;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		N = readInt();
		map = new ArrayList[N];
		for(int i=0;i<N;i++) map[i] = new ArrayList<Integer>();
		for(int i=0;i<N;i++){
			int m = readInt();
			for(int k=0;k<m;k++){
				int j = readInt();
				map[i].add(j);
			}
		}
		int tc = readInt();
		while(tc-->0){
			int i = readInt();
			int pos = BFS(i);
			if(pos==-1) bfw.write("0\n");
			else bfw.write(freq[pos]+" "+pos+"\n");
		}
		bfw.close();
	}
	int BFS(int start){
		Queue<Integer> qe = new LinkedList<Integer>();
		int dist[] = new int[N];
		for(int i=0;i<N;i++) dist[i] = Integer.MAX_VALUE;
		qe.add(start); dist[start] = 0;
		while(!qe.isEmpty()){
			int u = qe.poll();
			for(int v : map[u]){
				if(dist[v]==Integer.MAX_VALUE){
					qe.add(v); dist[v] = dist[u] + 1;
				}
			}
		}
		freq = new int[N];
		for(int i=0;i<N;i++){
			if(dist[i]!=Integer.MAX_VALUE){
				freq[ dist[i] ]++;
			}
		}
		int max = 0;
		int pos = -1;
		for(int i=1;i<N;i++){
			if(freq[i]>max){
				max = freq[i];
				pos = i;
			}
		}
		return pos;
	}
}