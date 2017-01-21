import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
	//10422 - Knights in FEN
	String start = "11111"+
			       "01111"+
			       "00 11"+
			       "00001"+
			       "00000";
	Map<String, Integer> map;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<String, Integer>();
		//Pre-process
		initialize();
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			String target = "";
			for(int i=0;i<5;i++) target += bfr.readLine().substring(0,5);
			/*************************************************************/
			if(map.get(target)==null){
				System.out.println("Unsolvable in less than 11 move(s).");
			}
			else{
				int move = map.get(target);
				System.out.println("Solvable in "+move+" move(s).");
			}
		}
	}
	void initialize(){
		Queue<Node> qe = new LinkedList<Node>();
		qe.add(new Node(start,0));
		map.put(start, 0);
		while(!qe.isEmpty()){
			Node n = qe.poll();
			if(n.move == 10) return; //terminate case
			int[]I = {-1,-2,-2,-1,1,2,2,1};
			int[]J = {-2,-1,1,2,2,1,-1,-2};
			for(int k=0;k<8;k++){//direction;
				int index = n.s.indexOf(" ");
				int i  = index/5;
				int j  = index%5;
				int newI = i + I[k];
				int newJ = j + J[k];
				if(0<= newI && newI < 5 && 0<=newJ && newJ < 5){
					//swap [i,j] and [newI,newJ]
					char[] c = n.s.toCharArray();
					char temp = c[i*5 + j];
					c[i*5 + j] = c[newI*5 + newJ];
					c[newI*5 + newJ] = temp;
					String newState = new String(c);
					
					if(!map.containsKey(newState)){
						//add to queue, new Node(new,n.move+1)
						qe.add(new Node(newState, n.move + 1));
						//add to map
						map.put(newState, n.move+1);
					}
					
				}
			}
		}
	}
	public class Node{
		String s;
		int move;
		Node(String s, int move){
			this.s = s;
			this.move = move;
		}
	}
}
