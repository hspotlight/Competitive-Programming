import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	//10013 - Super long sums
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			int nBits = Integer.parseInt(bfr.readLine());
			char[] A = new char[nBits];
			char[] B = new char[nBits];
			char[] C = new char[nBits];
			for(int i=0;i<nBits;i++){
				String[] num = bfr.readLine().split(" ");
				A[i] = (char)(num[0].charAt(0) - '0');
				B[i] = (char)(num[1].charAt(0) - '0');
			}
			int cin = 0;
			for(int i=nBits-1;i>=0;i--){
				int sum = A[i] + B[i] + cin;
				if(sum>=10) {
					cin = 1;
					C[i] = (char)(sum-10 + '0');
				}
				else {
					cin = 0;
					C[i] = (char)(sum + '0');
				}
			}
			bfw.write( (cin==1?"1":"") + new String(C) + "\n");
			if(tc>0) bfw.write("\n");
		}
		bfw.flush();
	}
}
