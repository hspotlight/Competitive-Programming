import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	// 1077A - Frog Jumping [Accepted]
	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(bfr.readLine());
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			String answer = "";
			int aMinusB = a - b;
			int kHalf = k / 2;
			if ((k & 1) == 0) { // (a-b) * (k/2)
				answer = BigInteger.valueOf(aMinusB).multiply(BigInteger.valueOf(kHalf)).toString();
			} else {// ((a-b) * (k/2)) + a
				answer = BigInteger.valueOf(aMinusB).multiply(BigInteger.valueOf(kHalf)).add(BigInteger.valueOf(a))
						.toString();
			}
			bfw.write(answer + "\n");
		}
		bfw.flush();
		bfw.close();
	}
}
