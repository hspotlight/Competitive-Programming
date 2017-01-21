import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//00674 - Coin Change
	int coin[];
	int cent[] = {1,5,10,25,50};
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		coin = new int[7490];
		coin[0] = 1;
		for(int c = 0;c<cent.length;c++){
			for(int i=0;i<7490;i++){
				if(i>=cent[c]) coin[i] += coin[ i-cent[c]];
			}
		}
		while(bfr.ready()){
			System.out.println(coin[Integer.parseInt(bfr.readLine())]);
		}
	}
}
