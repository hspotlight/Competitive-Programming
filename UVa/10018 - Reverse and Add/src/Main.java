import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10018 - Reverse and Add
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			String s = bfr.readLine();
			System.out.println(ans(s));
		}
	}
	String ans(String s){
		int count = 0;
		while(!par(s.toCharArray())){
			String temp = "";
			for(int i=s.length()-1;i>=0;i--) temp += s.charAt(i);
			long A = Long.parseLong(s);
			long B = Long.parseLong(temp);
			s = "" + (A+B);
			count++;
		}
		return count+" "+s;
	}
	boolean par(char[] s){
		for(int i=0;i<s.length/2;i++){
			if(s[i]!=s[(s.length-1) - i]) return false;
		}
		return true;
	}
}
