import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	// 1166A - Translation [Accepted]

	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = bfr.readLine();
		String t = bfr.readLine();
		bfw.write(validateTranslation(s, t) ? "YES" : "NO");
		bfw.write("\n");
		bfw.flush();
	}
	
	boolean validateTranslation(String s, String t) {
		StringBuilder sb = new StringBuilder(t);
		String reverseT = sb.reverse().toString();
		return s.equals(reverseT);
	}
}