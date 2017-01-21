import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//10201 - Adventures in Moving - Part IV
	ArrayList<Station> station;
	int bigC;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		String s = bfr.readLine();
		while(tc-->0){
			bigC = Integer.parseInt(bfr.readLine().trim()); //distance from WaterLoo to the big city
			
			station = new ArrayList<Station>();
			station.add(new Station(0,0) );//WaterLoo station
			
			while((s = bfr.readLine())!=null){ 
				if(s.isEmpty()) break; //end case
				StringTokenizer st = new StringTokenizer(s);
				int d = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if(d <= bigC) station.add(new Station(d,c));
				//if d > distance, no need to be stored
			}
			
			if( reachable() ){
				//initialize
				int[][] dp = new int[station.size()][201];
				for(int i=0;i<dp.length;i++)
					Arrays.fill(dp[i], Integer.MAX_VALUE);
				dp[0][100] = 0;
				
				//case W->s1, s1->bigC
				for(int i=0;i<dp.length-1;i++){//W to S[i-1]
					for(int j=0;j<=200;j++){//for each gas in the tank
						if(dp[i][j]==Integer.MAX_VALUE) continue;
						
						//move from i to i+1, dist = [i+1] - [i]
						int dist = station.get(i+1).dist - station.get(i).dist;//distance between station
						if(j - dist >= 0){//can go
							int remainGas = j-dist;
							for(int k=remainGas;k<=200;k++){
								dp[i+1][k] = Math.min(dp[i+1][k], dp[i][j] + (k-remainGas)*station.get(i+1).cost);
							}
						}
					}
				}
				
				//latest station to BigC
				int min = Integer.MAX_VALUE;
				int d = bigC - station.get(station.size()-1).dist;
				for(int j = 100+d;j<=200;j++)
					min = Math.min(min, dp[station.size()-1][j]);
				System.out.println(min==Integer.MAX_VALUE?"Impossible":min);
			}
			else System.out.println("Impossible");
			if(tc>0) System.out.println();
		}
	}
	boolean reachable(){
		if(station.isEmpty()) return bigC == 0;
		for(int i=0;i<station.size();i++){
			if (i == station.size()-1){//latest station
				int d = bigC - station.get(i).dist;
				if(d>100) return false;
			}
			else if(i == 0){
				int d = station.get(i+1).dist - station.get(i).dist;
				if(d>100) return false;
			}
			else{
				int d = station.get(i+1).dist - station.get(i).dist;
				if(d>200) return false;
			}
		}
		return true;
	}
	public class Station{
		int dist;//dist from WaterLoo
		int cost;//liter
		public Station(int d, int c){
			dist = d;
			cost = c;
		}
		public String toString() { return "("+dist+", "+cost+")"; }
	}
}
