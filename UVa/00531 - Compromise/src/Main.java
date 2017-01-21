import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	//00531 - Compromise
	ArrayList<String> s, t;
	BufferedReader bfr;
	int map[][];
	Stack<String> stack;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		bfr = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			//input
			s = split();
			t = split();
			map = new int[s.size()+1][t.size()+1];
			LCS();
			stack = new Stack<String>();
			path(s.size(),t.size());
			String out = ""+stack.pop();
			while(!stack.isEmpty()) out += " "+stack.pop();
			System.out.println(out);
			
			if(!bfr.ready()) break;
		}
	}
	public void path(int i, int j){
		if(i==0 || j==0) return;
		if(s.get(i-1).equals(t.get(j-1))){
			stack.add(s.get(i-1));
			path(i-1,j-1);
		}
		else if(map[i-1][j]>map[i][j-1]) path(i-1, j);
		else path(i, j-1);
	}
	public void LCS(){
		for(int i=1;i<s.size()+1;i++){
			for(int j=1;j<t.size()+1;j++){
				if(s.get(i-1).equals(t.get(j-1))) map[i][j] = map[i-1][j-1]+1;
				else map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
			}
		}
	}
	public ArrayList<String> split()throws IOException{
		ArrayList<String> list = new ArrayList<String>();
		String in;
		while(!(in = bfr.readLine()).equals("#")){
			StringTokenizer st = new StringTokenizer(in);
			while(st.hasMoreTokens()) list.add(st.nextToken());
		}
		return list;
	}
}
