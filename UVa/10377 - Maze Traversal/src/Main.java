import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//10377 - Maze Traversal
	int row,col;
	char map[][];
	char direction;
	int i;
	int j;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			map = new char[row][col];
			for(int i=0;i<row; map[i++] = bfr.readLine().toCharArray() );
			st = new StringTokenizer(bfr.readLine());
			i = Integer.parseInt(st.nextToken()) - 1;
			j = Integer.parseInt(st.nextToken()) - 1;
//			map[i][j] = 'x';
			String command = "";
			String s = bfr.readLine();
			while(!s.equals("")){//blankline
				command += s.replace(" ", "");
				if(!bfr.ready()) break;
				s = bfr.readLine();
			}
			traversal(command.toCharArray());
			System.out.println((i+1)+" "+(j+1)+" "+direction);
			if(tc>0)System.out.println();
		}
	}
	void traversal(char cmd[]){
		direction = 'N';
		for(int c=0;c<cmd.length;c++){
			if(cmd[c]=='Q') return;
			if(cmd[c]=='F')//move
				forward();
			else changeDir(cmd[c]);
//			for(int x=0;x<row;x++){
//				System.out.println(map[x]);
//			}
//			System.out.println("*************");
		}
	}
	void forward(){
		switch(direction){
		case 'N': if(0<= i-1 && map[i-1][j]!='*') {
//					 map[i][j] = ' ';
				 	 i--;
//				 	 map[i][j] = 'x';
				  }
				  break;
		case 'E': if(j+1<col && map[i][j+1]!='*') {
//					 map[i][j] = ' ';
					 j++;
//					 map[i][j] = 'x';
				  }
				  break;
		case 'S': if(i+1<row && map[i+1][j]!='*') {
//					 map[i][j] = ' ';
					 i++; 
//					 map[i][j] = 'x';
				  }
				  break;
		case 'W': if(0<= j-1 && map[i][j-1]!='*') {
//					 map[i][j] = ' ';
					 j--; 
//					 map[i][j] = 'x';
				  }
				  break;
		}
	}
	void changeDir(char c){
		String d = "NESW";
		if(c=='R'){
			direction = d.charAt( (d.indexOf(direction) +1 ) % 4);
		}
		else if (c=='L'){
			direction = d.charAt( (d.indexOf(direction) +3 ) % 4);
		}
	}
}
