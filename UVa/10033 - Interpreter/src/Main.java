import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10033 - Interpreter
	int[] REG, RAM;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		bfr.readLine();
		while(tc-->0){
			REG = new int[10]; RAM = new int[1000];
			String s;
			int n = 0;
			while((s=bfr.readLine())!=null){
				if(s.isEmpty()) break;
				RAM[n++] = Integer.parseInt(s);
			}
			int count = 0, counter = 0;
			while(true){
				//instruction
				System.out.println("position: "+counter+" inst: "+RAM[counter]);
				int[] code = ItoA( RAM[counter] );
				int next = operation(code);
				count++;
				for(int i=0;i<10;i++) System.out.print(REG[i]+" ");
				System.out.println();
				if(next==-9) break;
				if(next==-1) counter++;
				else counter = next;
			}
			System.out.println();
			System.out.println(count);
			if(s!=null) System.out.println();
		}
	}
	int[] ItoA(int x){//assume x inRange(0,999)
		int[] A = new int[3];
		int i = 2;
		while(x>0){
			A[i--] = x%10;
			x/=10;
		}
		return A;
	}
	int operation(int[] s){
		int op = s[0], b = s[1], c = s[2];
		switch(op){
			case 1: 					    		return -9;//halt
			case 2: REG[b]  = c%1000; 		    	return -1;
			case 3: REG[b]  = (REG[b]+c)%1000; 		return -1;
			case 4: REG[b]  = (REG[b]*c)%1000; 		return -1;
			case 5: REG[b]  = REG[c]%1000; 			return -1;
			case 6: REG[b]  = (REG[b]+REG[c])%1000; return -1;
			case 7: REG[b]  = (REG[b]*REG[c])%1000; return -1;
			case 8: REG[b]  = RAM[REG[c]]%1000;  	return -1;
			case 9: RAM[REG[c]] = REG[b]%1000;   	return -1;
			case 0: if(REG[c]!=0) 	        		return REG[b];
			default: return -1;
		}
	}
}
