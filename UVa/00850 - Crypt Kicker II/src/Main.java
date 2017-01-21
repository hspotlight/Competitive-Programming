import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	//00850 - Crypt Kicker II
	StreamTokenizer stk;
	char[] map;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		String s;
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			ArrayList<String> line = new ArrayList<String>();
			boolean found = false;
			while((s=bfr.readLine())!=null){
				if(s.isEmpty()) break;
				line.add(s);
				if(!found) found = isFound(s);
			}
			if(!found) System.out.println("No solution.");
			else for(String str : line) System.out.println(decrypt(str));
			if(tc>0) System.out.println();
		}
	}
	String decrypt(String s){
		char[] line = s.toCharArray();
		String out = "";
		for(int i=0;i<line.length;i++){
			if(line[i]==' ') out+=" ";
			else out += map[line[i]-'a'];
		}
		return out;
	}
	boolean isFound(String s){
		char[] line = s.toCharArray();
		if(line.length!=43) return false;
		if(line[3] != ' ' || line[9] != ' ' || line[15] != ' ' || line[19] != ' ' || line[25] != ' ' || line[30] != ' ' || line[34] != ' ' || line[39] != ' ') return false;
		char[] t = "the quick brown fox jumps over the lazy dog".toCharArray();
		int encode[] = countFreq(line), decode[] = countFreq(t);
		for(int i=0;i<encode.length;i++)
			if(encode[i]!=decode[i]) return false;
		map = new char[26];
		for(int i=0;i<line.length;i++) {
			if(line[i]==' ') continue;
			map[line[i]-'a'] = t[i];
		}
		return true;
	}
	int[] countFreq(char[] line){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int index = 0;
		int pattern[] = new int[26];
		for(int i=0;i<line.length;i++){
			char c = line[i];
			if(c==' ') continue;
			if(!map.containsKey(c)) map.put(c, index++); 
			pattern[map.get(c)]++;
		}
		return pattern;
	}
}
