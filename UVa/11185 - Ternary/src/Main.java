import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	//11185 - Ternary
	int N;
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while((N = Integer.parseInt(bfr.readLine()))>=0){
			//solution 1
			System.out.println(Integer.toString(N, 3));
			//solution 2 (BruteForce)
//			StringBuilder sb = new StringBuilder();
//			if(N==0) System.out.println(0);
//			else{
//				while(N>0){
//					sb.insert(0, ""+N%3);
//					N /= 3;
//				}
//				System.out.println(sb);
//			}
		}
	}
}

