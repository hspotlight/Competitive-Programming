import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//10789 - Prime Frequency
	boolean isComposite[] = new boolean[2002];
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		int TC = Integer.parseInt(bfr.readLine());
		//find prime
		isComposite[0] = isComposite[1] = true;
		for(int i=2;i<2002;i += (i==2?1:2)){
			if(!isComposite[i]){
				for(int j=i*i;j<2000;j+=i)
					isComposite[j] = true;
			}
		}
		for(int tc = 1; tc <= TC; tc++){
			String out = "";
			int freq[] = new int[130];
			char[] c = bfr.readLine().toCharArray();
			for(int i=0;i<c.length;i++){
				freq[ c[i] ]++;
			}
			for(int i=48;i<130;i++){
				if(!isComposite[ freq[i] ]) out += ""+((char)i);
			}
			bfw.write("Case "+tc+": "+ (!out.isEmpty()?out:"empty")+"\n");
		}
		bfw.flush();
		bfw.close();
	}
}