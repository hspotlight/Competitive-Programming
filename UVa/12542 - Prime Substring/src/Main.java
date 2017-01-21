import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//12542 - Prime Substring
	int N = 100000;
	boolean[] isComposite = new boolean[N];
	ArrayList<String> prime = new ArrayList<String>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		for(int i=2;i<N;i+=(i==2?1:2)){
			if(!isComposite[i]){
				prime.add(""+i);
				for(int j=i+i;j<N;j+=i){
					isComposite[j] = true;
				}
			}
		}
		
		Collections.reverse(prime);
		
		while(bfr.ready()){
			String line = bfr.readLine();
			if(line.equals("0")) break;
			
			System.out.println(find(line));
		}
	}
	String find(String line){
		for(String p : prime){
			if(line.indexOf(p)!=-1) return p;
		}
		return "Hello World!";
	}
}
