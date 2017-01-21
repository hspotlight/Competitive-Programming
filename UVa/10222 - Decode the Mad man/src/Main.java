import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10222 - Decode the Mad man
	char in [] = "ertyuiop[]\\dfghjkl;'cvbnm,./".toCharArray();
	char out[] = "qwertyuiop[asdfghjklzxcvbnm,".toCharArray();
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			char[] s = bfr.readLine().toLowerCase().toCharArray();
			String ans = "";
			for(int i=0;i<s.length;i++){
				if(s[i]==' ') ans+= " ";
				else{
					for(int j=0;j<in.length;j++){
						if(s[i]==in[j]){
							ans+= out[j];
							break;
						}
					}
				}
			}
			System.out.println(ans);
		}
	}
}
