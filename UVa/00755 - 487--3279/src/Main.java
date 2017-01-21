import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
	//00755 - 487--3279
	HashMap<String,Integer> map;
	int[] value = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
	int n;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			map = new HashMap<String,Integer>();
			n = Integer.parseInt(bfr.readLine());
			for(int i=0;i<n;i++){
				String s = bfr.readLine().replaceAll("-", "");
				String standard = convert(s.toCharArray());
				if(map.containsKey(standard)) map.put(standard, map.get(standard)+1);
				else map.put(standard, 1);
			}
			boolean duplicate = false;
			ArrayList<String> key = new ArrayList<String>(map.keySet());
			Collections.sort(key);
			for(String s : key){
				int val = map.get(s);
				if(val>1){
					duplicate = true;
					System.out.println(s+" "+val);
				}
			}
			if(!duplicate) System.out.println("No duplicates.");
			if(bfr.ready()) System.out.println();
		}
	}
	String convert(char[] s){
		String out = "";
		if(s.length!=7) return "";
		for(int i=0;i<7;i++){
			if('A'<=s[i] && s[i]<='Z') out += value[ s[i]-'A' ];
			else out += s[i];
			if(i==2) out+= "-";
		}
		return out;
	}
}
