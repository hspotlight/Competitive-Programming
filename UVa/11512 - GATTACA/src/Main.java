import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//11512 - GATTACA
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			System.out.println(ans(bfr.readLine()));
		}
	}
	String ans(String in){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<in.length();i++) list.add(in.substring(i));
		Collections.sort(list);
		String max = "";
		int count = 0;
		for(int i=0;i<list.size()-1;i++){
			int k = 0;
			while(k < list.get(i).length() && k < list.get(i+1).length()
					&&list.get(i).charAt(k)==list.get(i+1).charAt(k)) k++;
			String s = list.get(i).substring(0, k);
			if(k > max.length()) {
				max = s; count = 1;
			}
			if(!s.isEmpty() && k==max.length()){
				if(s.compareTo(max)<0) {
					max = s; count = 1;
				}
				else if (s.equals(max)) count++;
			}
		}
		if(count<2) return "No repetitions found!";
		return max+" "+count;
	}
}
