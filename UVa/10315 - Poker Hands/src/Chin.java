import java.io.*;
import java.util.*;

public class Chin {
	//10315 - Poker Hands
	static String cardN = "23456789TJQKA";
	static class MyCmp implements Comparator<String> {
		public int compare(String a,String b) {
			return cardN.indexOf(a.charAt(0)) - cardN.indexOf(b.charAt(0));
		}
	}
	static char findHighestPair(String[] cards,int n) {
		char c = 'A';
		for(int i = 4,j = 0;i > 0 && j <= n;i--) {
			if(cards[i].charAt(0) == cards[i-1].charAt(0)) {
				c = cards[i].charAt(0);
				j++;
			}
		}
		return c;
	}
	static int numOfPairs(String[] cards) {
		int n = 0;
		for(int i = 0;i < 4;i++)
			if(cards[i].charAt(0) == cards[i+1].charAt(0))
				n++;
		return n;
	}
	static char typeMaxNum(String[] cards) {
		char c = 'A';
		for(int i = 1;i < 4;i++)
			if(cards[i].charAt(0) == cards[i+1].charAt(0) && cards[i].charAt(0) == cards[i-1].charAt(0))
				c = cards[i].charAt(0);
		return c;
	}
	static int maxNumOfAKind(String[] cards) {
		char n;
		int num;
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < 5;i++) {
			n = cards[i].charAt(0);
			num = 0;
			for(int j = 0;j < 5;j++)
				if(cards[j].charAt(0) == n)
					num++;
			max = Math.max(num,max);
		}
		return max;
	}
	static boolean isFlush(String[] cards) {
		char t = cards[0].charAt(1);
		for(int i = 1;i < 5;i++)
			if(cards[i].charAt(1) != t)
				return false;
		return true;
	}
	static boolean isStraight(String[] cards) {
		for(int i = 0;i < 4;i++)
			if(cardN.indexOf(cards[i+1].charAt(0)) - cardN.indexOf(cards[i].charAt(0)) != 1)	return false;
		return true;
	}
	static String cardsWithoutPair(String[] cards) {
		String ans = "";
		int n;
		for(int i = 0;i < 5;i++) {
			n = 0;
			for(int j = 0;j < 5;j++)	if(cards[i].charAt(0) == cards[j].charAt(0))	n++;
			if(n == 1)	ans += cards[i].charAt(0);
		}
		return ans;
	}
	public static void main(String args[]) throws IOException {
		try {
			System.setIn(new FileInputStream(new File("input.txt")));
			System.setOut(new PrintStream(new FileOutputStream(new File("out.txt"))));
		} catch(FileNotFoundException e) {}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		String cards[][] = new String[2][5],inS,ansS = "";
		int status[] = new int[2],ans = 0;
		while((inS = in.readLine()) != null && inS.length() != 0) {
			st = new StringTokenizer(inS.toUpperCase());
			for(int i = 0;i < 2;i++)	for(int j = 0;j < 5;j++)	cards[i][j] = st.nextToken();
			Arrays.sort(cards[0],new MyCmp());
			Arrays.sort(cards[1],new MyCmp());
			for(int i = 0;i < 2;i++) {
				if(isStraight(cards[i]) && isFlush(cards[i]))						status[i] = 8;
				else if(maxNumOfAKind(cards[i]) == 4)								status[i] = 7;
				else if(maxNumOfAKind(cards[i]) == 3 && numOfPairs(cards[i]) == 3)	status[i] = 6;
				else if(isFlush(cards[i]))											status[i] = 5;
				else if(isStraight(cards[i]))										status[i] = 4;
				else if(maxNumOfAKind(cards[i]) == 3)								status[i] = 3;
				else if(numOfPairs(cards[i]) == 2)									status[i] = 2;
				else if(numOfPairs(cards[i]) == 1)									status[i] = 1;
				else status[i] = 0;
			}
			ans = status[0] - status[1];
			if(ans == 0) {
				if(status[0] == 1) {
					char c0 = findHighestPair(cards[0],0),c1 = findHighestPair(cards[1],0);
					if(c0 == c1) {
						String s0 = cardsWithoutPair(cards[0]),s1 = cardsWithoutPair(cards[1]);
						for(int i = s0.length()-1;i >= 0 && c0 == c1;c0 = s0.charAt(i),c1 = s1.charAt(i),i--);
						ans = cardN.indexOf(c0) - cardN.indexOf(c1);
					}
					else			ans = cardN.indexOf(c0) - cardN.indexOf(c1);
				}
				else if(status[0] == 2) {
					char c0 = findHighestPair(cards[0],0),c1 = findHighestPair(cards[1],0);
					if(c0 == c1) {
						c0 = findHighestPair(cards[0],1);
						c1 = findHighestPair(cards[1],1);
					}
					if(c0 == c1) {
						c0 = cardsWithoutPair(cards[0]).charAt(0);
						c1 = cardsWithoutPair(cards[1]).charAt(0);
					}
					ans = cardN.indexOf(c0) - cardN.indexOf(c1);
				}
				else if(status[0] == 3 || status[0] == 6 || status[0] == 7)	ans = cardN.indexOf(typeMaxNum(cards[0])) - cardN.indexOf(typeMaxNum(cards[1]));
				else if(status[0] == 4 || status[0] == 8)					ans = cardN.indexOf(cards[0][4].charAt(0)) - cardN.indexOf(cards[1][4].charAt(0));
				else if(status[0] == 5 || status[0] == 0) {
					for(int i = 4;i >= 0;i--) {
						if(cards[0][i].charAt(0) != cards[1][i].charAt(0)) {
							ans = cardN.indexOf(cards[0][i].charAt(0)) - cardN.indexOf(cards[1][i].charAt(0));
							break;
						}
					}
				}
			}
			if(ans < 0)			ansS = "White wins.\n";
			else if(ans > 0)	ansS = "Black wins.\n";
			else				ansS = "Tie.\n";
			out.write(ansS);
		}
		in.close();
		out.flush();
		out.close();
	}
}
