import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//10281 - Average Speed
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	int getTime(String s){
		int sum = 0;
		StringTokenizer st = new StringTokenizer(s,":");
		sum += 3600 * Integer.parseInt(st.nextToken());
		sum += 60 * Integer.parseInt(st.nextToken());
		sum += Integer.parseInt(st.nextToken());
		return sum;
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		double S,V,T;
		S = V = T = 0;
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			String strTime = st.nextToken();
			double time = getTime(strTime);
			//Calculation
			S += (time-T) * V /3600;
			if(st.hasMoreTokens()){//get V
				V = Integer.parseInt(st.nextToken());
			}
			else{//query
				System.out.println(String.format("%s %.2f km", strTime, S));
			}
			T = time;
		}
	}
}