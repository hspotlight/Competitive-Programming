import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 977A - Wrong Subtraction [Accepted]
	String number;
	int k;
	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		number = st.nextToken();
		k = Integer.parseInt(st.nextToken());
		while (k-- > 0) {
			number = tanyaSubtraction(number);
		}
		bfw.write(number + "\n");
		bfw.flush();
	}

	String tanyaSubtraction(String number) {
		char[] text = number.toCharArray();
		int length = text.length;
		if (text[length - 1] == '0') {
			return number.substring(0, length - 1);
		} else {
			text[length - 1] = (char) ((int) text[length - 1] - 1);
			return new String(text);
		}
	}
}