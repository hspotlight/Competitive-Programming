import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {
	//10319 - Manhattan
	int S, A, M;
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int) stk.nval; }
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		new Main().run();
	}
	void run() throws Exception{
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		int tc = readInt();
		while(tc-->0){
			S = readInt(); A = readInt(); M = readInt();
			Sat sat = new Sat(S+A);
			for(int i=0;i<M;i++){
				int s1 = readInt() - 1, a1 = readInt() - 1 + S, s2 = readInt() - 1, a2 = readInt() - 1 + S;
				int deltaS = s2 - s1, deltaA = a2 - a1;
				
				if(deltaS == 0 && deltaA == 0) continue;
				
				int directionS = deltaA > 0? 1 : 0;
				int directionA = deltaS > 0? 1 : 0;
				
				if (deltaS==0){//move horizontal  
					sat.addConstraint(s1, directionS^1, s1, directionS);
				}
				else if (deltaA==0){//move vertical
					sat.addConstraint(a1, directionA^1, a1, directionA);
				}
				else{
					sat.addConstraint(s1, directionS^1, s2, directionS);
					sat.addConstraint(s1, directionS^1, a1, directionA);
					
					sat.addConstraint(s2, directionS^1, s1, directionS);
					sat.addConstraint(s2, directionS^1, a2, directionA);
					
					sat.addConstraint(a1, directionA^1, s1, directionS);
					sat.addConstraint(a1, directionA^1, a2, directionA);
					
					sat.addConstraint(a2, directionA^1, s2, directionS);
					sat.addConstraint(a2, directionA^1, a1, directionA);
				}
			}
			
			bfw.write( (sat.check()? "Yes" : "No") + "\n");
		}
		bfw.flush();
	}
	public class Sat{
		boolean[][] city;
		public Sat(int nNodes){
			city = new boolean[nNodes*2][nNodes*2];
		}
		
		public void addConstraint(int from, int flagFr, int to, int flagTo){
			city[from*2+flagFr][to*2+flagTo] = true;
		}
		
		public boolean check(){
			for(int k=0;k<city.length;k++){
				for(int i=0;i<city.length;i++){
					for(int j=0;j<city.length;j++){
						city[i][j] |= (city[i][k] && city[k][j]);
					}
				}
			}
			
			//the same street is used for bi-direction
			for(int i=0;i<city.length;i+=2){
				if(city[i][i^1] && city[i^1][i]) return false;
			}
			
			return true;
		}
	}
}
