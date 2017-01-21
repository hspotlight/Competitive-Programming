import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Chin{
	//11987 - Almost Union-Find
	static StreamTokenizer st;
	static int getInt() throws Exception {st.nextToken(); return (int)st.nval;}
	static final int MAX = 100001;
	public static void main(String args[]) throws Exception {
		try {System.setIn(new FileInputStream("in.txt"));} catch(Exception e) {}
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int n,m;
		for(int i = 0;i < MAX;i++)	set.add(new TreeSet<Integer>());
		while(st.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int)st.nval; m = getInt();
			for(int i = 1;i <= n;i++) {sum[i] = i; root[i] = i; set.get(i).clear(); set.get(i).add(i);}
			while(m-- > 0) {
				switch(getInt()){
				case 1: merge(getInt(),getInt()); break;
				case 2: move(getInt(),getInt()); break;
				case 3: out.write(ans(getInt())); break;
				}
			}
		}
		out.flush();
	}
	static int[] root = new int[MAX];
	static long[] sum = new long[MAX];
	static ArrayList<Set<Integer>> set = new ArrayList<Set<Integer>>(MAX);
	static String ans(int n) {return set.get(root[n]).size() + " " + sum[root[n]] + "\n";}
	static void merge(int a,int b) {
		a = root[a]; b = root[b];
		if(a == b)	return;
		if(set.get(a).size() < set.get(b).size()) {a ^= b; b ^= a; a ^= b;}
		for(Integer x:set.get(b)) {set.get(a).add(x); root[x] = a;}
		sum[a] += sum[b]; sum[b] = 0;
		set.get(b).clear();
	}
	static void move(int from,int to){
		int rootFrom = root[from], rootTo = root[to];
		set.get(rootFrom).remove(from);
		set.get(rootTo).add(from);
		sum[rootFrom] -= from;
		sum[rootTo] += from;
		root[from] = rootTo;
	}
}
