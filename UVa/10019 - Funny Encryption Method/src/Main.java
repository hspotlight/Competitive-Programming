import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10019 - Funny Encryption Method
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			int N = Integer.parseInt(bfr.readLine());
			String s = Integer.toBinaryString(N);
			int b1 = countOnes(s);
			int b2 = 0;
			s = ""+N;
			for(int i=0;i<s.length();i++)
				b2 += val(s.charAt(i));
			System.out.println(b1+" "+b2);
		}
	}
	public int val(char c){
		String s = "0123456789abcdef";
		int[] val ={0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4};
		return val[s.indexOf(c)];
	}
	public int countOnes(String s){
		int count = 0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='1') count++;
		}
		return count;
	}
}
