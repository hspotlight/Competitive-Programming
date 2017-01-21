import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	//11650 - Mirror Clock
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine(),":");
			int HH = Integer.parseInt(st.nextToken());
			int MM = Integer.parseInt(st.nextToken());
			int H = getH(HH,MM);
			int M = getM(HH,MM);
			System.out.println(String.format("%02d:%02d", H,M));
		}
	}
	int getH(int H, int M){
		if(M==0)return H==12 ? 12 : 12-H;
		else{//M = [1,59]
			switch(H){
				case 12: return 11;
				case 11: return 12;
				case 10: return 1;
				case 9: return 2;
				case 8: return 3;
				case 7: return 4;
				case 6: return 5;
				case 5: return 6;
				case 4: return 7;
				case 3: return 8;
				case 2: return 9;
				case 1: return 10;
			}
			return -1;
		}
	}
	int getM(int H, int M){
		return M == 0  ?  0 : 60 - M; //[0,59]
	}
}