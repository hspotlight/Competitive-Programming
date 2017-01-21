import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	//11283 - Playing Boggle
	char map[][];
	boolean visited[][];
	ArrayList<ArrayList<Integer>> path;
	int count;
	int val[] = {0,0,1,1, 2,3,5,11, 11,11,11,11 ,11,11,11,11};
	boolean found;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		visited = new boolean[4][4];
		
		int I[] = {-1,-1,-1,0,1,1,1,0};
		int J[] = {-1,0,1,1,1,0,-1,-1};
		path = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<16;i++) path.add( new ArrayList<Integer>() );
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				int now = i*4 + j;
				for(int k=0;k<8;k++){
					int newI = i + I[k];
					int newJ = j + J[k];
					if(0<=newI && newI<4 && 0<=newJ && newJ<4)
						path.get(now).add(newI*4+newJ);
				}
			}
		}
		
		int tc = Integer.parseInt(bfr.readLine());
		for(int i=1;i<=tc;i++){
			bfr.readLine();
			count = 0;
			map = new char[4][4]; 
			for(int j=0;j<4;j++){
				map[j] = bfr.readLine().toCharArray();
//				System.out.println(map[j]);
			}
			int n;
			String input = bfr.readLine();
			while(input.equals("")) input = bfr.readLine();
			n = Integer.parseInt(input.trim());
			int count = 0;
			for(int j=0;j<n;j++){
				String s = bfr.readLine();
				found = false;
				
				for(int x=0;x<4;x++)
					for(int y=0;y<4;y++)
						find(x,y,s.toCharArray(), 0);
				
				if(found) count += val[s.length()-1];
//				System.out.println(found+" "+val[s.length()-1]);
			}
			System.out.println("Score for Boggle game #"+i+": "+count);
		}
	}
	void find(int x,int y,char[] s,int now){
//		if(now==s.length) { found = true; return; }
		if(now<s.length && s[now]==map[x][y]){
			if(now==s.length-1) { found = true; return; }
			visited[x][y] = true;
			for(Integer c : path.get(x*4+y)){
				if(!visited[c/4][c%4]){
					find(c/4,c%4,s,now+1);
				}
			}
			visited[x][y] = false;
		}
	}
}
