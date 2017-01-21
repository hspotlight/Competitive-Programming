import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//10505 - Montesco vs Capuleto
	ArrayList<ArrayList<Integer>> enemy;
	boolean visited[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		StringTokenizer st;
		while(tc-->0){
			bfr.readLine();
			int n = Integer.parseInt(bfr.readLine());
			visited = new boolean[n];
			enemy = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<n;i++) enemy.add(new ArrayList<Integer>());
			
			for(int i=0;i<n;i++){
				st = new StringTokenizer(bfr.readLine());
				int x = Integer.parseInt(st.nextToken());
				for(int j=0;j<x;j++){
					int k = Integer.parseInt(st.nextToken());
					if(1<=k&&k<=n){
						enemy.get(i).add(k-1);
						enemy.get(k-1).add(i);
					}
				}
			}
			int count = 0;
			for(int i=0;i<n;i++){
				if(!visited[i])
					count += BFS(i);
			}
			System.out.println(count);
		}
	}
	int BFS(int start){
		int max = -1;
		ArrayList<Integer> red = new ArrayList<Integer>();
		ArrayList<Integer> blue = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		red.add(start);
		while(!q.isEmpty()){
			int now = q.poll();
			boolean r = red.contains(now)? true: false;
			for(Integer c : enemy.get(now)){
				if(!visited[c]){
					visited[c] = true;
					q.add(c);
					if(r) blue.add(c);
					else red.add(c);
				}
				else{
					boolean R = red.contains(c)? true: false;
					if(r&&R) max = 0;//both are red
					else if (!r&&!R) max = 0;//both are blue
				}
			}
		}
		if(max==0) return 0;
		return Math.max(red.size(), blue.size());
	}
}
