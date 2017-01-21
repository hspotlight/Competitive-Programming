public class Main {
	//00136 - Ugly Numbers
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.out.println("The 1500'th ugly number is "+sol2()+".");
	}
	int sol2(){
		int ugly[] = new int[1500];
		int last = ugly[0] = 1, prev2 = 0, prev3 = 0, prev5 = 0;
		for(int i=1;i<1500;i++){
			while(ugly[prev2]*2 <= last) prev2++;
			while(ugly[prev3]*3 <= last) prev3++;
			while(ugly[prev5]*5 <= last) prev5++;
			last = ugly[i] = Math.min(ugly[prev2]*2, Math.min(ugly[prev3]*3, ugly[prev5]*5));
		}
		return ugly[1499];
	}
	int findUgly(int pos){//hong's first solution
		int n = 900000000;
		boolean isUgly[] = new boolean[n];
		isUgly[1] = isUgly[2] = isUgly[3] = isUgly[5] = true;
		int count = 1;
		for(int i=2;i<n;i++){
			if(i%2==0 && isUgly[i/2]) isUgly[i] = true;
			if(i%3==0 && isUgly[i/3]) isUgly[i] = true;
			if(i%5==0 && isUgly[i/5]) isUgly[i] = true;
			if(isUgly[i]) {
				count++;
				if(count==pos) return i;
			}
		}
		return -1;//indexOutOfBound
	}
}