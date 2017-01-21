import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//11157 - Dynamic Frog
	int stone[];
	boolean isBig[];
	ArrayList<Integer> big;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c = 1;c<=tc;c++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int n = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			stone = new int[n+2];
			isBig = new boolean[n+2];
			stone[0] = 0;   isBig[0] = true;
			stone[n+1] = D; isBig[n+1] = true;
			
			big = new ArrayList<Integer>();
			big.add(0);
			int k = 1;
			while(k<=n){
				st = new StringTokenizer(bfr.readLine());
				while(st.hasMoreTokens()){
					String s = st.nextToken();
					if(s.charAt(0)=='B'){
						isBig[k] = true;
						big.add(k);
					}
					stone[k] = Integer.parseInt(s.substring(2));
					k++;
				}
			}
			big.add(n+1);
			int max = Integer.MIN_VALUE;
			//calculation
			for(int i=0;i<big.size()-1;i++){
				int ans = move(i);
				if(ans>max) max = ans;
			}
			
			System.out.println("Case "+c+": "+max);
		}
	}
	int move(int i){//move from i to i+1
		int start = big.get(i);
		int end = big.get(i+1);
		int j,dist,max = 0;
		//forward
		for(j=start;j<=end-2;j+=2){
			dist = stone[j+2] - stone[j];
			if(dist>max) max = dist;
		}
		
		if(j<end){//j == end - 1
			dist = stone[j+1] - stone[j];
			if(dist>max) max = dist;
		}
		//backward
		j=start;
		dist = stone[j+1] - stone[j];
		if(dist>max) max = dist;
		
		for(j=start+1;j<=end-2;j+=2){
			dist = stone[j+2] - stone[j];
			if(dist>max) max = dist;
		}
		
		if(j<end){//j == end - 1
			dist = stone[j+1] - stone[j];
			if(dist>max) max = dist;
		}
		
		return max;
	}
}
