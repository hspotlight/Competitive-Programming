import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//10340 - All in All
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			char[] s = st.nextToken().toCharArray();
			String t = st.nextToken();
			int index = 0;
			int i = 0;
			while(i<s.length){
				index = t.indexOf(s[i], index);
				if(index==-1) break;//not found
				i++; index++;//shift
			}
			if(index==-1) System.out.println("No");
			else System.out.println("Yes");
		}
	}
}
