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

public class Bell {
	//10044 - Erdos Numbers
	int num[];
	Map<String,Integer> map;
	ArrayList<ArrayList<Integer>> adjList;
	public static void main(String[]args)throws IOException{new Bell().run();}
	void run() throws IOException{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line;
		int nCases = Integer.parseInt(bfr.readLine().trim());
		for(int n=1;n<=nCases;n++){
			line = bfr.readLine();
			StringTokenizer stk = new StringTokenizer(line);
			int P = Integer.parseInt(stk.nextToken());
			int N = Integer.parseInt(stk.nextToken());
			
			int nNodes = 0;
			adjList = new ArrayList<ArrayList<Integer>>();
			map = new HashMap<String,Integer>();
			
			
			for(int i=0;i<P;i++){
				ArrayList<String> authors = new ArrayList<String>();
				line = bfr.readLine();
				int len = line.length();
				int cnt=0,k=0;
				//hong
				line = line.substring(0,line.indexOf(":"));
				StringTokenizer st = new StringTokenizer(line,", ");
				while(st.hasMoreTokens()){
					String s = st.nextToken()+", "+st.nextToken();
					authors.add(s);
				}
				//bell
//				for(int j=0;j<len;j++){
//					if((line.charAt(j)==','&&(++cnt%2)==0)){
//						authors.add(line.substring(k,j).trim());
//						k=j+2;
//					}
//				}
//				authors.add(line.substring(k,line.indexOf(":")));
				
				for(String a: authors)
					if(!map.containsKey(a)){
						map.put(a,nNodes++);
						adjList.add(new ArrayList<Integer>());
					}
			
				len = authors.size();
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
					line = bfr.readLine();
					if(map.containsKey(line)){
						int erdosnum = num[map.get(line)];
						String ans = erdosnum==0 ? "infinity": ""+erdosnum;
						bfw.write(line+" "+ans+"\n");
					}
					else bfw.write(line+" infinity\n");
				}
			}else{
				for(int i=0;i<N;i++){
					line = bfr.readLine();
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
			for(int v : adjList.get(u)){
				if(!visited[v]){
					visited[v] = true;
					num[v] = num[u]+1;
					qe.add(v);
				}
			}
		}
	}
}