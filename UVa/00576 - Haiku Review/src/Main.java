import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//00576 - Haiku Review
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			String haiku = bfr.readLine();
			if(haiku.equals("e/o/i")) break;
			String ans = validate(new StringTokenizer(haiku,"/"));
			System.out.println(ans);
		}
	}
	String validate(StringTokenizer st){
		int perLine[] = {5,7,5};
		int line = 1;
		while(st.hasMoreTokens()){
			String[] words = st.nextToken().split("\\s+");
			int nSyllables = 0;
			for(String word : words){
				word = word.replaceAll("[aeiouy]+", "A").replaceAll("[a-z]+", "");
				//consecutive vowel represented as A;count them na ja
				//Assumption haiku will contain only lower-case
				nSyllables += word.length();
			}
			if(nSyllables != perLine[line-1]) return ""+line;
			line++;
		}
		return "Y";
	}
}