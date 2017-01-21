import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//10205 - Stack 'em Up
	int[] deck;
	int[][] pattern;
	String[] val = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
	String[] suit= {"Clubs","Diamonds","Hearts","Spades"};
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		bfr.readLine();
		while(tc-->0){
			int n = readInt();
			deck = new int[53];
			for(int i=1;i<=52;i++) deck[i] = i;
			pattern = new int[n][53];
			for(int i=0;i<n;i++){
				for(int j=1;j<=52;j++){
					pattern[i][j] = readInt();
				}
			}
			//shuffles
			String s = bfr.readLine();
			while(s.equals("")) s = bfr.readLine();
			while(s != null){
				if(s.equals("")) break;
				shuffle(Integer.parseInt(s)-1);
				s = bfr.readLine();
			}
			//output
			for(int i=1;i<=52;i++){
				sb.append(valueSuit(deck[i]-1)+"\n");
			}
			if(tc>0) sb.append("\n"); 
		}
		bfw.write(""+sb);
		bfw.flush();
	}
	public String valueSuit(int k){
		int v = k%13, s = k/13;
		return val[v]+" of "+suit[s];
	}
	void shuffle(int k){
		int[] temp = new int[53];
		for(int i=1;i<=52;i++){
			temp[i] = deck[ pattern[k][i] ];
		}
		deck = temp;
	}
}
