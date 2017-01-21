import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {
	//12442 - Forwarding Emails
	int edge[];
	int maxSend[];
	boolean visited[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int i=1;i<=tc;i++){
			int n = Integer.parseInt(bfr.readLine());
			edge = new int[n];
			maxSend = new int[n];
			visited = new boolean[n];
			for(int j=0;j<n;j++){
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				edge[Integer.parseInt(st.nextToken())-1] = Integer.parseInt(st.nextToken())-1;
			}
			int max = 0;
			int index = -1;
			for(int j=0;j<n;j++){
				int ans = DFS(j);
				if(ans>max){
					max = ans;
					index = j;
				}
			}
			System.out.println("Case "+i+": "+(index+1));
		}
	}
	int DFS(int i){
		if(maxSend[i]!=0) return maxSend[i];
		Stack<Integer> stack = new Stack<Integer>();
		visited[i] = true;
		stack.push(i);
		int v2 = edge[i];	
		while(!visited[v2]){
			visited[v2] = true;
			stack.push(v2);
			v2 = edge[v2];
		}//found v2 again
		
		if(maxSend[v2]!=0){//v2 is calculated
			while(!stack.isEmpty()){
				int x = stack.pop();
				maxSend[x] = 1 + maxSend[ edge[x] ];
			}
			return maxSend[i];
		}
		else {//have never seen
			Stack<Integer> cycle = new Stack<Integer>();
			while(!stack.isEmpty() && stack.peek()!=v2){
				cycle.push( stack.pop() );
			}
			cycle.push( stack.pop() );//pop v2
			
			int count = cycle.size(); //size = number of vertex in cycle
			
			while(!cycle.isEmpty()){
				int x = cycle.pop();
				maxSend[x] = count;
			}
			
			while(!stack.isEmpty()){
				int x = stack.pop();
				maxSend[x] = 1 + maxSend[ edge[x] ];
			}
			return maxSend[i];
		}
	}
}
