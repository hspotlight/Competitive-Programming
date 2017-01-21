import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	//11991 - Easy Problem from Rujia Liu
	StreamTokenizer stk;
	int index;
	ArrayList<ArrayList<Integer>> list; //number -> position
	HashMap<Integer, Integer> map;
	int getIndex(int n){
		if(!map.containsKey(n)) { map.put(n, index++); list.add(new ArrayList<Integer>()); }
		return map.get(n);
	}
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			int n = readInt(), m = readInt();
			list = new ArrayList<ArrayList<Integer>>();
			map = new HashMap<Integer, Integer>();
			index = 0;
			for(int i=1;i<=n;i++){
				int index = getIndex(readInt()); //index of list
				list.get(index).add(i);//add position to list
			}
			for(int i=0;i<m;i++){
				int k = readInt()-1, v = readInt();
				if(map.containsKey(v)){
					int index = map.get(v); //OK
					if(k < list.get(index).size()) System.out.println(list.get(index).get(k));
					else System.out.println(0);
				}
				else System.out.println(0);
			}
		}
	}
}