import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // UVa 10911 - Forming Quiz Teams
    int N, fullMask;
    int[] xCoord, yCoord;
    double[][] distance;
    double[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        System.setOut(new PrintStream(new FileOutputStream("src/output.txt")));
        new Main().run();
    }

    public void run() throws Exception {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCounter = 1;
        while ((N = Integer.parseInt(bfr.readLine())) != 0) {
            N = N * 2;
            xCoord = new int[N + 1];
            yCoord = new int[N + 1];
            dp = new double[(2 << 17)];
            fullMask = (2 << N) - 2;
            distance = new double[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(bfr.readLine());
                st.nextToken();
                xCoord[i] = Integer.parseInt(st.nextToken());
                yCoord[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    distance[i][j] = distance[j][i] = calculateDistance(i, j);
                }
            }

            double answer = calculateAnswer(0);
            bfw.write("Case " + caseCounter + ": " + String.format("%.2f", answer) + "\n");
            caseCounter += 1;
        }
        bfw.flush();
        bfw.close();
        bfr.close();
    }

    private double calculateAnswer(int mask) {
        if (mask == fullMask) {
            return 0;
        }
        if (dp[mask] != 0) {
            return dp[mask];
        }
        double answer = 99999999;
        for (int i = 1; i <= N; i++) {
            if ((mask & (1 << i)) > 0) continue;
            for (int j = i + 1; j <= N; j++) {
                if ((mask & (1 << j)) > 0) continue;
                int newMask = mask | (1 << i) | (1 << j);
                answer = Math.min(answer, distance[i][j] + calculateAnswer(newMask));
            }
        }
        dp[mask] = answer;
        return answer;
    }

    public double calculateDistance(int posI, int posJ) {
        return Math.sqrt(Math.pow(xCoord[posI] - xCoord[posJ], 2) + Math.pow(yCoord[posI] - yCoord[posJ], 2));
    }
}
