import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11830 - Contract Revision
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			String pattern = st.nextToken();
			String text = st.nextToken();
			if(pattern.equals("0") && text.equals("0")) break;
			
			text = text.replaceAll(pattern, "");
			//find first non zero digit
			int pos = -1;
			for(int i=0;i<text.length();i++){
				if(text.charAt(i)!='0'){
					pos = i;
					break;
				}
			}
			System.out.println( pos == -1 ? "0" : text.substring(pos));
		}
	}
}

