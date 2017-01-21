import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
	//10008 - What's Cryptanalysis
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int n = Integer.parseInt(bfr.readLine());
		ArrayList<Node> list = new ArrayList<Node>();
		int freq[] = new int[132];
		while(n-->0){
			char[] c = bfr.readLine().replaceAll("[^A-Za-z]", "").toCharArray();
			for(int i=0;i<c.length;i++){
				freq[ c[i] ]++;
			}
		}
		for(int i='A';i<='Z';i++){
			int f = freq[i] + freq[i+32];
			if(f!=0) list.add(new Node(i, f));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(Node c : list){
			sb.append(c+"\n");
		}
		System.out.print(sb);
	}
	public class Node implements Comparable<Node>{
		int val;
		int freq;
		public Node(int v, int f){
			val = v;
			freq = f;
		}
		public int compareTo(Node n){
			if(freq != n.freq) return -(freq - n.freq);
			return (val - n.val);
		}
		public String toString(){ return ""+((char)val)+" "+freq; }
	}
}