import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00713 - Adding Reversed Numbers
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			String A = st.nextToken(), B = st.nextToken();
			if(A.length() < B.length()){
				String tmp = A;
				A = B;
				B = tmp;
			}
			int diff = A.length() - B.length();
			if(diff>0) B += new String(new char[diff]).replaceAll("\0", "0");
			
			char[] a = A.toCharArray(), b = B.toCharArray();
			int cin = 0;
			String out = "";
			boolean OK = false;
			for(int i=0;i<a.length;i++){
				int sum = a[i] - '0' + b[i] - '0' + cin;
				if(sum>9){
					sum %= 10;
					cin = 1;
				}
				else cin = 0;
				if (sum > 0) OK =  true;
				
				if(OK) out += (char)(sum + '0');
			}
			if(cin==1) out += "1";
			
			System.out.println(out);
		}
	}
}
