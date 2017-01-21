import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//00488 - Triangle Wave
	String tri[];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		tri = new String[10];
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			bfr.readLine();
			int A = Integer.parseInt(bfr.readLine());
			int F = Integer.parseInt(bfr.readLine());
			if(F==0) continue;
			for(int i=0;i<F;i++){
				System.out.print( print(A) );
				if(i!=F-1) System.out.println();
			}
			if(tc!=0) System.out.println();
		}
	}
	String print(int A){
		if(tri[A]!=null) return tri[A];
		String s = "";
		for(int i=1;i<=A;i++){
			for(int j=1;j<=i;j++){ //number of i
				s += i;
			}
			s += "\n";
		}
		for(int i=A-1;i>=1;i--){
			for(int j=1;j<=i;j++){
				s += i;
			}
			s += "\n";
		}
		return tri[A] = s;
	}
}
