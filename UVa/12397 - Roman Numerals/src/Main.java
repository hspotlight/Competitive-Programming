import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//12397 - Roman Numerals
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			String s = tho(n/1000) + hun((n%1000)/100) + ten((n%100)/10) + one(n%10);
			int val = 0;
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)=='i') val+=1;
				else if(s.charAt(i)=='v') val+=2;
				else if(s.charAt(i)=='x') val+=2;
				else if(s.charAt(i)=='l') val+=2;
				else if(s.charAt(i)=='c') val+=2;
				else if(s.charAt(i)=='d') val+=3;
				else if(s.charAt(i)=='m') val+=4;
			}
			System.out.println(val);
		}
	}
	String tho(int n){
		String[] val = {"","m","mm","mmm"};
		return val[n];
	}
	String hun(int n){
		String[] val = {"","c","cc","ccc","cd","d","dc","dcc","dccc","cm"};
		return val[n];
	}
	String ten(int n){
		String[] val = {"","x","xx","xxx","xl","l","lx","lxx","lxxx","xc"};
		return val[n];
	}
	String one(int n){
		String[] val = {"","i","ii","iii","iv","v","vi","vii","viii","ix"};
		return val[n];
	}
}