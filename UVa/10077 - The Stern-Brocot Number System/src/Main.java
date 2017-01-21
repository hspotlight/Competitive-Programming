import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10077 - The Stern-Brocot Number System
	int U, L;
	Node target;
	double targetVal;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			U = readInt(); L = readInt();
			if(U==1 && L==1) break;
			target = new Node(U,L);
			targetVal = U*1.0/L;
			walk();
		}
	}
	void walk(){
		//initial
		Node left = new Node(0,1); Node right= new Node(1,0);
		Node mid  = createMid(left,right);
		String path = "";
		while(true){
			if(mid.equals(target)) break;
			double midVal = mid.up*1.0/mid.low;
			if(targetVal < midVal){ //left
				path += "L";
				right = mid;
				mid = createMid(left,right);
			}
			else{
				path += "R";
				left = mid;
				mid = createMid(left,right);
			}
		}
		System.out.println(path);
	}
	public Node createMid(Node left, Node right){
		return new Node(left.up+right.up, left.low+right.low);
	}
	class Node{
		int up, low;
		public Node(int up, int low){
			this.up = up;
			this.low = low;
		}
		boolean equals(Node target){
			return up == target.up && low == target.low;
		}
	}
}
