import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//12207 - That is Your Queue
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int P = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if(P==0 && C==0) break;
			ArrayList<Integer> list = new ArrayList<Integer>();
			//initial
			int n = P < 1000? P : 1000;
			for(int i=1;i<=n;i++){
				list.add(i);
			}
			//command 
			System.out.println("Case "+c+":");
			c++;
			for(int i=0;i<C;i++){
				st = new StringTokenizer(bfr.readLine());
				String command = st.nextToken();
				if(command.equals("N")){ //N 
					//poll
					int cur = list.get(0);
					list.remove(0);
					list.add(cur);
					System.out.println(cur);
				}
				else{// E
					int query = Integer.parseInt(st.nextToken());
					int index = list.indexOf(query);
					if(index!=-1) list.remove(index);
					list.add(0, query);
				}
			}
		}
	}
}
