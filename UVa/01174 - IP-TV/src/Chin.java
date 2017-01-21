import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Chin {
	//01174 - IP-TV
	static StreamTokenizer st;
	static int getInt() throws Exception {st.nextToken(); return (int)st.nval;}
	static String getString() throws Exception {st.nextToken(); return st.sval;}
	static HashMap<String,Integer> map;
	static int X;
	static int getCity(String s) {
		if(map.containsKey(s))	return map.get(s);
		map.put(s,X);
		return X++;
	}
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		map = new HashMap<String,Integer>();
		int TC = getInt(),m,n,w;
		String fromS,toS;
		ArrayList<Edge> list = new ArrayList<Edge>();
		UnionFind uf;
		while(TC-- > 0) {
			m = getInt(); n = getInt();
			list.clear();
			map.clear(); X = 0;
			while(n-- > 0) {
				fromS = getString(); toS = getString(); w = getInt();
				list.add(new Edge(getCity(fromS),getCity(toS),w));
			}
			//process
			Collections.sort(list);
			uf = new UnionFind(m);
			w = 0;
			for(Edge e:list) {
				if(uf.find(e.u) == uf.find(e.v))	continue;
				w += e.w;
				uf.merge(e.u,e.v);
				if(uf.size() == 1)	break;
			}
			
			System.out.println(w);
			if(TC > 0)	System.out.println();
		}
	}
	static class Edge implements Comparable<Edge> {
		public int u,v,w;
		public Edge(int u,int v,int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge e) {return w - e.w;}
	}
	static class UnionFind {
		private int[] p;
		private int size;
		//Constructor
		public UnionFind(int n) {
			p = new int[n];
			size = n;
			for(int i = 0;i < n;i++)	p[i] = i;
		}
		//Method
		public int find(int n) {
			int x = n, t;
			while(n != p[n]) n = p[n];
			while(x != n) {
				t = p[x];
				p[x] = n;
				x = t;
			}
			return n;
		}
		public void merge(int a,int b) {
			int x = find(a), y = find(b);
			if(x == y)	return;
			p[x] = y;
			size--;
		}
		public int size() { return size; }
	}
}
