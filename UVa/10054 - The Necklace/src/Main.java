import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//10054 - The Necklace
	int N = 50;//total color
	int nBeads;
	int countBeadUsed;
	boolean bead[];//visited
	boolean used[];
	StreamTokenizer stk;
	ArrayList<Bead> list[];
	int inDegree[];
	int start;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter (new OutputStreamWriter (System.out));
		stk = new StreamTokenizer(bfr);
		int TC = readInt();
		for(int tc=1;tc<=TC;tc++){
			nBeads = readInt();
			bead = new boolean[nBeads];
			used = new boolean[N+1];
			list = new ArrayList[N+1];
			inDegree = new int[N+1];
			countBeadUsed = 0;
			start = -1;
			for(int i=0;i<N+1;i++) list[i] = new ArrayList<Bead>();
			for(int i=0;i<nBeads;i++){
				int u = readInt(), v = readInt();
				list[u].add(new Bead(u, v, i));
				list[v].add(new Bead(v, u, i));
				inDegree[u]++;
				inDegree[v]++;
				used[u] = used[v] = true;
				
				if(start==-1) start = u;
			}
			bfw.write("Case #"+tc+"\n");
			if(!validate()) bfw.write("some beads may be lost\n");
			else bfw.write(euler(start));
			if(tc!=TC) bfw.write("\n");
		}
		bfw.flush();
	}
	String euler(int u){
		ArrayList<Bead> list = new ArrayList<Bead>();
		list = DFS(u);
		while(countBeadUsed < nBeads){
			int v = -1, pos = -1;
			for(int i=list.size()-1;i>=0;i--){
				Bead b = list.get(i);
				if(inDegree[ v = b.to ]!=0){//can go
					pos = i; break;
				}
			}
			if(v==-1) break;
			ArrayList<Bead> pDash = DFS(v);
			for(int i= pDash.size()-1;i>=0;i--) list.add(pos+1, pDash.get(i));
		}
		//output
		StringBuilder sb = new StringBuilder();
		for(Bead b : list)
			sb.append(b+"\n");
		
		return  sb.toString();
	}
	ArrayList<Bead> DFS(int start){
		ArrayList<Bead> path = new ArrayList<Bead>();
		int from = start;
		while(true){
			for(Bead b : list[from]){
				if(!bead[b.beadNumber]){
					bead[b.beadNumber] = true;
					inDegree[b.from]--; inDegree[b.to]--;
					countBeadUsed++;
					from = b.to;
					path.add(b);
					break;
				}
			}
			if(path.get(path.size()-1).to == start && inDegree[from]==0) break;
		}
		return path;
	}
	boolean validate(){
		for(int i=1;i<N+1;i++){
			if(used[i]){
				if(list[i].size()%2!=0) return false;
			}
		}
		return true;
	}
	public class Bead{
		int from;
		int to;
		int beadNumber;
		public String toString() { return from+" "+to; }
		public Bead(int u, int v, int b){
			this.from = u;
			this.to = v;
			beadNumber = b;
		}
	}
}