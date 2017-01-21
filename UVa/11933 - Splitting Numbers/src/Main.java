import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//11933 - Splitting Numbers
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			if(n==0) break;
			String s = Integer.toBinaryString(n);
			String a = "";
			String b = "";
			boolean odd = true;
			for(int i=s.length()-1;i>=0;i--){
				if(s.charAt(i)=='1'){
					if(odd){//a
						a += "1";
						odd = false;
						b += "0";
					}
					else{//b
						b += "1";
						odd = true;
						a += "0";
					}
				}
				else { a += "0"; b+= "0"; }
			}
			System.out.println(Integer.parseInt(rev(a), 2)+" "
						      +Integer.parseInt(rev(b), 2));
		}
	}
	public String rev(String s){
		String out = "";
		for(int i=s.length()-1;i>=0;i--){
			out += s.charAt(i);
		}
		return out;
	}
}
