import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//00108 - Maximum Sum
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	String getString() throws Exception{ stk.nextToken(); return stk.sval; }
	int data[][];
	int sum[][];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		while(bfr.ready()){
			int n = readInt();
			data = new int[n+1][n+1];
			sum = new int[n+1][n+1];
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					data[i][j] = readInt();
				}
			}
			//calculate
			for(int i=1;i<=n;i++){
				int sumLine = 0;
				for(int j=1;j<=n;j++){
					sumLine += data[i][j];
					sum[i][j] = sumLine + sum[i-1][j];
				}
			}
			int max = Integer.MIN_VALUE;
			for(int startI = 1; startI<=n; startI++){
				for(int startJ = 1; startJ <= n ; startJ++){
					for(int endI = startI; endI <= n ; endI++){
						for(int endJ = startJ; endJ <= n; endJ++){
							int sum2D = sum[endI][endJ] - sum[endI][startJ-1] - sum[startI-1][endJ] + sum[startI-1][startJ-1];//
							if(sum2D>max) max = sum2D;
						}
					}
				}
			}
			System.out.println(max);
		}
	}
}
