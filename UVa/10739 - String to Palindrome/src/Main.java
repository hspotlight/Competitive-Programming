import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//10739 - String to Palindrome
	int ans;
	char[] s, t;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		for(int c=1;c<=tc;c++){
			s = bfr.readLine().toCharArray();
			t = reverse(s);
			ans = editDistance(s, t);
			System.out.println("Case "+c+": "+ans);
		}
	}
	int editDistance(char[] s, char[] t){
		int ed[][] = new int[s.length+1][t.length+1];
		for(int i=1;i<=s.length;i++) ed[i][0] = i;
		for(int i=1;i<=t.length;i++) ed[0][i] = i;
		for(int i=1;i<=s.length;i++){
			for(int j=1;j<=t.length;j++){
				if(s[i-1]==t[j-1]) ed[i][j] = ed[i-1][j-1];
				else{
					int del = ed[i-1][j] + 1;
					int ist = ed[i][j-1] + 1;
					int rep = ed[i-1][j-1] + 1;
					if(del<ist && del<rep) ed[i][j] = del;
					else if (ist<del && ist<rep) ed[i][j] = ist;
					else ed[i][j] = rep;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int j = t.length;
		for(int i=0;i<=s.length;i++,j--){
			if(i==s.length){
				if(ed[i][j] < min) min = ed[i][j];
			}
			else{
				if(ed[i][j] < min) min = ed[i][j];
				if(ed[i][j-1] < min) min = ed[i][j-1];
			}
		}
		return min;
	}
	char[] reverse(char[] s){
		char[] t = new char[s.length];
		for(int i=s.length-1;i>=0;i--) 
			t[s.length - 1 - i] = s[i];
		return t;
	}
}
