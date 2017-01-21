import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	//11286 - Conformity
	int n;
	Map<String, Integer> map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			int max = Integer.MIN_VALUE;
			n = Integer.parseInt(bfr.readLine());
			if(n==0) break;
			map = new HashMap<String, Integer>();
			for(int i=0;i<n;i++){
				String query = "";
				StringTokenizer st = new StringTokenizer(bfr.readLine());
				ArrayList<Integer> course = new ArrayList<Integer>();
				while(st.hasMoreTokens()) course.add(Integer.parseInt(st.nextToken()));
				Collections.sort(course);
				for(Integer c : course)
					query += c;
				if(!map.containsKey(query)) map.put(query, 1);
				else map.put(query, map.get(query) + 1);
				if(map.get(query)>max) max = map.get(query);
			}
			Set<String> s = map.keySet();
			int count = 0;
			for(String c : s){
				if(map.get(c)==max) count+=max;
			}
			System.out.println(count);
		}
	}
}
