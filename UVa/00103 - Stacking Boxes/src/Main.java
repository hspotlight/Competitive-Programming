import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	int n,d;
	int[][] data;
	int[] max, track;
	StreamTokenizer stk;
	ArrayList<ArrayList<Integer>> path;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			n = readInt(); d = readInt();
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<=n;i++) path.add(new ArrayList<Integer>());
			data = new int[n+1][d];
			max = new int[n+1];
			track = new int[n+1];
			for(int i=1;i<=n;i++){
				for(int j=0;j<d;j++){
					data[i][j] = readInt();
				}
				Arrays.sort(data[i]);
			}
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					if(inSide(i,j)) path.get(i).add(j);
				}
			}
			int m = 0;
			int index = 0;
			for(int i=1;i<=n;i++){
				int x = traversal(i);
				if(x > m){
					m = max[i];
					index = i;
				}
			}
			System.out.println(m);
			System.out.print(index);
			while((index = track[index])!=0){
				System.out.print(" "+index);
			}
			System.out.println();
		}
	}
	int traversal(int i){
		if(max[i]!=0) return max[i];
		int m = 0;
		for(Integer c : path.get(i)){
			int x = traversal(c);
			if(x > m){
				 m = x;
				track[i] = c;
			}
		}
		return max[i] = m + 1;
	}
	boolean inSide(int i, int j){
		for(int k=0;k<d;k++){
			if(data[i][k] >= data[j][k]) return false;
		}
		return true;
	}
}
