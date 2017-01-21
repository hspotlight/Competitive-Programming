import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11777 - Automate the Grades
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c = 1;c <= tc; c++){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			double total = 0;
			double sum = 0;
			int min = Integer.MAX_VALUE;
			for(int i=0;i<7;i++){
				if(i<4) total += Integer.parseInt(st.nextToken());
				else {
					int x = Integer.parseInt(st.nextToken());
					sum += x;
					if(x<min) min = x;
				}
			}
			total += ((sum-min)/2);
			char grade = ' ';
			if(total >= 90) grade = 'A';
			else if (total >= 80) grade = 'B';
			else if (total >= 70) grade = 'C';
			else if (total >= 60) grade = 'D';
			else grade = 'F';
			System.out.println("Case "+c+": "+grade);
		}
	
	}
}
