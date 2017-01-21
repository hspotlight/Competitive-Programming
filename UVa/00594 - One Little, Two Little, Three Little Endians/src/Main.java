import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	//00594 - One Little, Two Little, Three Little Endians
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			int in = Integer.parseInt(bfr.readLine());
			System.out.println(in+" converts to "+Integer.reverseBytes(in));
		}
	}
	public String rev(String s){//useless
		String out = "";
		for(int i=s.length()-1;i>=0;i--)
			out += s.charAt(i);
		return out;
	}
	public int convert(String s){//useless
		String bytes[] = {s.substring(0,8),
						  s.substring(8,16),
						  s.substring(16,24),
						  s.substring(24,32)};
		for(int i=0;i<4;i++)
			System.out.println(bytes[i]);
		int out = 0;
		return out;
	}
}
