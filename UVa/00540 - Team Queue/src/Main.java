import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//00540 - Team Queue
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		Map<String,Integer> map = new HashMap<String,Integer>();
		int t = Integer.parseInt(bfr.readLine());
		int c = 1;
		while(t!=0){
			System.out.println("Scenario #"+c);
			for(int i=0;i<t;i++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				int n = Integer.parseInt(st.nextToken());
				for(int j=0;j<n;j++) map.put(st.nextToken(), i); //element j belong to group i
			}
			ArrayList<Queue<String>> list = new ArrayList<Queue<String>>();
			ArrayList<Integer> team = new ArrayList<Integer>();
			String s = bfr.readLine();
			while(!s.equals("STOP")){
				if(s.equals("DEQUEUE")){
					System.out.println(list.get(0).poll());
					if(list.get(0).size()==0) {
						list.remove(0);
						team.remove(0);
					}
				}
				else{
					String element = s.substring(8);
					int teamNum = map.get(element); 
					int index = team.indexOf(teamNum); 
					if(index==-1){//not exist
						team.add(teamNum);
						list.add(new LinkedList<String>());
						list.get(list.size()-1).add(element);
					}
					else{
						list.get(index).add(element);
					}
				}
				
				s = bfr.readLine();
			}
			t = Integer.parseInt(bfr.readLine());
			System.out.println();
			c++;
		}
	}
}
