import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//word can be delimited by pronunciation
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
			String s = bfr.readLine().replaceAll("[^A-Za-z ]", " ");
			StringTokenizer st = new StringTokenizer(s);
			int count = 0;
			while(st.hasMoreTokens()){
				String word = st.nextToken();
//				System.out.print("["+word+"] ");
				if(word.matches("[A-Za-z]+")){ 
					count++;
				}
			}
//			System.out.println();
			System.out.println(count);
		}
	}
}