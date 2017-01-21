import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	//00821 - Page Hopping
	int maxVal = Integer.MAX_VALUE;
	int nMaxVal;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			if(st.countTokens()==2) break; // input = "0 0"
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
			int nEdge = (st.countTokens()-2) >> 1;
			int edge[][] = new int[nEdge][2];
			int count = 0;
			for(int i=0;i<nEdge;i++){
				int s = Integer.parseInt(st.nextToken());
				if(!map.containsKey(s)) map.put(s, count++);
				int d = Integer.parseInt(st.nextToken());
				if(!map.containsKey(d)) map.put(d, count++);
				edge[i][0] = map.get(s);
				edge[i][1] = map.get(d);
			}
			int dist[][] = new int[count][count];
			for(int i=0;i<count;i++){
				for(int j=0;j<count;j++){
//					if(i!=j)//transive closure
						dist[i][j] = maxVal;
				}
			}
			for(int i=0;i<nEdge;i++){
				dist[ edge[i][0] ][ edge[i][1] ] = 1; //dist[src][des] = 1;
			}
			nMaxVal = count*(count-1) - nEdge;
			/************************************************************/
			//floyd warshall
			FW(count,dist);
//			dowork(count,dist);//transitive closure
			int total = 0;
			int divisor = 0;
			for(int i=0;i<count;i++){
				for(int j=0;j<count;j++){
					if(i!=j && dist[i][j]!=maxVal){
						total += dist[i][j];
						divisor++;
					}
				}
			}
			
			if(divisor!=0) {
				System.out.print("Case "+c+": average length between pages = ");
				System.out.print(new DecimalFormat("0.000").format((double)total/divisor));
				System.out.println(" clicks");
			}
			c++;
		}
	}
	//floyd warshall
	void FW(int n, int[][] dist){
		for(int k=0;k<n;k++){
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(dist[i][k]==maxVal || dist[k][j]==maxVal) continue;
					if(dist[i][j] > dist[i][k]+dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
	}
	//transitive closure
	void dowork(int count, int[][] dist){
		int[][] prev;
		do{
			prev = clone(dist);
			for(int i=0;i<count;i++){
				for(int j=0;j<count;j++){
					for(int k=0;k<count;k++){
						if(dist[i][j]==maxVal || dist[j][k]==maxVal) continue;
						if (dist[i][k] > dist[i][j] + dist[j][k]) {
							dist[i][k] = dist[i][j] + dist[j][k];
						}
					}
				}
			}
		}
		while(!compare(prev,dist)); //do until matrix i and i-1 is same
	}
	int[][] clone(int[][] cur){
		int[][] prev = new int[cur.length][cur.length]; 
		for(int i=0;i<prev.length;i++){
			for(int j=0;j<prev.length;j++){
				prev[i][j] = cur[i][j];
			}
		}
		return prev;
	}
	boolean compare(int[][] prev, int[][] cur){
		for(int i=0;i<prev.length;i++){
			for(int j=0;j<prev.length;j++){
				if(prev[i][j]!=cur[i][j]) return false;
			}
		}
		return true;
	}
}
