import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//10959 - The Party, Part I
	int P, D;
	ArrayList<Integer> list[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();	
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			P = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			list = new ArrayList[P];
			for(int i=0;i<P;i++) list[i] = new ArrayList<Integer>();
			for(int i=0;i<D;i++){
				st = new StringTokenizer(bfr.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				list[u].add(v); list[v].add(u);
			}
			bfw.write( BFS() );
			if(tc>0) bfw.write("\n");
		}
		bfw.flush();
		bfw.close();
	}
	String BFS(){
		int move[] = new int[P];
		Arrays.fill(move, -1);
		Queue<Integer> qe = new LinkedList<Integer>();
		qe.add(0); move[0] = 0;
		while(!qe.isEmpty()){
			int u = qe.poll();
			for(int v : list[u]){
				if(move[v]==-1){
					qe.add(v); move[v] = move[u] + 1;
				}
			}
		}
		String out = "";
		for(int i=1;i<P;i++)
			out += (move[i])+"\n";
		return out;
	}
}