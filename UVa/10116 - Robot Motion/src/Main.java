import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//10116 - Robot Motion
	int r, c;
	char[][] map;
	int[][] dist;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			if(r==0 && c==0 && x==0) break;
			map = new char[r][];
			dist = new int[r][c];
			for(int i=0;i<r;map[i++] = bfr.readLine().toCharArray());
			for(int i=0;i<r;i++){
				for(int j=0;j<c;j++) dist[i][j] = -1;
			}
			move(0,x-1);
		}
	}
	void move(int i, int j){
		dist[i][j] = 0;
		while(true){
			int prevStep = dist[i][j];
			if(map[i][j]=='N')      i--;
			else if(map[i][j]=='W') j--;
			else if(map[i][j]=='E') j++;
			else if(map[i][j]=='S') i++;
			//new position
			if(exit(i,j)) {
				System.out.println((prevStep+1) +" step(s) to exit");
				break;
			}
			if(dist[i][j]!=-1){
				int currentStep = dist[i][j];
				System.out.println(currentStep+" step(s) before a loop of "+(prevStep+1-currentStep)+" step(s)");
				break;
			}
			dist[i][j] = prevStep + 1;
		}
	}
	boolean exit(int i, int j){
		return i<0 || i>=r || j<0 || j>=c;
	}
}
