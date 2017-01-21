import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Main {
	//00147 - Dollars
	int centVal[] = {5,10,20,50,100,200,500,1000,2000,5000,10000};
	long dollar[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		dollar = new long[30001];
		dollar[0] = 1;
		for(int i=0;i<centVal.length;i++){
			for(int j=0;j<30001;j++){
				if(j >= centVal[i]) dollar[j] += dollar[j-centVal[i]];
			}
		}
		String s = "";
		while(!(s=bfr.readLine()).equals("0.00")){
			String y = ""+dollar[ double2Int( s )];
			int in = (int)(Double.parseDouble(s));
			int len = 0;
			if(in<10) len = 1;
			else if (in < 100) len = 2;
			else len = 3;
			for(int i=0;i<6-(len+3);i++) System.out.print(" ");
			System.out.printf("%.2f%17s\n",Double.parseDouble(s), y);
		}
	}
	int double2Int (String s){
		return Integer.parseInt(s.replace(".", ""));
	}
}
