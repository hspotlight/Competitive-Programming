import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	//11059 - Maximum Product
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			int n = Integer.parseInt(bfr.readLine());
			BigInteger max = BigInteger.ZERO;
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int[] data = new int[st.countTokens()];
			for(int i=0;i<data.length; i++){
				data[i] = Integer.parseInt(st.nextToken());
			}
			max = findMax(data);
			System.out.println("Case #"+c+": The maximum product is "+max+".");
			System.out.println();
			bfr.readLine();
			c++;
		}
	}
	BigInteger findMax(int[] data){
		int n = data.length;
		BigInteger max = BigInteger.ZERO;
		for(int i=0;i<n;i++){//header
			for(int j=i;j<n;j++){//trailer
				BigInteger product = BigInteger.ONE;
				for(int k=i;k<=j;k++){//count each case
					if(data[k]==0) break;
					product = product.multiply(BigInteger.valueOf(data[k]));
					if(product.compareTo(max) == 1){
						max = product;
					}
				}
			}
		}
		return max;
	}
}
