import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
	//10226 - Hardwood Species
	Map<String,Integer> map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			map = new HashMap<String,Integer>();
			String s = bfr.readLine();
			
			int count = 0;
			while(!s.equals("")){
				if(map.containsKey(s)) map.put(s, map.get(s)+1);
				else map.put(s, 1);
				count++;
				s = bfr.readLine();
				if(s==null) break;
			}
			
			double factor = 100.0/count;
			Set<String> set = map.keySet();
			ArrayList<String> temp = new ArrayList<String>(set);
			Collections.sort(temp); 
			for(String key : temp){
				double ans = map.get(key) * factor;
				System.out.println(key+" "+ new DecimalFormat("0.0000").format(ans));
			}
			if(bfr.ready()){
				System.out.println();
			}
		}
	}
}
