import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class test {
	//11137 - Ingenuous Cubrency
	int cube[] = new int[22];
	int answer[] = new int[10000];
	int count;
	public static void main(String [] args) throws Exception{
		new test().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1;i<22;i++) cube[i] = i*i*i;
		while(bfr.ready()){
			count = 0;
			int input = Integer.parseInt(bfr.readLine());
			int index = 0;
			for(int i=21;i>=1;i--)
				if(cube[i]<input){ index = i; break; }
			if(answer[input]==0) {
				findAnswer(input,index);
				answer[input] = count;
			}
//			System.out.println(answer[input]);
		}
	}
	void findAnswer(int total,int index){
		int coins[] = new int[index+1];
		update(total,index,coins);
		int pointer = index-1;
		while(pointer>=0 && coins[pointer]==0)pointer--;
		while(coins[index]!=0){
			count++;
			for(int i=index;i>=0;i--) System.out.print(coins[i]+" ");
			System.out.println();
			coins[pointer] -= 1;
			update(cube[pointer],pointer-1,coins);
			if(coins[pointer]==0) while(pointer>=0 && coins[pointer]==0)pointer--; 
			if(pointer==0){
				coins[index]--;
				update(total-cube[index],index-1,coins);
				pointer = index-1;
				while(pointer>=0 && coins[pointer]==0)pointer--;
			}
		}
//		findAnswer(total,index-1);
	}
	void update(int total, int index, int coins[]){
		
		while(total>0){
			int x = total/cube[index];
			if(x>0) coins[index] += x;
			total = total%cube[index];
//			System.out.print(cube[index]+": ");
//			System.out.println(coins[index]+" ");
			index--;
		}
	}
}
