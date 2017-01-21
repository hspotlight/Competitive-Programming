import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00230 - Borrowers
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	String s[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		s = new String[10];
		preprocess();
		while(bfr.ready()){
			System.out.print(s[readInt()]);
		}
	}
	void preprocess(){
		s[2] =  "00\n"+
				"01\n"+
				"81\n";
		s[4] =  "0000\n"+
				"0001\n"+
				"2025\n"+
				"3025\n"+
				"9801\n";
		s[6] =  "000000\n"+
				"000001\n"+
				"088209\n"+
				"494209\n"+
				"998001\n";
		s[8] =  "00000000\n"+
				"00000001\n"+
				"04941729\n"+
				"07441984\n"+
				"24502500\n"+
				"25502500\n"+
				"52881984\n"+
				"60481729\n"+
				"99980001\n";
	}
}
