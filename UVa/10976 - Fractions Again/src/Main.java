import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10976 - Fractions Again
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			answer(Integer.parseInt(bfr.readLine()));
		}
	}
	void answer(int start){
		int count = 1;
		StringBuilder sb = new StringBuilder();
		for(int i=start+1;i< start*2;i++){
			int up = i - start;
			int down = i*start;
			if(up==1){//OK
				count ++;
				sb.append("1/"+start+" = 1/"+down+" + 1/"+i+"\n");
			}
			else{
				int rem = down%up;//can use another method (plus until)
				if(rem!=0); //cannot
				else{
					down /= up;
					count++;
					sb.append("1/"+start+" = 1/"+down+" + 1/"+i+"\n");
				}
			}
			
		}
		System.out.println(count);
		System.out.print(sb);
		System.out.println("1/"+start+" = 1/"+(start+start)+" + 1/"+(start+start));
	}
}
