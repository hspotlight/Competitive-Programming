import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//00357 - Let Me Count The Ways
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int coins[] = {1,5,10,25,50};
		long way[] = new long[30001];
		way[0] = 1;
		for(int i=0;i<coins.length;i++){
			for(int j=0;j<way.length;j++){
				if(j-coins[i]>=0) way[j] += way[j-coins[i]];
			}
		}
		while(bfr.ready()){
			int change = Integer.parseInt( bfr.readLine().trim() );
			long ans = way[change];
			if(ans>1)System.out.print("There are "+ans+" ways ");
			else System.out.print("There is only "+ans+" way ");
			System.out.println("to produce "+change+" cents change.");
			
		}
	}
}
