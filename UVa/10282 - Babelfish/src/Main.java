import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	//10282 - Babelfish
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		Map<String,String> map = new HashMap<String,String>();
		while(bfr.ready()){
			String s = bfr.readLine();
			StringTokenizer st = new StringTokenizer(s);
			if(st.countTokens()==0) break;
			String val = st.nextToken();
			String key = st.nextToken();
			map.put(key, val);
		}
		while(bfr.ready()){
			String query = bfr.readLine();
			if(map.containsKey(query)) System.out.println(map.get(query));
			else System.out.println("eh");
		}
	}
}
