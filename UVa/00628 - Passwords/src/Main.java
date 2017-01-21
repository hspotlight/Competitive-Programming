import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//00628 - Passwords
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String[] s;
	char[] pattern;
	StringBuilder sb;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		sb = new StringBuilder("");
		while(bfr.ready()){
			sb.append("--\n");
			int n = Integer.parseInt(bfr.readLine());
			s = new String[n];
			for(int i=0;i<n;i++) s[i] = bfr.readLine();
			int m = Integer.parseInt(bfr.readLine());
			for(int i=0;i<m;i++){
				pattern = bfr.readLine().toCharArray();
				print(0,"");
			}
		}
		bfw.write(""+sb);
		bfw.flush();
	}
	void print(int index, String path){
		if(index==pattern.length){ sb.append(path+"\n"); }
		else{
			if(pattern[index]=='0'){
				for(int i=0;i<10;i++) print(index+1, path+i);
			}
			else{
				for(int i=0;i<s.length;i++) print(index+1, path+s[i]);
			}
		}
	}
}
