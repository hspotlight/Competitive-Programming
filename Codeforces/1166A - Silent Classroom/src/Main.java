import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 1166A - Silent Classroom [Accepted]

	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(bfr.readLine());
		int[] freq = new int[26];
		for (int i = 0; i < n; i++) {
			String stdName = bfr.readLine();
			char firstC = stdName.charAt(0);
			freq[firstC - 'a']++;
		}
		int count = 0;
		for (int i = 0; i < 26; i++) {
			if (freq[i] > 2) {
				if ((freq[i] & 1) == 1) {
					count += countPairs(freq[i]/2) + countPairs((freq[i]+1)/2);
				} else {
					count += countPairs(freq[i]/2) * 2;
				}
			}
		}
		bfw.write(count + "\n");
		bfw.flush();
	}
	
	int countPairs(int n) {
		return n*(n-1)/2;
	}
}