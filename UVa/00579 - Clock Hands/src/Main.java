import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Main {
	//00579 - Clock Hands
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine(),":");
			int H = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(H==0 && M==0) break;
			/*******************************************************/
			if(H==12) H = 0; //H[0-11], M[00-59]
			//60 mins -> 30 degree, 2 mins -> 1 degree
			double hour = H * 30 + (M/2.0);//[0-11] * 30 => [0-330]
			double minute = M * 6;//[0-59] * 6 -> [0-354] compare with 00.00 clockwise
			double degree = Math.abs(hour - minute);
			if(degree > 180) degree = 360 - degree;
			System.out.println(new DecimalFormat("0.000").format(degree));
		}
	}
}
