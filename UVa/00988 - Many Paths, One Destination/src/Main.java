import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//00988 - Many Paths, One Destination
	int n;
	int[] max;
	boolean[] visited;
	ArrayList<ArrayList<Integer>> path;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			path = new ArrayList<ArrayList<Integer>>();
			n = readInt();
			max = new int[n];
			visited = new boolean[n];
			for(int i=0;i<n;i++) path.add(new ArrayList<Integer>());
			for(int i=0;i<n;i++){
				int m = readInt();
				while(m-->0){
					int to = readInt();
					path.get(i).add(to);
				}
			}
			//traversal
			System.out.println(traversal(0));//birth event
			if(bfr.ready()) {
				bfr.readLine();
				System.out.println();
			}
		}
	}
	int traversal(int index){
		if(max[index]!=0) return max[index];
		if(path.get(index).size()==0) return (max[index] = 1);
		int sum = 0;
		for(Integer next : path.get(index)){
			sum += traversal(next);
		}
		return max[index] = sum;
	}
}
