import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//11942 - Lumberjack Sequencing
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		System.out.println("Lumberjacks:");
		int n = readInt();
		for(int i=0;i<n;i++){
			int arr[] = new int[10];
			for(int j=0;j<10;j++) arr[j] = readInt();
			System.out.println(increasing(arr) || decreasing(arr)?"Ordered":"Unordered");
		}
	}
	boolean increasing(int[] A){
		for(int i=0;i<A.length-1;i++){
			if(A[i]>A[i+1]) return false;
		}
		return true;
	}
	boolean decreasing(int[] A){
		for(int i=A.length-1;i>0;i--){
			if(A[i-1]<A[i]) return false;
		}
		return true;
	}
}