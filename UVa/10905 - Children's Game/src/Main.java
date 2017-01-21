import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//10905 - Children's Game
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			ArrayList<Node> list = new ArrayList<Node>();
			if(n==0) break;
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			for(int i=0;i<n;i++){
				list.add(new Node(st.nextToken()));
			}
			Collections.sort(list);
			String out = "";
			for(Node c : list) out +=c;
			System.out.println(out);
		}
	}
	public class Node implements Comparable<Node>{
		String key;
		public Node(String k){
			key = k;
		}
		public int compareTo(Node another){
			String s = another.key;
			return (s+key).compareTo(key+s);
		}
		public String toString(){ return new String(key); }
	}
}
