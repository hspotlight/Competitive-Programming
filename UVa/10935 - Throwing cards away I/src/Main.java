import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//10935 - Throwing cards away I
	//read problem statement carefully
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	public void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			if(n==0) break;
			
			Queue<Integer> qe = new LinkedList<Integer>();
			for(int i=1;i<=n;i++) qe.add(i);
			
			System.out.print("Discarded cards:");
			String out = "";
			while(qe.size()>1){
				int discard = qe.poll();
				if(out.isEmpty()){
					out += " "+discard;
				}
				else{
					out += ", "+discard;
				}
				qe.add(qe.poll());
			}
			System.out.println(out);
			System.out.println("Remaining card: "+qe.poll());
		}
	}
}
