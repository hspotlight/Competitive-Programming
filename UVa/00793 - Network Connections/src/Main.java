import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//00793 - Network Connections
	StreamTokenizer stk;
	int[] parent, rank;
	int n;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			n = Integer.parseInt(bfr.readLine());
			int S = 0, U = 0;
			parent = new int[n+1]; rank = new int[n+1];
			for(int i=1;i<=n;i++) { parent[i] = i; rank[i] = 0; }
			while(bfr.ready()){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				if(st.countTokens()==0) break;
				char c = st.nextToken().charAt(0);
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if(c=='c') union(u,v);
				else{//'q'
					if(find(u)==find(v)) S++;
					else U++;
				}
			}
			System.out.println(S+","+U);
			if(bfr.ready()) System.out.println();
		}
	}
	boolean union(int u, int v){
		int uSet = find(u);
		int vSet = find(v);
		if(uSet==vSet) return false;
		if(rank[uSet]<rank[vSet])  parent[uSet] = vSet; else parent[vSet] = uSet;
		if(rank[uSet]==rank[vSet]) rank[uSet]++;
		return true;
	}
	int find(int node){
		return parent[node]==node? node: (parent[node]=find(parent[node]));
	}
}
