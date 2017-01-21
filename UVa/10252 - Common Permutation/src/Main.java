import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	//10252 - Common Permutation
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	char[] s, t;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		String in;
		while((in=bfr.readLine())!= null){
			s = in.toCharArray(); t = bfr.readLine().toCharArray();
			something();
		}
	}
	void something(){
		int S[] = new int[26];
		int T[] = new int[26];
		for(int i=0;i<s.length;i++) S[ s[i]-'a' ]++;
		for(int i=0;i<t.length;i++) T[ t[i]-'a' ]++;
		String out = "";
		for(int i=0;i<26;i++){
			if(S[i]>0 && T[i]>0){
				int min = S[i]<T[i]? S[i] : T[i];
				for(int j=0;j<min;j++){
					out += ((char)(i+'a'));
				}
			}
		}
		System.out.println(out);
	}
}
