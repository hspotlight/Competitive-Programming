import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Counting {
	//10198 - Counting
	public static void main(String[] avgs)throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BigInteger[] series= new BigInteger[1001];
		series[0] = BigInteger.valueOf(1);
		series[1] = BigInteger.valueOf(2);
		series[2] = BigInteger.valueOf(5);
		series[3] = BigInteger.valueOf(13);
		for(int i=4;i<=1000;i++)	series[i] = series[i-1].add(series[i-2]).add(series[i-3].add(series[i-1]));
		String line;
		while((line = bf.readLine())!= null)	System.out.println(series[Integer.parseInt(line)]);
	}
}

