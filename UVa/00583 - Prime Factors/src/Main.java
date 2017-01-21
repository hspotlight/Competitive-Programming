import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	//00583 - Prime Factors
	int N;
	boolean isComposite[] = new boolean[100000];
	ArrayList<Integer> prime = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i=2;i<100000;i+=(i==2?1:2)){
			if(!isComposite[i]){
				prime.add(i);
				for(int j=i+i;j<100000;j+=i) isComposite[j] = true;
			}
		}
		while(true){
			N = Integer.parseInt(bfr.readLine());
			if(N==0) break;
			String out = ""+N+" = ";
			boolean first = true;
			if(N<0){
				out += "-1";
				N = -N;
				first = false;
			}
			int index = 0;
			while(N>1 && index<prime.size()){
				int p = prime.get(index);
				while(N%p==0){
					if(!first) out += " x ";
					else first = false;
					out += p;
					N /= p;
				}
				index++;
			}
			if(N>1){
				out += ( first ? "" : " x ") + N;
			}
			sb.append(out+"\n");
		}
		System.out.print(sb);
	}
}
