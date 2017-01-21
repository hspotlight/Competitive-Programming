import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	//10954 - Add All
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int N;
		while((N=readInt())!=0){
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for(int i=0;i<N;i++) pq.add(readInt());
			int cost = 0;
			while(pq.size() >= 2){
				int a = pq.poll(), b = pq.poll();
				int ans = a+b;
				cost += ans;
				pq.add(ans);
			}
			System.out.println(cost);
		}
	}
}