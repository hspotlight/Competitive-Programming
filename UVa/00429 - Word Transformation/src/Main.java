import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//00429 - Word Transformation
	ArrayList<ArrayList<String>> word;
	ArrayList<ArrayList<ArrayList<Integer>>> map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			word = new ArrayList<ArrayList<String>>();
			map = new ArrayList<ArrayList<ArrayList<Integer>>>();
			for(int i=0;i<10;i++) {
				word.add(new ArrayList<String>());
				map.add( new ArrayList<ArrayList<Integer>>() );
			}
			//add all word into dictionary
			String s = bfr.readLine();
			while(!s.equals("*")){
				int len = s.length()-1;
				word.get(len).add(s);
				map.get(len).add(new ArrayList<Integer>());
				s = bfr.readLine();
			}
			//Pre-processing
			for(int i=0;i<10;i++){
				//each word that same length
				for(int j=0;j<word.get(i).size();j++){//retrieve every word
					
					String current = word.get(i).get(j);
					for(int k=j+1;k<word.get(i).size();k++){//every word except itself
						
						String compare = word.get(i).get(k);
						if(check(current, compare)){//traversal
							map.get(i).get(j).add(k);
							map.get(i).get(k).add(j);
						}
					}
				}
			}
			//part query
			String query = bfr.readLine();
			while(query!=null && !query.equals("")){
				StringTokenizer st = new StringTokenizer(query);
				System.out.println(traversal(st.nextToken(),st.nextToken()));
				query = bfr.readLine();
			}
			if(tc>0)System.out.println();
		}
	}
	boolean check(String A, String B){
		int diff = 0;
		for(int i=0;i<A.length();i++){
			char a = A.charAt(i);
			char b = B.charAt(i);
			if(a!=b) diff++;
		}
		return diff==1;
	}
	String traversal(String source, String dest){
		int len = source.length()-1;
		int move = -1;
		ArrayList<ArrayList<Integer>> relation = map.get(len);
		boolean visited[] = new boolean[relation.size()];
		int s = -1,t = -1;
		for(int i=0;i<word.get(len).size();i++){
			if(source.equals(word.get(len).get(i))) s = i;
			if(dest.equals(word.get(len).get(i)))   t = i;
		}
		
		Queue<Node> qe = new LinkedList<Node>();
		qe.add(new Node(s,0));
		visited[s] = true;
		while(!qe.isEmpty()){
			Node now = qe.poll();
			int index = now.index;
			if(index==t){
				move = now.move;
				break;
			}
			for(Integer c : relation.get(index)){//connected
				if(!visited[c]){
					visited[c] = true;
					qe.add(new Node(c,now.move+1));
				}
			}
		}
		return source+" "+dest+" "+(move);
	}
	public class Node{
		int index;
		int move;
		public Node(int index, int move){
			this.index = index;
			this.move = move;
		}
	}
}

