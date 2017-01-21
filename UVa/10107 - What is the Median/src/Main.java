import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		//10107 - What is the Median
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(bfr.ready()){
			list.add(Integer.parseInt(bfr.readLine().trim()));
			Collections.sort(list);
			int size = list.size();
			if((size&1)==1){//5 [0 1 2 3 4] 5/2 = 2
				System.out.println(list.get(size/2));
			}
			else{// 4 [0 1 2 3] 4/2 = 2
				int a = list.get(size/2);
				int b = list.get(size/2 - 1);
				System.out.println((a+b)/2);
			}
		}
	}
}
