	import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Stack;

public class Main {
	//10591 - Happy Number
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	HashMap<Integer, Boolean> map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		map = new HashMap<Integer, Boolean>();
		map.put(1, true);
		//true = happy, false = unhappy
		int tc = readInt(),c = 1;
		while(tc-->0){
			int n = readInt();
			int tmp = n;
			Stack<Integer> stack = new Stack<Integer>();
			while(!map.containsKey(tmp)){//do
				if(stack.contains(tmp)) break;
				stack.add(tmp);
				int next = 0;
				while(tmp>0){
					int digit = tmp%10;
					next += (digit*digit);
					tmp/=10;
				}
				tmp = next;
			}
			if(map.containsKey(tmp) && map.get(tmp)){//all value in stack is true
				while(!stack.isEmpty()) map.put(stack.pop(), true);
			}
			else{//loop occur
				while(!stack.isEmpty()) map.put(stack.pop(), false);
			}
			System.out.print("Case #"+(c++)+": "+n+" is ");
			if(map.get(n)) System.out.println("a Happy number."); 
			else System.out.println("an Unhappy number.");
		}
	}
}