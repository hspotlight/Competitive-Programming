import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	//00657 - The die is cast
	int W,H;
	char map[][];
	int diceVal;
	int I[] = {-1,1,0,0};
	int J[] = {0,0,1,-1};
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int tc = 1;
		while(true){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W==0 && H==0) break;
			map = new char[H][W];
			for(int i=0;i<H;i++){
				map[i] = bfr.readLine().toCharArray();
			}
			//
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<H;i++){
				for(int j=0;j<W;j++){
					if(map[i][j]=='*'){// found some part of dice
						diceVal = 0;
						dice(i,j);
						if(1<=diceVal && diceVal<=6)
							list.add(diceVal);
					}
				}
			}
			//
			System.out.println("Throw "+(tc++));
			Collections.sort(list);
			for(int i=0;i<list.size();i++){
				System.out.print((i==0?"":" ") + list.get(i));
			}
			System.out.println();
			System.out.println();
		}
	}
	void dice(int i, int j){//*
		if(!inRange(i,j) || map[i][j]=='.') return;
		if(map[i][j]=='X') { diceVal++; findVal(i,j); }
		//map[i][j] == '*';
		map[i][j] = '.';
		for(int k=0;k<4;k++){
			dice(i+I[k], j+J[k]);
		}
	}
	void findVal(int i, int j){//X
		if(!inRange(i,j) || map[i][j]!='X') return;
		//map[i][j] == 'X';
		map[i][j] = '.';
		for(int k=0;k<4;k++){
			findVal(i+I[k], j+J[k]);
		}
	}
	boolean inRange(int i, int j){
		return 0<=i && i<H && 0<=j && j<W;
	}
}