import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10473 - Simple Base Conversion
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			String line = bfr.readLine();
			try{
				int dec = Integer.parseInt(line);
				if(dec < 0) break;
				String s = Integer.toHexString(dec).toUpperCase();
				System.out.println("0x"+s);
			}
			catch(Exception e){//hex
				int val = 0;
				for(int i = 2; i<line.length();i++){
					val = val << 4;
					val += atoi(line.charAt(i));
				}
				System.out.println(val);
			}
		}
	}
	static int atoi(char c){
		if('0'<=c && c<='9') return c - '0';
		return c - 'A' + 10;
	}
}
