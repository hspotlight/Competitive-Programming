import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11530 - SMS Typing
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int nCases = Integer.parseInt(bfr.readLine().trim());
		for(int tc=1;tc<=nCases;tc++){
			System.out.println("Case #"+tc+": "+ans(bfr.readLine().toCharArray()));
		}
	}
	int ans(char[] s){
		int sum = 0;
		for(int i=0;i<s.length;i++){
			if(s[i]==' ') sum++;
			if('a'<=s[i]){
				if(s[i]<='c') sum += (s[i]-'a') + 1;
				else if(s[i]<='f') sum += (s[i]-'d') + 1;
				else if(s[i]<='i') sum += (s[i]-'g') + 1;
				else if(s[i]<='l') sum += (s[i]-'j') + 1;
				else if(s[i]<='o') sum += (s[i]-'m') + 1;
				else if(s[i]<='s') sum += (s[i]-'p') + 1;
				else if(s[i]<='v') sum += (s[i]-'t') + 1;
				else if(s[i]<='z') sum += (s[i]-'w') + 1;	
			}
		}
		return sum;
	}
}