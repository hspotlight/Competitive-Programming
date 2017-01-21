import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10188 - Automated Judge Script
	int n,m;
	String[] sol, ans;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int c = 1;
		while(bfr.ready()){
			//standard solution
			n = Integer.parseInt(bfr.readLine());
			if(n==0) break;
			sol = new String[n];
			for(int i=0;i<n;sol[i++] = bfr.readLine());
			
			//team solution
			m = Integer.parseInt(bfr.readLine());
			ans = new String[m];
			for(int i=0;i<m;ans[i++] = bfr.readLine());
			
			//input clear
			System.out.println("Run #"+c+": "+judge());
			c++;
		}
	}
	String judge(){
		boolean accepted = true;
		if(n!=m) accepted = false;//line is not equal
		else{//assume n == m
			for(int i=0;i<n;i++)
				if(!sol[i].equals(ans[i])) accepted = false;
		}
		if(accepted) return "Accepted"; //every line is similar
		
		//concatenate
		String s = "";
		for(int i=0;i<n;i++) s += sol[i];
		String a = "";
		for(int i=0;i<m;i++) a += ans[i];
		//keep only digit character
		s = s.replaceAll("\\D+","");
		a = a.replaceAll("\\D+","");
		if(s.equals(a)) return "Presentation Error";
		return "Wrong Answer";
	}
}
