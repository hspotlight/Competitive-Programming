import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//00627 - The Net
	int N, M;
	ArrayList<Integer> list[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(bfr.ready()){
			N = Integer.parseInt(bfr.readLine());
			list = new ArrayList[N+1];
			StringTokenizer st;
			for(int i=1;i<=N;i++) list[i] = new ArrayList<Integer>();
			for(int i=1;i<=N;i++){
				st = new StringTokenizer(bfr.readLine(),"-");
				int routerID = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens()){
					st = new StringTokenizer(st.nextToken(),",");
					while(st.hasMoreTokens()){
						int next = Integer.parseInt(st.nextToken());
						list[routerID].add(next);
					}
				}
				Collections.sort(list[routerID]);
			}
			/**********************************************************/
			System.out.println("-----");
			M = Integer.parseInt(bfr.readLine());
			for(int i=0;i<M;i++){
				st = new StringTokenizer(bfr.readLine());
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				String path = BFS(src, dst);
				System.out.println(path.isEmpty()?"connection impossible":path);
			}
		}
	}
	String BFS(int src, int dst){
		int parent[] = new int[N+1];
		Arrays.fill(parent, -1);
		Queue<Integer> qe = new LinkedList<Integer>();
		qe.add(src); parent[src] = 0;//root
		while(!qe.isEmpty()){
			int u = qe.poll();
			for(int v : list[u]){
				if(parent[v]==-1){
					parent[v] = u;
					if(v==dst) return getPath(parent,dst);
					qe.add(v);
				}
			}
		}
		return "";
	}
	String getPath(int parent[],int dst){
		ArrayList<Integer> path = new ArrayList<Integer>();
		int cur = dst;
		do{
			path.add(cur);
			cur = parent[cur];
		}while(cur!=0);
		Collections.reverse(path);
		return path.toString().replace("[", "").replace("]", "").replace(",", "");
	}
}