import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	//10150 - Doublets
	HashMap<String, String> parent;//keep track
	HashSet<String> set;
	String S,T;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		set = new HashSet<String>();
		String s;
		while(!(s = bfr.readLine()).equals("")) set.add(s);
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			S = st.nextToken(); T = st.nextToken();
			System.out.print( BFS() );
			if(bfr.ready()) System.out.println();
		}
	}
	String BFS(){
		Queue<String> qe = new LinkedList<String>();
		parent = new HashMap<String,String>();
		parent.put(S,null); qe.add(S);
		while(!qe.isEmpty()){
			String u = qe.poll();
			//terminate case
			if(u.equals(T)){
				Stack<String> stack = new Stack<String>();
				String s = T;
				while(s!=null){
					stack.add(s);
					s = parent.get(s);
				}
				String out = "";
				while(!stack.isEmpty()) out += stack.pop()+"\n";
				return out;
			}
			//u -> v
			for(int i=0;i<u.length();i++){//index 0 to n-1
				for(int j=0;j<26;j++){// a - z
					char[] c = u.toCharArray();
					c[i] = (char)('a' + j);
					String v = new String(c);
					if(set.contains(v)){//$v is in Dict
						if(!parent.containsKey(v)){//$v is not visited
							parent.put(v, u); qe.add(v);
						}
					}
				}
			}
		}
		return "No solution.\n";
	}
}
