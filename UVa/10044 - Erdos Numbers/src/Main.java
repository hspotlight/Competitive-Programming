import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//10044 - Erdos Numbers
	ArrayList<ArrayList<Integer>> path;
	HashMap<String, Integer> map;
	int[] distance;
	int index;
	int getIndex(String s){
		if(map.containsKey(s)) return map.get(s);
		map.put(s, index); path.add(new ArrayList<Integer>());
		return index++;
	}
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c=1;c<=tc;c++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			path = new ArrayList<ArrayList<Integer>>();
			map = new HashMap<String, Integer>();
			index = 0;
			//paper
			for(int i=0;i<p;i++){
				String s = bfr.readLine();
				s = s.substring(0, s.indexOf(':'));
				st = new StringTokenizer(s, ", ");
				int author[] = new int[ (st.countTokens()+1)/2 ];
				int id = 0;
				while(st.hasMoreTokens()){
					String name = st.nextToken()+", "+st.nextToken();
					author[id++] = getIndex(name);
				}
				//author = [1, 0, 2];
				for(int j=0;j<author.length;j++){
					for(int k=0;k<j;k++){
						path.get(author[j]).add(author[k]);
						path.get(author[k]).add(author[j]);
					}
				}
			}
			distance = new int[index];
			for(int i=0;i<index;i++) distance[i] = Integer.MAX_VALUE;
			if(map.containsKey("Erdos, P.")) bfs(map.get("Erdos, P."));
			System.out.println("Scenario "+c);
			//query
			for(int i=0;i<n;i++){
				String s = bfr.readLine();
				if(map.containsKey(s)){
					int id = map.get(s);
					String ErdosNum = distance[id]==Integer.MAX_VALUE?" infinity": " "+distance[id];
					System.out.println(s+ErdosNum);
				}
				else System.out.println(s+" infinity");
			}
		}
	}
	void bfs(int key){
		Queue<int[]> qe = new LinkedList<int[]>();
		int node[] = new int[2];
		node[0] = key; distance[key] = 0;
		qe.add(node); 
		while(!qe.isEmpty()){
			int[] u = qe.poll();
			for(int v : path.get(u[0])){
				if(distance[v]==Integer.MAX_VALUE){
					int[] next = new int[2];
					next[0] = v; next[1] = u[1]+1;
					distance[v] = next[1];
					qe.add(next);
				}
			}
		}
	}
}
