import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	//10006 - Carmichael Numbers
	int n = 65000;
	boolean isComposite[] = new boolean[n];
	Boolean[] carmichael = new Boolean[n];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=2;i<n;i += (i==2?1:2)){
			if(!isComposite[i]){
				for(int j=i+i;j<n;j+=i)
					isComposite[j]=true;
			}
		}
		while(bfr.ready()){
			BigInteger n = new BigInteger(bfr.readLine());
			if(n.intValue() == 0) break;
			
			boolean carmichael = isCarmichael(n);
			
			if(carmichael) bfw.write("The number "+n+" is a Carmichael number.\n");
			else bfw.write(n+" is normal.\n");
		}
		bfw.flush();
	}
	boolean isCarmichael(BigInteger n){
		int index = n.intValue();
		if(carmichael[index] != null) return carmichael[index];
		
		if(!isComposite[n.intValue()]) return carmichael[index] = false;
		else{
			BigInteger a = BigInteger.valueOf(2);
			while(a.compareTo(n)<0){
				if(!a.modPow(n, n).equals(a)){
					return carmichael[index] = false;
				}
				a = a.add(BigInteger.ONE);
			}
			return carmichael[index] = true;
		}
	}
}
