import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00344 - Roman Digititis
	StreamTokenizer stk;
	int I=0, V=1, X=2, L=3, C=4;
	int[][] val;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int n = 100;
		val = new int[5][101];
		for(int k=1;k<=n;k++){
			String s = left(k/10) + right(k%10);
			//concat
			for(int j=0;j<5;j++) val[j][k] = val[j][k-1];
			for(int j=0;j<s.length();j++){
				switch(s.charAt(j)){
					case 'i': val[I][k]++; break;
					case 'v': val[V][k]++; break;
					case 'x': val[X][k]++; break;
					case 'l': val[L][k]++; break;
					case 'c': val[C][k]++; break;
				}
			}
		}
		int tc;
		while((tc=readInt())!=0){
			System.out.println(tc+": "+val[I][tc]+" i, "+val[V][tc]+" v, "+val[X][tc]+" x, "+val[L][tc]+" l, "+val[C][tc]+" c");
		}
	}
	String left(int n){
		String[] val = {"","x","xx","xxx","xl","l","lx","lxx","lxxx","cx","c"};
		return val[n];
	}
	String right(int n){
		String[] val = {"","i","ii","iii","iv","v","vi","vii","viii","ix"};
		return val[n];
	}
}