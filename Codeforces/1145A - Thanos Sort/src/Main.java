import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 1145A - Thanos Sort [Accepted]
	int n;
	int count = 1;
	public static void main(String[] args) throws Exception {
		new Main().run();
	}

	void run() throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(bfr.readLine());
		count = 1;
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		thanosSort(array);
		bfw.write(count + "\n");
		bfw.flush();
	}

	void thanosSort(int[] arr) {
		if (isSorted(arr)) {
			if (arr.length > count) {
				count = arr.length;
			}
		} else {
			int length = arr.length / 2;
			int[] left = new int[length];
			int[] right = new int[length];
			for (int i = 0; i < arr.length; i++) {
				if (i < length) {
					left[i] = arr[i];
				} else {
					right[i - length] = arr[i];
				}
			}
			thanosSort(left);
			thanosSort(right);
		}

	}

	boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}
		return true;
	}

}
