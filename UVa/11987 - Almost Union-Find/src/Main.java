import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//11987 - Almost Union-Find
	int N, M;
	int[] parent, set, nElements;
	long sum[];
	StringTokenizer st;
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		while(bfr.ready()){
			st = new StringTokenizer(bfr.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
			set = new int[N+1];
			nElements = new int[N+1];
			sum = new long[N+1];
			for(int i=0;i<=N;i++) sum[i] = parent[i] = set[i] = i;
			Arrays.fill(nElements, 1);
			
			for(int i=0;i<M;i++){
				String line = bfr.readLine();
				st = new StringTokenizer(line);
				int op = Integer.parseInt(st.nextToken());
				int p  = Integer.parseInt(st.nextToken());
				if(op == 3){//print sum
					int n = nElements[ find(set[p]) ];
					long s = sum[ find(set[p]) ];
					sb.append(n+" "+s+"\n");
				}
				else{
					int q = Integer.parseInt(st.nextToken());
					if(op == 1){//union set that containing p and q
						union(p, q);
					}
					else if (op == 2){//move p to set containing q
						int setP = find(set[p]);
						int setQ = find(set[q]);
						if(setP != setQ){
							sum[setQ] += p; nElements[setQ]++;
							sum[setP] -= p; nElements[setP]--;
							set[p] = setQ;
						}
					}
				}
			}
		}
		bfw.write(sb.toString());
		bfw.close();
	}
	void union(int p, int q){
		int setP = find(set[p]);
		int setQ = find(set[q]);
		if(setP != setQ){
			parent[setP] = setQ;
			sum[setQ] += sum[setP];
			nElements[setQ] += nElements[setP];
		}
	}
	int find(int node){
		return node == parent[node]?node:(parent[node] = find(parent[node]));
	}
}
