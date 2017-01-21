import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	//11997 - K Smallest Sums
	int N, best[];
	StreamTokenizer stk;
	int readInt()throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		while(stk.nextToken() != StreamTokenizer.TT_EOF){
			N = (int)stk.nval;
			best = new int[N];
			for(int i=0;i<N;i++) best[i] = readInt();
			Arrays.sort(best);
			
			//next k-1 lines
			for(int k=1;k<N;k++){
				int[] arr = new int[N];
				for(int j=0;j<N;j++) arr[j] = readInt();
				Arrays.sort(arr);
				
				PriorityQueue<Node> pq = new PriorityQueue<Node>();
				for(int i=0;i<N;i++) pq.add(new Node(best[i] + arr[0], 0));
				
				int cnt = 0;
				int temp[] = new int[N];
				while(cnt < N){
					Node u = pq.poll();
					temp[cnt] = u.val;
					if(u.index!=N-1){
						pq.add(new Node(u.val - arr[u.index] + arr[u.index+1], u.index+1));
					}
					cnt++;
				}
				best = temp;
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<N;i++) sb.append(best[i]+" ");
			bfw.write(sb.deleteCharAt(sb.length()-1)+"\n");
		}
		bfw.flush();
	}
	public class Node implements Comparable<Node>{
		int val;
		int index;
		public Node(int v, int i){
			val = v;
			index = i;
		}
		public int compareTo(Node n){ return val - n.val; }
	}
}
