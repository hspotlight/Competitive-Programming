import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//01223 - Editor
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			System.out.println( ans(bfr.readLine() ));
		}
	}
	int ans(String s){
		int max = 0;
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<s.length();i++) {
			list.add( s.substring(i) );
			System.out.println(list.get(i));
		}
//		list.add("");
		Collections.sort(list);
		for(int i=0;i<list.size()-1;i++){
			int count = 0;
			while(count<list.get(i).length() && count<list.get(i+1).length()
					&&list.get(i).charAt(count)==list.get(i+1).charAt(count))
				count++;
			if(count>max) max = count;
		}
		return max;
	}
}
