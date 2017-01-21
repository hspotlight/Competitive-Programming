import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	//10131 - Is Bigger Smarter
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		ArrayList<Node> list = new ArrayList<Node>();
		int counter = 0;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int w  = Integer.parseInt(st.nextToken());
			int IQ = Integer.parseInt(st.nextToken());
			list.add(new Node(++counter, w, IQ));
		}
		Collections.sort(list, new Comparator<Node>(){
			public int compare(Node arg0, Node arg1) {
				if(arg0.weight!=arg1.weight) return (arg0.weight - arg1.weight);
				return -(arg0.IQ - arg1.IQ);
			}
		});
		/********************************* process ********************************/
		int max = 0;
		int index = -1;
		int maxLen[] = new int[counter];
		int parent[] = new int[counter];
		Arrays.fill(parent, -1);
		maxLen[0] = 1;
		for(int i=1;i<counter;i++){
			Node cur = list.get(i);
			for(int j=0;j<i;j++){//elephant 0 to i-1
				Node prev = list.get(j);
				if(cur.weight > prev.weight && cur.IQ < prev.IQ && maxLen[i] < maxLen[j]){
					maxLen[i] = maxLen[j];
					parent[i] = j;
				}
			}
			maxLen[i] = maxLen[i] + 1;
			if(max <= maxLen[i]){
				max = maxLen[i];
				index = i;
			}
		}
		/********************************* output *******************************/
		ArrayList<Integer> out = new ArrayList<Integer>();
		int v = index, u = parent[v];
		do{
			out.add(list.get(v).id);
			v = u; u = parent[v];
		}while(u!=-1);
		out.add(list.get(v).id);
		
		System.out.println(max);
		for(int i=out.size()-1;i>=0;i--){
			System.out.println(out.get(i));
		}
		
	}
	public class Node{
		int id;
		int weight;
		int IQ;
		public Node(int id, int w, int iq){
			this.id = id;
			weight = w;
			IQ = iq;
		}
		public String toString() { return "("+id+": "+weight+" "+IQ+")";}
	}
}