import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//00621 - Secret Research
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			String line = bfr.readLine();	
			String ans = encrypt(line);
			System.out.println(ans);
		}
	}
	String encrypt(String line){
		if(line.equals("1")||line.equals("4")||line.equals("78")) return "+";
		else if(line.length()>2 && line.substring(line.length()-2, line.length()).equals("35")) return "-";
		else if (line.length()>2 && line.charAt(0)=='9' && line.charAt(line.length()-1)=='4') return "*";
		return "?";
	}
}
