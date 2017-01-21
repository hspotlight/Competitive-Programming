import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11957 - Checkers
	int n;
	char[][] map;
	int[][] ways;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine());
		int startI = 0, startJ = 0;
		for(int c=1; c<=tc; c++){
			n = Integer.parseInt(bfr.readLine());
//			System.out.println(n);
			map = new char[n][n];
			ways = new int[n][n];
			for(int i=0;i<n;i++){
				String s = bfr.readLine();
				int index = s.indexOf('W');
				if(index!=-1) { startI = i; startJ = index; }
				map[i] = s.toCharArray();
//				System.out.println(map[i]);
			}
//			System.out.println(startI+" "+startJ);
			int ans = traversal(startI,startJ);
			System.out.println("Case "+c+": "+ans);
		}
	}
	int traversal(int i, int j){
		if(ways[i][j]!=0) return ways[i][j];
		if(i==0) return 1;
		int x, y, c, w = 0;
		//left
		x = i - 1; y = j - 1;
		c = choice(x,y);
		if(c==1) w += traversal(x,y);
		else if (c==2 && choice(x-1,y-1)==1) w += traversal(x-1,y-1);
		//right
		x = i - 1; y = j + 1;
		c = choice(x,y);
		if(c==1) w += traversal(x,y);
		else if (c==2 && choice(x-1,y+1)==1) w += traversal(x-1,y+1);
		return ways[i][j] = (w%1000007);
	}
	int choice(int i, int j){
		if(0<=i && i<n && 0<=j && j<n){
			if(map[i][j]=='.') return 1; //free
			return 2; //black
		}
		return -1; //cannot go
	}
	public class Node{
		int i;
		int j;
		public Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
