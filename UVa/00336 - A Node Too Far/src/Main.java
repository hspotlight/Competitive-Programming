import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//00336 - A Node Too Far
	ArrayList<Integer> node;
	ArrayList<ArrayList<Integer>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));	
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		String s = bfr.readLine();
		while(bfr.ready()){
			int n = Integer.parseInt(s);
			if(n==0) break;
			int i = 0;
			s = bfr.readLine();
			boolean finished = false;
			node = new ArrayList<Integer>();
			path = new ArrayList<ArrayList<Integer>>();
			while(!finished){ //blank line
				StringTokenizer st = new StringTokenizer(s);
				while(st.hasMoreTokens()){
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					if(i<n){
						//add path a-b and b-a
						if(node.indexOf(a) == -1) {
							node.add(a);
							path.add(new ArrayList<Integer>());
						}
						if(node.indexOf(b) == -1) {
							node.add(b);
							path.add(new ArrayList<Integer>());
						}
						path.get(node.indexOf(a)).add(node.indexOf(b));
						path.get(node.indexOf(b)).add(node.indexOf(a));
						i++;
					}
					else{//query
						if(a==0 && b==0) {
							finished = true;
							break;//end query
						}
						int ans = traversal(a,b);
						System.out.println("Case "+c+": "+ans+" nodes not reachable from node "+a+" with TTL = "+b+".");
						c++;
					}
				}
				s = bfr.readLine();
			}
			//s contain blank line
			s = bfr.readLine();
		}
	}
	int traversal(int s, int TTL){
		if(node.indexOf(s)==-1) return node.size(); //unreachable
		int count = 0;
		boolean visited[] = new boolean[node.size()];
		Queue<Node>    qe = new LinkedList<Node>();
		qe.add(new Node(node.indexOf(s),0));
		visited[node.indexOf(s)] = true;
		count++;
		while(!qe.isEmpty()){
			Node node = qe.poll();
			if(node.move>TTL) break;
			count++;
			int current = node.node;
			for(Integer c : path.get(current)){
				if(!visited[c]){
					visited[c] = true;
					qe.add(new Node(c,node.move+1));
				}
			}
		}
		return node.size() - count + 1;
	}
	public class Node{
		int node;
		int move;
		Node(int n,int m){
			node = n;
			move = m;
		}
	}
}
