import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//00926 - Walking Around Wisely
	//direction, [x][y] -> [y][x], data type
	//T^T
	int n;
	BigInteger cnt[][];
	BufferedReader bfr;
	StringTokenizer st;
	ArrayList<ArrayList<Character>> list;
	int readInt() throws Exception {
		if(!st.hasMoreTokens()) st = new StringTokenizer(bfr.readLine());
		return Integer.parseInt(st.nextToken()); 
	}
	public static void main(String [] args) throws Exception{ new Main().run(); }
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		bfr = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(bfr.readLine());
		int tc = readInt();
		while(tc-->0){
			n = readInt();
			int sX = readInt(), sY = readInt(); //start
			int tX = readInt(), tY = readInt(); //target
			cnt = new BigInteger[n+1][n+1];
			list = new ArrayList<ArrayList<Character>>(); // X*n + Y;
			for(int i=0;i<(n+1)*(n+1);i++) list.add(new ArrayList<Character>()); 
			int m = readInt();
			for(int i=0;i<m;i++){
				int x = readInt(), y = readInt();
				char c = st.nextToken().charAt(0);
				String s = "NSNWEW";
				list.get(y*n+x).add(c);
				if(c=='S')y--;//N
				if(c=='N')y++;//S
				if(c=='E')x++;//W
				if(c=='W')x--;//E
				char v = s.charAt(s.indexOf(c)+1);
				list.get(y*n+x).add(v);
			}
			for(int y=0;y<n+1;y++)
				for(int x=0;x<n+1;x++)
					cnt[y][x] = BigInteger.ZERO;
			//
			cnt[sY][sX] = BigInteger.ONE;
			for(int y=sY;y<=tY;y++){
				for(int x=sX;x<=tX;x++){
					int index = y*n+x;
					if(!list.get(index).contains('W')) 
						cnt[y][x] = cnt[y][x].add(cnt[y][x-1]);	
					if(!list.get(index).contains('S')) 
						cnt[y][x] = cnt[y][x].add(cnt[y-1][x]);
				}
			}
			System.out.println(cnt[tY][tX]);
		}
	}
}