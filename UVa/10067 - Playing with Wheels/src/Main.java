import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//10067 - Playing with Wheels
	String s, t;
	boolean visited[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		int tc = Integer.parseInt(st.nextToken());
		while(tc-->0){
			s = bfr.readLine();
			while(s.trim().length()==0) s = bfr.readLine();
			s = convert(s);
			t = convert(bfr.readLine());
			st = new StringTokenizer(bfr.readLine());
			int m = Integer.parseInt(st.nextToken());
			visited = new boolean[10000]; // 0000 - 9999
			for(int i=0;i<m;i++){
				String forbidden = convert(bfr.readLine());
				int trapped = Integer.parseInt(forbidden);
				visited[trapped] = true;
			}
			/*********************************************************************/
			//traversal
			System.out.println( traversal(s,t) );
		}
	}
	String convert(String s){
		String out = "";
		for(int i=0;i<s.length();i++){
			if('0'<=s.charAt(i) && s.charAt(i)<='9'){
				out+=s.charAt(i);
			}
		}
		return out;
	}
	int traversal(String s, String t){
		int source = Integer.parseInt(s);
		visited[source] = true;
		Queue<Node> qe = new LinkedList<Node>();
		qe.add(new Node(s,0));
		while(!qe.isEmpty()){
			Node cur = qe.poll();
			String config = cur.config;
			if(config.equals(t)){
				return cur.move;
			}
			//move 8 direction
			for(int i=0;i<8;i++){
				int pos = i/2;   // [0 1] [2 3] [4 5] [6 7]
				int left = i & 1;// [0 1] [0 1] [0 1] [0 1] //0 = left, 1 = right
				char[] c = config.toCharArray();
				if(left==0){//-1
					if(c[pos]=='0') c[pos] = '9';
					else c[pos]--;
				}
				else{//+1
					if(c[pos]=='9') c[pos] = '0';
					else c[pos]++;
				}
				String next = new String(c);
				int v = Integer.parseInt(next);
				if(!visited[v]){
					visited[v] = true;
					qe.add(new Node(next,cur.move+1));
				}
			}
		}
		return -1;
	}
	class Node{
		String config;
		int move;
		public Node(String s, int m){
			config = s;
			move = m;
		}
	}
}
