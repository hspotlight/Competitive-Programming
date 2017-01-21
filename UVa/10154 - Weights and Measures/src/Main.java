import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//10154 - Weights and Measures
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Turtle> list = new ArrayList<Turtle>();
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int strength = Integer.parseInt(st.nextToken());
			list.add(new Turtle(weight, strength));
		}
		Collections.sort(list);
		//dp 2 dimension[put turtle i][length j] with minimum weight j<=i
		int n = list.size();
		int dp[][] = new int[n+1][n+1];//row 0 | col 0 are 0
		int prevMax = 0;
		for(int i=1;i<=n;i++){// put turtle[i-1]
			Turtle cur = list.get(i-1);
			int curMax = 0;
			for(int j=1;j<=prevMax+1;j++){// 1 for try new
				int topleft = dp[i-1][j-1]<=cur.capacity()? dp[i-1][j-1]+cur.weight:Integer.MAX_VALUE;
				int top  = (j != prevMax+1)? dp[i-1][j] : Integer.MAX_VALUE;
				
				dp[i][j] = Math.min(topleft, top);
				if(dp[i][j]!=Integer.MAX_VALUE) curMax = Math.max(curMax, j);
			}
			prevMax = Math.max(prevMax, curMax);
		}
		System.out.println(prevMax);
	}
	public class Turtle implements Comparable<Turtle>{
		int weight;
		int strength;
		public Turtle(int w, int s){
			weight = w;
			strength = s;
		}
		public int capacity(){ return strength<weight ? 0 : strength - weight; }
		public int compareTo(Turtle t){ return strength - t.strength; }
		public String toString() { return "("+weight+", "+strength+")"; }
	}
}