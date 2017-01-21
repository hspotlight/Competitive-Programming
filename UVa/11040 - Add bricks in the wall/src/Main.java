import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	//11040 - Add bricks in the wall
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = readInt();
		while(tc-->0){
			int block[][] = new int[10][];
			for(int i=1;i<=9;i++) block[i] = new int[i+1];
			for(int i=1;i<=9;i+=2){
				for(int j=1;j<=i;j+=2){
					block[i][j] = readInt();
				}
			}	
			for(int i=1;i<=7;i+=2){//header
				for(int j=1;j<=i;j+=2){
					/*Z*/block[i+2][j+1] = ( block[i][j] - block[i+2][j] - block[i+2][j+2]) / 2;
					/*Y*/block[i+1][j] = block[i+2][j] + block[i+2][j+1];
					/*X*/block[i+1][j+1] = block[i+2][j+1] + block[i+2][j+2];
				}
			}
			for(int i=1;i<=9;i++){
				String s = "";
				for(int j=1;j<=i;j++){
					s += s.isEmpty()? (""+block[i][j]) : (" "+block[i][j]);
				}
				System.out.println(s);
			}
		}
	}
}