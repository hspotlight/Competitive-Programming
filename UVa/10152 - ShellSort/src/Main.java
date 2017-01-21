import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//10152 - ShellSort
	StreamTokenizer stk;
	ArrayList<String> list = new ArrayList<String>();
	StringBuilder sb = new StringBuilder();
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		stk = new StreamTokenizer(bfr);
		int tc = Integer.parseInt(bfr.readLine());
		while(tc-->0){
			int n = Integer.parseInt(bfr.readLine());
			list.clear();
			for(int i=0;i<n;i++) list.add(bfr.readLine());
			int[] start = new int[n];
			int[] end = new int[n];
			for(int i=0;i<n;i++) start[i] = i;
			for(int i=0;i<n;i++) end[i] = list.indexOf(bfr.readLine());
			step(start,end);
			sb.append("\n");
		}
		bfw.write(""+sb);
		bfw.flush();
	}
	void step(int[] left, int[] right){
		int l = left.length-1, r = right.length-1;
		while(l>= 0 && r >=0){
			if(left[l] == right[r]) r--;
			l--;
		}
		while(r>=0){
			sb.append(list.get(right[r--])+"\n");
		}
	}
}
