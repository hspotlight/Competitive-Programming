import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	//11233 - Deli Deli
	StringTokenizer st;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		st = new StringTokenizer(bfr.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		HashMap<String, String> map = new HashMap<String, String>();
		String vowel = "aeiou";
		for(int i=0;i<n;i++){
			st = new StringTokenizer(bfr.readLine());
			map.put(st.nextToken(), st.nextToken());
		}
		for(int i=0;i<m;i++){
			String s = bfr.readLine();
			String out = "";
			if(map.containsKey(s)) out = map.get(s);
			else if(vowel.indexOf(s.charAt(s.length()-2))==-1 && s.charAt(s.length()-1)=='y') out = s.substring(0,s.length()-1) + "ies";//
			else if(s.charAt(s.length()-1)=='o') out = s.substring(0,s.length()) + "es";//
			else if(s.charAt(s.length()-1)=='s') out = s.substring(0,s.length()) + "es";//
			else if(s.charAt(s.length()-1)=='x') out = s.substring(0,s.length()) + "es";//
			else if(s.charAt(s.length()-2)=='c' && s.charAt(s.length()-1)=='h') out = s.substring(0,s.length()) + "es";//
			else if(s.charAt(s.length()-2)=='s' && s.charAt(s.length()-1)=='h') out = s.substring(0,s.length()) + "es";//
			else out = s + "s";
			System.out.println(out);
		}
	}
}