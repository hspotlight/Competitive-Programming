import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	//11308 - Bankrupt Baker
	int m,n,b;
	HashMap<String,Integer> recipe;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			String binder = bfr.readLine();
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			//m
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			for(int i=0;i<m;i++){
				st = new StringTokenizer(bfr.readLine());
				String name = st.nextToken();
				int cost = Integer.parseInt(st.nextToken());
				map.put(name, cost);
			}
			//n
			recipe = new HashMap<String,Integer>();
			for(int i=0;i<n;i++){
				String name = bfr.readLine();
				int k = Integer.parseInt(bfr.readLine());
				int cost = 0;
				for(int j=0;j<k;j++){
					st = new StringTokenizer(bfr.readLine());
					cost += map.get(st.nextToken()) * Integer.parseInt(st.nextToken());
				}
				if(cost<=b)recipe.put(name, cost);
			}
			//output
			String[] list = sort(new ArrayList<String>(recipe.keySet()));
			System.out.println(binder.toUpperCase());
			if(list.length==0) System.out.println("Too expensive!");
			else{
				for(int i=0;i<list.length;i++){
					System.out.println(list[i]);//+" "+recipe.get(list[i]));
				}
			}
			System.out.println();
		}
	}
	String[] sort(ArrayList<String> list){
		String[] s = new String[list.size()];
		for(int i=0;i<list.size(); s[i] = list.get(i++));
		for(int i=0;i<s.length-1;i++){
			int cur = recipe.get(s[i]);
			for(int j=i+1;j<s.length;j++){
				int next = recipe.get(s[j]);
				if(cur > next){
					String temp = s[i];
					s[i] = s[j];
					s[j] = temp;
					cur = next;
				}
				else if(cur==next && s[i].compareTo(s[j]) > 0){
						String temp = s[i];
						s[i] = s[j];
						s[j] = temp;
						cur = next;
				}
			}
		}
		return s;
	}
}
