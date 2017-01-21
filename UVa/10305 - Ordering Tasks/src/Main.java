import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//10305 - Ordering Tasks
	//BFS-inDegree-update
	int n, m;
	int inDegree[];
	boolean path[][];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if(n==0 && m==0) break;
			inDegree = new int [n+1]; // 1 to n
			path = new boolean[n+1][n+1];
			for(int i=0;i<m;i++){
				st = new StringTokenizer(bfr.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				path[a][b] = true;
				inDegree[b]++;
			}
			//traversal
			traversal();
		}
	}
	void traversal(){
		Queue<Integer> qe = new LinkedList<Integer>();
		for(int i=1;i<=n;i++){
			if(inDegree[i]==0) qe.add(i);
		}
		boolean first = true;
		while(!qe.isEmpty()){
			int cur = qe.poll();
			inDegree[cur] = -1; //visited
			System.out.print((first? "": " ") + cur);
			first = false;
			for(int i=1;i<=n;i++){
				if(path[cur][i]){
					path[cur][i] = false;
					inDegree[i]--;
					if(inDegree[i]==0) qe.add(i);
				}
			}
		}
		System.out.println();
	}
}
