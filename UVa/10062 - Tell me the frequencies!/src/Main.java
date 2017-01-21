import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//10062 - Tell me the frequencies!
	StringBuilder sb;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		sb = new StringBuilder();
		while(bfr.ready()){
			output(bfr.readLine().toCharArray());
			if(bfr.ready()) sb.append("\n");
		}
		System.out.print(sb);
	}
	void output(char[] c){
		int freq[] = new int[130];
		for(int i=0;i<c.length;i++){
			freq[ c[i] ]++;
		}
		ArrayList<Node> list = new ArrayList<Node>();
		for(int i=32;i<=128;i++){
			if(freq[i]!=0) list.add(new Node(i,freq[i]));
		}
		Collections.sort(list);
		for(Node n : list) sb.append(n+"\n");
	}
	public class Node implements Comparable<Node>{
		int val;
		int freq;
		public Node(int v, int f){
			val = v;
			freq = f;
		}
		public int compareTo(Node n){
			if(freq != n.freq) return (freq - n.freq);
			return -(val - n.val);
		}
		public String toString(){ return ""+val+" "+freq; }
	}
}