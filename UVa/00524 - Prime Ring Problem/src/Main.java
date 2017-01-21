import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	//00524 - Prime Ring Problem
	boolean visited[];
	int n;
	ArrayList<ArrayList<Integer>> path;
	StringBuilder sb;
	int prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			n = Integer.parseInt(bfr.readLine());
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<=n;i++) path.add(new ArrayList<Integer>());
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					for(int p : prime){
						if(i+j==p) {
							path.get(i).add(j);
							break;
						}
					}
				}
			}
			System.out.println("Case "+c+":");
			visited = new boolean[n+1];
			visited[1] = true;
			recursive(1,"1",1);
			c++;
			if(bfr.ready()) System.out.println();
		}
	}
	void recursive(int u, String p, int len){
		if(len==n && path.get(u).get(0)==1){ System.out.println(p); return; }
		visited[u] = true;
		for(int v : path.get(u)){
			if(!visited[v]){
				recursive(v,p+" "+v,len+1);
			}
		}
		visited[u] = false;
	}
}
