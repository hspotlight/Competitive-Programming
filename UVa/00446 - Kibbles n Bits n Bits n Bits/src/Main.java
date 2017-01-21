import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00446 - Kibbles n Bits n Bits n Bits
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			
			String A = st.nextToken();
			char op = st.nextToken().charAt(0);
			String B = st.nextToken();
			
			Integer a = Integer.parseInt(A, 16);
			Integer b = Integer.parseInt(B, 16);
			
			A = addLeadingZero(Integer.toBinaryString(a));
			B = addLeadingZero(Integer.toBinaryString(b));
			
			System.out.print(A+" "+op+" "+B+" = ");
			if(op == '+'){
				System.out.println(a+b);
			}
			else{
				System.out.println(a-b);
			}
		}
	}
	String addLeadingZero(String s){
		int added = 13 - s.length();
		while(added>0){
			s = "0" + s;
			added--;
		}
		return s;
	}
}
