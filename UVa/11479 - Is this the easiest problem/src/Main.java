import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11479 - Is this the easiest problem
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int i=1;i<=tc;i++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			long side[] = new long[3];
			for(int j=0;j<3;j++) {
				side[j] = Integer.parseInt(st.nextToken());
			}
			System.out.print("Case "+i+": ");
			System.out.println(determine(side[0],side[1],side[2]));
		}
	}
	/*
	 * 	if(a+b>c && a+c>b && b+c>a) System.out.println("OK");//triangle
		else System.out.println("Wrong!!");
	 */
	public String determine(long a,long b,long c){
		if(!( (a>0 && b>0 && c>0) && a+b>c && a+c>b && b+c>a) ) {return "Invalid"; }
		else {//triangle
			if(a==b && b==c) return "Equilateral";
			if(a==b && b!=c) return "Isosceles";
			if(b==c && c!=a) return "Isosceles";
			if(c==a && a!=b) return "Isosceles";
			else return "Scalene";
		}
	}
}
