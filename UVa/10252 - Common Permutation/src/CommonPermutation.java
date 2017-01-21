import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CommonPermutation {
	//10252 - Common Permutation
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while(bf.ready()){
			char[] a1 = bf.readLine().toCharArray();
			char[] a2 = bf.readLine().toCharArray();
			Arrays.sort(a1);
			Arrays.sort(a2);
			System.out.println(String.valueOf(a1));
			System.out.println(String.valueOf(a2));
			String answer = "";
			int j = 0;
			for(int i=0;i<a1.length;i++){
				while(j<a2.length && a1[i] > a2[j])	j++;
				if(j<a2.length && a1[i] == a2[j]){
					answer += a1[i];
					j++;
				}
			}
			if(answer.isEmpty())	System.out.println();
			else{
				char[] order = answer.toCharArray();
				Arrays.sort(order);
				System.out.println(String.valueOf(order));
			}
		}
	}
}
