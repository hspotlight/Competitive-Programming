import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//11494 - Queen
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int X1 = Integer.parseInt(st.nextToken());
			int Y1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			int Y2 = Integer.parseInt(st.nextToken());
			if(X1==0 && Y1==0 && X2==0 & Y2==0) break;
			//do_work
			int deltaX = X2-X1;
			int deltaY = Y2-Y1;
			if(deltaX<0) deltaX = -deltaX;
			if(deltaY<0) deltaY = -deltaY;
			//output
			if(deltaX==0 && deltaY==0) System.out.println(0);
			else{//move 1 or 2
				//move 1
				if(deltaX==0 || deltaY==0) System.out.println(1); //horizon and vertical
				else if(deltaX==deltaY) System.out.println(1);
				//move 2
				else System.out.println(2);
			}
		}
	}
}
