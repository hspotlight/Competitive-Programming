import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.regex.Matcher;

public class Main {
	//00325 - Identifying Legal Pascal Real Constants
	/**
	 * * zero or more
	 * + one or more
	 * . any character
	 * []
	 */
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		String s;
		while((s=bfr.readLine())!=null){
			if(!s.isEmpty() && s.charAt(0)=='*') break;
			s = s.trim();
			System.out.println(s+" is "+ (isMatch(s) ? "legal." : "illegal."));
		}
	}
	boolean isMatch(String s){
		boolean result = false;
		result = s.matches("[+-]?[0-9]+[.][0-9]+");//decimal case
		result |= s.matches("[+-]?[0-9]+[.][0-9]+[eE][+-]?[0-9]+");//decimal & exponent
		result |= s.matches("[+-]?[0-9]+[eE][+-]?[0-9]+");//no decimal as expo
		return result;
	}
}