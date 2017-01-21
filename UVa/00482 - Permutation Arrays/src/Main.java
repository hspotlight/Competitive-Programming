import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//00482 - Permutation Arrays
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			//line1
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int[] indexA = new int[st.countTokens()];
			for(int i=0;i<indexA.length;i++) 
				indexA[i] = Integer.parseInt(st.nextToken()) - 1;
			//line2
			st = new StringTokenizer(bfr.readLine());
			String[] StringA = new String[st.countTokens()];
			for(int i=0;i<StringA.length;i++) 
				StringA[ indexA[i] ] = st.nextToken();
			//output
			for(String s : StringA)
				System.out.println(s);
			
			if(tc>0) System.out.println();
		}
		
	}
}