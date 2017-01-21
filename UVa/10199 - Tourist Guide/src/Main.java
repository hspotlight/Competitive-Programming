import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	//10199 - Tourist Guide
	int N, count,index;
	int[] num, low, parent, nChildren;
	boolean[] cPoint;
	ArrayList<ArrayList<Integer>> path;
	HashMap<String, Integer> map;
	ArrayList<String> cityList;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		boolean first = true;
		int tc = 1;
		while(bfr.ready()){
			N = Integer.parseInt(bfr.readLine());
			if(N==0) break;
			/***************************************************************/
			if(first) first = false;
			else System.out.println();
			path = new ArrayList<ArrayList<Integer>>();
			map = new HashMap<String, Integer>();
			cityList = new ArrayList<String>();
			index = 0;
			for(int i=0;i<N;i++){
				String s = bfr.readLine();
				cityList.add(s); map.put(s, index++); path.add(new ArrayList<Integer>());
			}
			/***************************************************************/
			//query
			int M = Integer.parseInt(bfr.readLine());
			for(int i=0;i<M;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int s = map.get(st.nextToken()), t = map.get(st.nextToken());
				path.get(s).add(t); path.get(t).add(s);
			}
			/***************************************************************/
			//tarjan
			count = 0;
			num = new int[N]; low = new int[N]; parent = new int[N];
			cPoint = new boolean[N];
			nChildren = new int[N];
			for(int i=0;i<N;i++){
				if(num[i]==0){
					tarjan(i);
					cPoint[i] = (nChildren[i]>1)? true: false;
				}
			}
			/***************************************************************/
			//print
			ArrayList<String> list = new ArrayList<String>();
			for(int i=0;i<N;i++) if(cPoint[i]) list.add(cityList.get(i));
			Collections.sort(list);
			System.out.println("City map #"+(tc++)+": "+list.size()+" camera(s) found");
			for(String s : list) System.out.println(s);
		}
	}
	void tarjan(int u){
		num[u] = low[u] = ++count;
		for(int v : path.get(u)){
			if(num[v]==0){
				nChildren[u]++;
				parent[v] = u;
				tarjan(v);
				if(low[v]>=num[u]) cPoint[u] = true;
				low[u] = Math.min(low[u], low[v]);
			}
			else if (parent[u]!=v) low[u] = Math.min(low[u], num[v]);
		}
	}
}
