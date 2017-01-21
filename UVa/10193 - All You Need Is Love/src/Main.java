import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	//10193 - All You Need Is Love
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(bfr.readLine());
		for(int tc=1;tc<=TC;tc++){
			int s1 = Integer.parseInt(bfr.readLine(), 2);
			int s2 = Integer.parseInt(bfr.readLine(), 2);
			
			if(s1 > s2){//swap
				int t = s1;
				s1 = s2;
				s2 = t;
			}
			
			bfw.write("Pair #"+tc+": ");
			if(getGCD(s1, s2) > 1)
				bfw.write("All you need is love!\n");
			else bfw.write("Love is not all you need!\n");
		}
		bfw.flush();
	}
	int getGCD(int a, int b){
		int rem = a%b;
		return rem == 0? b : getGCD(b, rem);
	}
}
