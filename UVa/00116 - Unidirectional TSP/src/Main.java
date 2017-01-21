import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//00116 - Unidirectional TSP: 0.210
	int N, M;
	int[][] map, parent;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int) stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		while(stk.nextToken() != stk.TT_EOF){
			N = (int)stk.nval; M = readInt();
			map = new int[N][M];
			parent = new int[N][M];
			for(int i=0;i<N;i++){
				for(int j=M-1;j>=0;j--){
					map[i][j] = readInt();
				}
			}
			
			for(int j=1;j<M;j++){//column
				for(int i=0;i<N;i++){
					int min = Integer.MAX_VALUE, index = N;
					
					for(int k = i-1;k <= i+1;k++){// i-1 i i+1
						int row = index(k);
						if(map[row][j-1] < min){
							min = map[row][j-1];
							index = row;
						}
						else if (map[row][j-1]==min) index = Math.min(index, row);
					}
					map[i][j] += min;
					parent[i][j] = index;
				}
			}
			
			int min = Integer.MAX_VALUE, index = N;
			for(int i=0;i<N;i++){
				if(map[i][M-1] < min){
					min = map[i][M-1];
					index = i;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(index+1);
			for(int j=M-2;j>=0;j--){
				index = parent[index][j+1];
				sb.append(" "+(index+1));
			}
			bfw.write(sb.toString()+"\n");
			bfw.write(min+"\n");
		}
		bfw.flush();
	}
	int index(int k){
		if(k<0) return N-1;
		if(k==N) return 0;
		return k;
	}
}
