import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//00208 - Firetruck
	int fire;
	ArrayList<ArrayList<Integer>> path;
	boolean visited[];
	boolean reachable;
	int count;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			fire = Integer.parseInt(bfr.readLine());
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<=21;i++) path.add(new ArrayList<Integer>());
			visited = new boolean[22];
			count = 0;
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			while(!(x==0&&y==0) ){
				path.get(x).add(y);
				path.get(y).add(x);
				st = new StringTokenizer(bfr.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
			}
			for(int i=1;i<=21;i++) Collections.sort(path.get(i));
			
			System.out.println("CASE "+c+":");
			for(int i=1;i<=21;i++) visited[i] = false;
			reachable = false;
			
			traversal(1);
			
			for(int i=1;i<=21;i++) visited[i] = false;
			visited[1] = true;
			
			if(reachable) DFS(1,"");
			System.out.println("There are "+count+" routes from the firestation to streetcorner "+fire+".");
			c++;
		}
	}
	void traversal(int i){
		if(i==fire) reachable = true;
		if(reachable) return;
		if(!visited[i]){
			visited[i] = true;
			for(Integer c : path.get(i)){
				traversal(c);
			}
			visited[i] = false;
		}
	}
	void DFS(int i,String s){
		if(i==fire) { System.out.println(s+" "+i); count++;}
		else{
			for(Integer c : path.get(i)){
				if(!visited[c]){
					visited[c] = true;
					DFS(c,s.equals("")? ""+i :s+" "+i);
					visited[c] = false;
				}
			}
		}
	}
}
