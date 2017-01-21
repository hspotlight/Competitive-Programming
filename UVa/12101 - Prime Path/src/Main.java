import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//12101 - Prime Path
	char[] s, t;
	ArrayList<char[]> prime;
	ArrayList<ArrayList<Integer>> path;// path is incorrect
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		//pre-process find prime up to 9999 - 4 digits
		findPrime();
		//add path
		addPath();
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			s = st.nextToken().toCharArray();
			t = st.nextToken().toCharArray();
			//BFS - traversal
			System.out.println( BFS(indexOf(s), indexOf(t)) );
		}
	}
	int indexOf(char[] s){
		for(int i=0;i<prime.size();i++){
			if(equals(s,prime.get(i),4)) return i;
		}
		return -1;
	}
	int BFS(int s, int t){
		boolean visited[] = new boolean[prime.size()+1];
		Queue<Node> qe = new LinkedList<Node>();
		qe.add(new Node(s,0));
		while(!qe.isEmpty()){
			Node v1 = qe.poll();
			if(visited[v1.id]) continue;
			if(v1.id==t) return v1.move;
			visited[v1.id] = true;

			for(Integer c : path.get(v1.id)){
				if(!visited[c]){
					qe.add(new Node(c,v1.move+1));
				}
			}
		}
		return -1;
	}
	class Node{
		int id;
		int move;
		Node(int i, int m){
			id = i;
			move = m;
		}
	}
	boolean equals(char[] source, char[] target,int limit){
		int sum = 0;
		for(int i=0;i<4;i++){
			if(source[i]==target[i]) sum++;
		}
		return sum==limit;
	}
	void addPath(){
		path = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<prime.size();i++) path.add(new ArrayList<Integer>());
		for(int i=0;i<path.size();i++){
			char[] s  = prime.get(i);
			for(int j=0;j<path.size();j++){
				if(i==j) continue; // itself
				char[] t = prime.get(j);
				if(equals(s,t,3)) path.get(i).add(j);
			}
		}
	}
	void findPrime(){
		prime = new ArrayList<char[]>();
		boolean isComposite[] = new boolean[10000];
		for(int i=3;i<10000;i+=2){
			if(isComposite[i]) continue;
			//this is next prime; add
			if(i>999) prime.add( int2Char(i) );
			for(int sum = i<<1; sum <10000; sum+=i){
				isComposite[sum] = true;
			}
		}
	}
	char[] int2Char(int prime){
		char[] out = new char[4];
		int i = 3;
		while(i>=0){
			out[i] = (char)((prime % 10)+'0');
			prime /= 10;
			i--;
		}
		return out;
	}

}
