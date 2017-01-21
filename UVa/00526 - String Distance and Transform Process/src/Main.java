import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//00526 - String Distance and Transform Process
	char op[][];
	int  ed[][];
	char[] s, t;
	int pos, step;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			s = bfr.readLine().toCharArray();
			t = bfr.readLine().toCharArray();
			letGo(s, t);
			if(bfr.ready()) System.out.println();
		}
	}
	void letGo(char[] s, char[] t){
		//s->t
		op = new char[s.length+1][t.length+1];
		ed = new int[s.length+1][t.length+1];
		for(int i=1;i<s.length+1;i++) { ed[i][0] = i; op[i][0] = 'D'; }
		for(int i=1;i<t.length+1;i++) { ed[0][i] = i; op[0][i] = 'I'; }
		op[0][0] = 'M';
		
		for(int i=1;i<s.length+1;i++){
			for(int j=1;j<t.length+1;j++){
				if(s[i-1] == t[j-1]){//match
					ed[i][j] = ed[i-1][j-1];
					op[i][j] = 'M';
				}
				else{
					int del = ed[i-1][j] + 1;
					int ist = ed[i][j-1] + 1;
					int rep = ed[i-1][j-1] + 1;
					if(del<=ist && del<=rep) {
						ed[i][j] = del;
						op[i][j] = 'D';
					}
					else if (ist<=del && ist<=rep) {
						ed[i][j] = ist;
						op[i][j] = 'I';
					}
					else {
						ed[i][j] = rep;
						op[i][j] = 'R';
					}
				}
			}
		}
		System.out.println(ed[s.length][t.length]);
		pos = 1; step = 1;
		print(s.length,t.length);
	}
	void print(int i,int j){
		if(i==-1 || j==-1) return;
		if(i==0 && j==0) return;
		switch(op[i][j]){
			case 'M': 
				print(i-1,j-1);
				break;
			case 'D': //up
				print(i-1,j);
				System.out.println((step++)+" Delete "+(j+1));	
				break;
			case 'I': //left
				print(i,j-1);
		  	    System.out.println((step++)+" Insert "+(j)+","+t[j-1]);
	  		  	break;
			case 'R': //up left
				print(i-1,j-1);
				System.out.println((step++)+" Replace "+(j)+","+t[j-1]);
	  	  	  	break;
		}
	}
}
