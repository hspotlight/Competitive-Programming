import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	// 1166A - Helpful Maths [Accepted]

	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		String in = bfr.readLine();
		bfw.write(rearrange(in) + "\n");
		bfw.flush();
	}
	
	String rearrange(String s) {
		String[] summans = s.split("\\+");
		Arrays.parallelSort(summans);
		String out = "";
		for(String sum: summans) {
			out += out.isEmpty() ? sum : '+' + sum;
		}
		return out;
	}
}