import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//10276 - Hanoi Tower Troubles Again!
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			int nPeg = Integer.parseInt(bfr.readLine());
			int peg[] = new int[nPeg+1];
			int ball = 1;
			while(true){
				boolean stack = false;
				for(int i=1;i<=nPeg;i++){
					int top = peg[i];
					int sqrt = (int)Math.sqrt(top+ball);
					if(sqrt*sqrt == top + ball || top == 0){
						peg[i] = ball++;
						stack = true;
						break;
					}
				}
				if(!stack) break;
			}
			System.out.println(ball-1);
		}
	}
}