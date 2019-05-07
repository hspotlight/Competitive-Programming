import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 1006A - Adjacent Replacements [Accepted]
	int[] array;

	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bfr.readLine());
		array = new int[n];
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		for (int i = 0; i < n; i++) {
			int a_i = Integer.parseInt(st.nextToken());
			array[i] = ((a_i & 1) == 0) ? a_i - 1 : a_i;
		}
		String answer = Arrays.toString(array).replaceAll("(\\[|\\]|,)", "");
		bfw.write(answer + "\n");
		bfw.flush();
		bfw.close();
	}
}