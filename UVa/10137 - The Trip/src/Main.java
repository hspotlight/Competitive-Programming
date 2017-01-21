import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;

public class Main {
	//10137 - The Trip
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	double readDouble() throws Exception{ stk.nextToken(); return stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int n;
		while((n = readInt())!=0){
			double[] A = new double[n];
			double total = 0;//total expense
			for(int i=0;i<n;i++){
				total += (A[i] = readDouble()*100);
			}
			double avg = Math.round(total/n); //average expense -> amount of money that choose have
			//what about within 1 cent
			//assume average in correct -> can be written in cent
//			System.out.println("average = "+(avg));
			double sumA = 0, sumB = 0;
			for(int i=0;i<n;i++){
				double diff = Math.abs(A[i] - avg); //find the number of exchange
				if(A[i] > avg) sumA += diff; //above average
				else sumB += diff;           //below average
			}
			//find the minimum of the exchange expense
//			System.out.println("Above = "+sumA/100);
//			System.out.println("Below = "+sumB/100);
			if(sumA<sumB) System.out.printf("$%.2f\n",sumA/100);
			else System.out.printf("$%.2f\n",sumB/100);
		}
	}
}
