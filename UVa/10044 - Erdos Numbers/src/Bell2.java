import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bell2 {
	//10044 - Erdos Numbers
	int num[];
	Map<String,Integer> map;
	ArrayList<ArrayList<Integer>> adjList;
	public static void main(String[]args)throws IOException{new Bell2().run();}
	void run() throws IOException{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line;
		int nCases = Integer.parseInt(bfr.readLine());
		for(int n=1;n<=nCases;n++){
			while((line = bfr.readLine()).equals(""));
			StringTokenizer stk = new StringTokenizer(line);
			int P = Integer.parseInt(stk.nextToken());
			int N = Integer.parseInt(stk.nextToken());
			
			int nNodes = 0;
			adjList = new ArrayList<ArrayList<Integer>>();
			map = new HashMap<String,Integer>();
			
			
			for(int i=0;i<P;i++){
				while((line = bfr.readLine()).equals(""));
				ArrayList<String> authors = new ArrayList<String>();
				int cnt=0,k=0;
				stk =  new StringTokenizer(line,":");
				stk = new StringTokenizer(stk.nextToken(),", ");
				while(stk.hasMoreTokens())
					authors.add((stk.nextToken()+", "+stk.nextToken()));
				
				for(String a: authors)
					if(!map.containsKey(a)){
						map.put(a,nNodes++);
						adjList.add(new ArrayList<Integer>());
					}
			
				int len = authors.size();
				for(int j=0;j<len;j++){
					int u = map.get(authors.get(j));
					for(k=0;k<len;k++){
						if(j==k)continue;
						int v = map.get(authors.get(k));
		//				System.out.println(u+" "+v+" "+i);
						adjList.get(u).add(v);
					}	
				}
			}
			bfw.write("Scenario "+n+"\n");
			if(map.containsKey("Erdos, P.")){
				bfs();
				for(int i=0;i<N;i++){
					while((line = bfr.readLine()).equals(""));
					if(map.containsKey(line)){
						int erdosnum = num[map.get(line)];
						String ans = erdosnum==0 ? "infinity": ""+erdosnum;
						bfw.write(line+" "+ans+"\n");
					}
					else bfw.write(line+" infinity\n");
				}
			}else{
				for(int i=0;i<N;i++){
					while((line = bfr.readLine()).equals(""));
					bfw.write(line+" infinity\n");
				}
			}
			
		}
		bfw.close();
	}
	
	void bfs(){
		int n = map.size();
		boolean visited[] = new boolean[n];
		num = new int[n];
		
		Queue<Integer> qe = new LinkedList<Integer>();
		int root = map.get("Erdos, P.");
		qe.add(root);
		visited[root] = true;
		while(!qe.isEmpty()){
			int u = qe.poll();
			for(int v:adjList.get(u)){
				if(!visited[v]){
					visited[v] = true;
					num[v] = num[u]+1;
					qe.add(v);
				}
			}
		}
	}
}
