import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	//10992 - The Ghost of Programmers
	String[] ghost = {
			"Ghost of Tanveer Ahsan!!!\n",
			"Ghost of Shahriar Manzoor!!!\n",
			"Ghost of Adrian Kugel!!!\n",
			"Ghost of Anton Maydell!!!\n",
			"Ghost of Derek Kisman!!!\n",
			"Ghost of Rezaul Alam Chowdhury!!!\n",
			"Ghost of Jimmy Mardell!!!\n",
			"Ghost of Monirul Hasan!!!\n",
			"Ghost of K. M. Iftekhar!!!\n"
	};
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	boolean isLeap(BigInteger in){
		if(in.remainder(BigInteger.valueOf(400)).intValue()==0) return true;
		if(in.remainder(BigInteger.valueOf(100)).intValue()==0) return false;
		return in.remainder(BigInteger.valueOf(4)).intValue()==0;
	}
	void run() throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		boolean first = true;
		while(bfr.ready()){
			boolean multiple[] = new boolean[12];
			String line = bfr.readLine();
			if(line.equals("0")) break;
			
			if(first) first = false;
			else System.out.println();
			
			System.out.println(line);
			BigInteger in = new BigInteger(line);
			boolean leapYear = isLeap(in);
			in = in.subtract(BigInteger.valueOf(2148));
			if(in.compareTo(BigInteger.ZERO)==1){
				BigInteger[] dr = in.divideAndRemainder(BigInteger.valueOf(2));
				//2 - 4
				if(dr[1].equals(BigInteger.ZERO)){
					multiple[2] = true;
					dr = dr[0].divideAndRemainder(BigInteger.valueOf(2));
					if(dr[1].equals(BigInteger.ZERO))
						multiple[4] = true;
				}
				//3 - 9
				dr = in.divideAndRemainder(BigInteger.valueOf(3));
				if(dr[1].equals(BigInteger.ZERO)){
					multiple[3] = true;
					dr = dr[0].divideAndRemainder(BigInteger.valueOf(3));
					if(dr[1].equals(BigInteger.ZERO))
						multiple[9] = true;
				}
				//5
				if(in.remainder(BigInteger.valueOf(5)).equals(BigInteger.ZERO))
					multiple[5] = true;
				//7
				if(in.remainder(BigInteger.valueOf(7)).equals(BigInteger.ZERO))
					multiple[7] = true;
				//11
				if(in.remainder(BigInteger.valueOf(11)).equals(BigInteger.ZERO))
					multiple[11] = true;
				
				String out = "";
				if(multiple[2]) out += ghost[0];
				if(multiple[5]) out += ghost[1];
				if(multiple[7]) out += ghost[2];
				if(multiple[11]) out += ghost[3];
				
				if(multiple[3] && multiple[5]) out += ghost[4];
				if(multiple[4] && multiple[5]) out += ghost[5];
				if(multiple[4] && multiple[7]) out += ghost[6];
				if(multiple[4] && multiple[9]) out += ghost[7];
				
				if(leapYear) out += ghost[8];
				if(out.isEmpty())  System.out.println("No ghost will come in this year");
				else System.out.print(out);
			}
			else if (in.equals(BigInteger.ZERO)){
				for(String g : ghost){
					System.out.print(g);
				}
			}
			else System.out.println("No ghost will come in this year");
		}
	}
}
