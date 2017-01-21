import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//00117 - The Postal Worker Rings Once
	ArrayList<Integer> odd = new ArrayList<Integer>();
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
			String line;
			int totalCost = 0;
			odd.clear();
			int degree[] = new int[26];
			int map[][] = new int[26][26];
			for(int i=0;i<26;i++){
				for(int j=0;j<26;j++) map[i][j] = i==j?0:100;
			}
			while(!(line = bfr.readLine()).equals("deadend")){
				int u = line.charAt(0)-'a', v = line.charAt(line.length()-1) - 'a'; 
				map[u][v] = map[v][u] = line.length();
				degree[u]++; degree[v]++;
				totalCost += map[u][v];
			}
			if(!isCircuit(degree)){
				for(int k=0;k<26;k++){
					for(int i=0;i<26;i++){
						for(int j=0;j<26;j++){
							map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
						}
					}
				}
				totalCost += map[odd.get(0)][odd.get(1)];
			}
			System.out.println(totalCost);
		}
	}
	boolean isCircuit(int degree[]){
		for(int i=0;i<degree.length;i++){
			if(degree[i]%2==1) odd.add(i);
		}
		return odd.isEmpty();
	}
}