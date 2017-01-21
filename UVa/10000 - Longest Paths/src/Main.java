import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
	//10000 - Longest Paths
	int n,start;
	boolean visited[];
	int end[];
	int length[];
	ArrayList<ArrayList<Integer>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bfr.readLine());
		int c = 1;
		while(n!=0){
			path = new ArrayList<ArrayList<Integer>>();
			start = Integer.parseInt(bfr.readLine());
			for(int i=0;i<=n;i++) path.add(new ArrayList<Integer>());
			visited = new boolean[n+1];
			end = new int[n+1];
			length = new int[n+1];
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			while(!(a==0&&b==0)){
				path.get(a).add(b);
				st = new StringTokenizer(bfr.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
			}
			visited[start] = true;
			int ans = traversal(start)-1;
			System.out.println("Case "+c+": The longest path from "+start+" has length "+ans+", finishing at "+end[start]+".\n");
			n = Integer.parseInt(bfr.readLine());
			c++;
		}
	}
	int traversal(int i){
		if(length[i]!=0) return length[i];
		int max = 0;
		int ed = 0;
		for(Integer c : path.get(i)){
			int ans = traversal(c);
			if(ans>max){
				max = ans;
				end[i] = end[c];
				ed = end[i];
			}
			if(ans==max && end[c]<ed) end[i] = end[c];
		}
		if(max==0) end[i] = i;
		return length[i] = max+1;
	}
}
