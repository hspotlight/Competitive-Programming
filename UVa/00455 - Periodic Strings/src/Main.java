import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//00455 - Periodic Strings
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			String line = bfr.readLine();
			int min = line.length();
			int i = 0;
			for(int j=i+1;j<line.length();j++){
				if(line.charAt(i)==line.charAt(j)){
					int len = j-i;
					int k = 0;
					String pattern = line.substring(i, i+len), compare = ""; 
					while ((j+len)+k <= line.length()){//shift k
						compare = line.substring(j+k, (j+k)+len);
						if(!pattern.equals(compare)) break;
						k += len;
					}
					if(j+k == line.length()) //
						min = Math.min(min, len);
					
				}
			}
			System.out.println(min);
			if(tc>0) System.out.println();
		}
	}
}