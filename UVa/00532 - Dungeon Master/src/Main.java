import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//00532 - Dungeon Master
	int L, R, C;
	int I[] = {-1,0,1,0,0,0};
	int J[] = {0,1,0,-1,0,0};
	int K[] = {0,0,0,0,-1,1};
	char map[][][];
	boolean visited[][][];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L==0 && R==0 && C==0) break;
			map = new char[L][R][C];
			Node start = new Node(0,0,0,0);
			for(int i=0;i<L;i++){//level
				for(int j=0;j<R;j++){//each row in level
					String s = bfr.readLine();
					map[i][j] = s.toCharArray();
					if(s.indexOf('S')!=-1){
						start = new Node(i,j,s.indexOf('S'),0);
					}
				}
				bfr.readLine(); //blankline 
			}
			int move = BFS(start);
			bfw.write(move>-1? ("Escaped in "+move+" minute(s).\n") : "Trapped!\n");
		}
		bfw.flush();
	}
	boolean inRange(Node node){
		return 0<=node.lv && node.lv<L && 0<=node.row && node.row<R && 0<= node.col && node.col<C;
	}
	int BFS(Node start){
		visited = new boolean[L][R][C];
		Queue<Node> qe = new LinkedList<Node>();
		qe.add(start); visited[start.lv][start.row][start.col] = true;
		while(!qe.isEmpty()){
			Node u = qe.poll();
			for(int k=0;k<6;k++){//direction
				Node v = new Node(u.lv+K[k],u.row+I[k],u.col+J[k], u.move+1);
				if(inRange(v) && !visited[v.lv][v.row][v.col]){
					visited[v.lv][v.row][v.col] = true;
					if(map[v.lv][v.row][v.col]=='E') return v.move;
					if(map[v.lv][v.row][v.col]=='.') qe.add(v);
				}
			}
		}
		return -1;
	}
	public class Node{
		int lv, row, col;
		int move;
		public Node(int l, int r, int c, int m){
			lv  = l;
			row = r;
			col = c;
			move = m;
		}
	}
}