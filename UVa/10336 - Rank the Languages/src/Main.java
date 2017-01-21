import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//10336 - Rank the Languages
	int n, m;
	char map[][];
	int freq[];
	int I[] = {-1,1,0,0};
	int J[] = {0,0,1,-1};
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine());
		for(int c = 1;c<=tc;c++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n][m];
			freq = new int[26];//a-z
			for(int i=0;i<n;i++){
				map[i] = bfr.readLine().toCharArray();
			}
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++){
					if('a'<= map[i][j] && map[i][j] <= 'z'){
						char target = map[i][j];
						freq[target-'a']++;
						//DFS
						DFS(i,j,target);
					}
				}
			}
			//
			ArrayList<Node> list = new ArrayList<Node>();
			for(int i=0;i<26;i++){
				if(freq[i]>0) list.add( new Node((char)(i+'a'),freq[i]) );
			}
			Collections.sort(list);
			System.out.println("World #"+c);
			for(Node node : list)
				System.out.println(node);
		}
	}
	void DFS(int i, int j, char pattern){
		if(!inRange(i,j) || map[i][j] != pattern) return;
		map[i][j] = 0;
		for(int k=0;k<4;k++)
			DFS(i+I[k], j+J[k],pattern);
	}
	boolean inRange(int i, int j){
		return 0<=i && i<n && 0<=j && j<m;
	}
	public class Node implements Comparable<Node>{
		char ch;
		int freq;
		public Node(char c, int freq){
			this.ch = c;
			this.freq = freq;
		}
		public int compareTo(Node n){
			if(freq!=n.freq) return -(freq - n.freq);
			return ch - n.ch;
		}
		public String toString(){ return ch+": "+freq; }
	}
}