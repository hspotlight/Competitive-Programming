import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//00705 - Slash Maze
	int W,H;
	StreamTokenizer stk;
	int map[][];
	int I[] = {-1,0,1,0};
	int J[] = {0,1,0,-1};
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	boolean exit;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = 1;
		while(true){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W==0 && H==0) break;
			map = new int[H*3][W*3];
			for(int i=0;i<map.length;i+=3){
				char[] s = bfr.readLine().toCharArray();
				for(int j=0;j<s.length;j++){
					if(s[j]=='/'){
						map[i  ][j*3+2] = 1; 
						map[i+1][j*3+1] = 1;
						map[i+2][j*3  ] = 1;
					}
					else if (s[j]=='\\'){
						map[i  ][j*3  ] = 1;
						map[i+1][j*3+1] = 1;
						map[i+2][j*3+2] = 1;
					}
				}
			}
//			for(int i=0;i<map.length;i++) System.out.println(Arrays.toString(map[i]));
			int count = 0;
			int max = -1;
			for(int i=0;i<map.length;i++){
				for(int j=0;j<map[0].length;j++){
					if(map[i][j]==0){
						exit = false;
						int sum = flood(i,j);
						if(!exit){
							count++;
							max = Math.max(max, sum/3);
						}
					}
				}
			}
			System.out.println("Maze #"+(tc++)+":");
			if(count==0) System.out.println("There are no cycles.");
			else System.out.println(count+" Cycles; the longest has length "+max+".");
			System.out.println();
		}
	}
	int flood(int i, int j){
		int sum = 0;
		if(!inRange(i,j)) { exit = true; return 0; }
		if(map[i][j]==1) return 0;
		if(map[i][j]==0) {
			map[i][j] = 1; sum = 1;
		}
		for(int k=0;k<4;k++){
			sum += flood(i+I[k],j+J[k]);
		}
		return sum;
	}
	boolean inRange(int i, int j){
		return (0<=i && i<map.length && 0<=j && j<map[0].length);
	}
}