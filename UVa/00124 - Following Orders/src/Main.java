import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//00124 - Following Orders
	ArrayList<Character> list;
	int[] count;
	boolean[][] path;
	boolean[] visited;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			//first line: vertex
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			list = new ArrayList<Character>();
			while(st.hasMoreTokens()){
				list.add(st.nextToken().charAt(0));
			}
			path = new boolean[list.size()][list.size()];
			count = new int[list.size()];
			visited = new boolean[list.size()];
			Collections.sort(list);//sort alphabet (NO NEED?)
			//second line: path
			st = new StringTokenizer(bfr.readLine());
			while(st.hasMoreTokens()){
				char s = st.nextToken().charAt(0);
				char d = st.nextToken().charAt(0);
				int indexS = list.indexOf(s);
				int indexD = list.indexOf(d);
				count[indexD]++;
				path[indexS][indexD] = true;
			}
			
			/*end of input part*/
			//'count' the number of incoming edge
			for(int i=0;i<list.size();i++){
				if(count[i]==0){//no incoming edge (can be starter)
					traversal(i,""+list.get(i));
				}
			}
			if(bfr.ready()) System.out.println();
		}
	}
	void traversal(int index,String p){
		if(p.length()==list.size()) System.out.println(p);
		else{
			//do task and update
			visited[index] = true;
			for(int i=0;i<path[index].length;i++){
				if(path[index][i]) count[i]--; 
			}
			//find next vertex to go
			for(int i=0;i<list.size();i++){
				if(count[i]==0 && !visited[i]){
					traversal(i, p + list.get(i));
				}
			}
			//return task and update
			for(int i=0;i<path[index].length;i++){
				if(path[index][i]) count[i]++; 
			}
			visited[index] = false;
		}
	}
}
