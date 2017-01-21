import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	//10100 - Longest Match
	String[] tokens;
	int wordCounter;
	ArrayList<Integer> S = new ArrayList<Integer>();
	ArrayList<Integer> T = new ArrayList<Integer>();
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(bfr.ready()){
			String s = bfr.readLine();
			String t = bfr.readLine();
			
			map.clear();
			System.out.print(String.format("%2d. ", tc));
			if(s.isEmpty() || t.isEmpty()) System.out.println("Blank!");
			else{
				S.clear(); T.clear();
				wordCounter = 0;
				
				tokens = s.split("\\W+");
				for(String token : tokens){
					if(!map.containsKey(token)) map.put(token, wordCounter++);
					S.add( map.get(token) );
				}
				
				tokens = t.split("\\W+");
				for(String token : tokens){
					if(!map.containsKey(token)) map.put(token, wordCounter++);
					T.add( map.get(token) );
				}
				
				System.out.println("Length of longest match: "+longestMatch());
			}
			tc++;
		}
	}
	int longestMatch(){ //LCS
		int map[][] = new int[S.size()+1][T.size()+1];
		for(int i=1;i<=S.size();i++){
			for(int j=1;j<=T.size();j++){
				map[i][j] = Math.max(map[i-1][j],
						Math.max(map[i][j-1], map[i-1][j-1] + (S.get(i-1)==T.get(j-1) ? 1 : 0)));
			}
		}
		return map[S.size()][T.size()];
	}
}
