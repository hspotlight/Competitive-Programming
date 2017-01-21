import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

public class Main {
	//732 - Anagrams by Stack
	StreamTokenizer stk;
	char[] src, des;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			src = bfr.readLine().toCharArray();
			des = bfr.readLine().toCharArray();
			System.out.println("[");
			if(src.length==des.length){
				//do recursive
				stack(new Stack<Character>(),0,0,"");
			}
			System.out.println("]");
		}
	}
	@SuppressWarnings("unchecked")
	void stack(Stack<Character> stack,int i, int o, String out){
		if((out.length()+1)/2==src.length+des.length) System.out.println(out);
		else{
			//push
			if(i<src.length){//can be push
				Stack<Character> tmp = (Stack<Character>)stack.clone();
				tmp.push(src[i]);
				stack(tmp,i+1,o, out+(out.isEmpty()?"":" ")+"i");
			}
			//pop
			if(!stack.isEmpty() && o < des.length){
				Stack<Character> tmp = (Stack<Character>)stack.clone();
				char c = tmp.pop();
				if(c==des[o]){
					stack(tmp,i,o+1, out+(out.isEmpty()?"":" ")+"o");
				}
			}
		}
	}
}