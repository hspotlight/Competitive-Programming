import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11173 - Grey Codes
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int n = Integer.parseInt(st.nextToken()); //n bits
			int k = Integer.parseInt(st.nextToken()); //k values
			String val = "";
			while(k>0){
				int rightest = k & 1;
				val += rightest;
				k = k>>1;
			}
			int zero = n-val.length();
			while(zero-->0) val += "0";
			String grey = "";
			char bin[] = val.toCharArray();
			for(int i=0;i<bin.length-1;i++){
				grey += ((bin[i]==bin[i+1])? "0" : "1");
			}
			grey += bin[bin.length-1];
			grey = rev(grey);
			System.out.println(Integer.parseInt(grey,2));
		}
	}
	public String rev(String in){
		String out = "";
		for(int i=in.length()-1;i>=0;i--){
			out += in.charAt(i);
		}
		return out;
	}
}
