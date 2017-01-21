import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//BigInteger - 
	int n = 1001;
	int nPrime = 168;
	int factor[][] = new int[n][nPrime];
	int sum[] = new int[n];
	int prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		//pre-process
		//create table
		for(int i=2;i<n;i++){ //1 to n - 1
			int temp = i;
			int j = 0;
			while(temp>1 || j< nPrime){
				int r = temp% prime[j];
				while(r==0){
					temp /= prime[j];
					factor[i][j]++;
					r = temp%prime[j];
				}
				j++;
			}
		}
		int total[] = new int[nPrime]; //total factor of each prime
		for(int i = 1;i<n;i++){
			for(int j=0;j<nPrime;j++){
				total[j] += factor[i][j];
			}
			if(total[0]<total[2]){ total[0] = 0; total[2] -= total[0]; }
			else{ total[0] -= total[2]; total[2] = 0; }
			BigInteger p = BigInteger.ONE;
			for(int j=0;j<nPrime;j++){
				if(total[j]>0) p = p.multiply(BigInteger.valueOf(prime[j]).pow(total[j]));
			}
			String s = "" + p;
			for(int j=0;j<s.length();j++){
				sum[i] += (s.charAt(j)- '0');
			}
		}
		//query
		while(bfr.ready()){
			int x = Integer.parseInt(bfr.readLine());
			System.out.print(sum[x]+", ");
		}
	}
	public void print(int total[]){
		System.out.print("total: ");
		for(int j=0;j<nPrime;j++){
			System.out.print(total[j]+" ");
		}
		System.out.println();
	}
}
