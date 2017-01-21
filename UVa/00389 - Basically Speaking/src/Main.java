import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00389 - Basically Speaking
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			char[] src = st.nextToken().toCharArray();
			int baseFrom = Integer.parseInt(st.nextToken());
			int baseTo = Integer.parseInt(st.nextToken());
			//convert baseFrom to base 10
			int sum = toBase10(src, baseFrom);
			//convert base10 to baseTo
			System.out.println(String.format("%7s", toBaseTo(sum, baseTo)));
		}
	}
	String toBaseTo(int val, int baseTo){
		StringBuilder sb = new StringBuilder();
		if(val==0) sb.append("0");
		else{
			while(val>0){
				int rem = val%baseTo;
				val = val/baseTo;
				sb.insert(0, itoa(rem));
			}
		}
		return sb.length() > 7? "ERROR" : sb.toString();
	}
	int toBase10(char[] s, int baseFrom){
		int sum = 0;
		for(int i=s.length-1, j = 0;i>=0;i--, j++){//i = index, j = degree
			sum += (int)(atoi(s[i]) * Math.pow(baseFrom, j));
		}
		return sum;
	}
	char itoa(int i){
		return (char)( i >= 10 ? ('A' + i - 10) : ('0' + i));
	}
	int atoi(char c){
		String s = "0123456789ABCDEF";
		return s.indexOf(c);
	}
}
