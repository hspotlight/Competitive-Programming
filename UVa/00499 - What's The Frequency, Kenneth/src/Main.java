import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00499 - What's The Frequency, Kenneth
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
			int freq[] = new int[52];//A-Z//a-z
			char[] s = bfr.readLine().toCharArray();
			int max = 0, f = 0;
			for(int i=0;i<s.length;i++){
				if('A' <= s[i] && s[i] <= 'Z') f = ++freq[ s[i] - 'A'];
				if('a' <= s[i] && s[i] <= 'z') f = ++freq[ s[i] - 'a' + 26];
				max = Math.max(max, f);
			}
			String line = "";
			for(int i=0;i<26;i++)
				if(freq[i]==max) line += ((char)(i+'A'));
			for(int i=26;i<52;i++)
				if(freq[i]==max) line += ((char)(i+'a'-26));
			System.out.println(line+" "+max);
		}
	}
}