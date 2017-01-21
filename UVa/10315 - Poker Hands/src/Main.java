import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//10315 - Poker Hands
	String value = "  23456789TJQKA";
	String suit  = " CDHS";
	//Why RE, have no idea
	//Cube Diamond Heart Spades
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(bfr.ready()){
			StringTokenizer st = new StringTokenizer(bfr.readLine());
			String black = format(st);
			String white = format(st);
			System.out.println( play(black,white) );
		}
	}
	void score(String s){
		System.out.println("****************************************************");
		System.out.println("straightFlush: "+ (straight(s) && flush(s)));
		System.out.println("fourOfKind: "+fourOfKind(s));
		System.out.println("fullHouse: "+fullHouse(s));
		System.out.println("flush: "+flush(s));
		System.out.println("straight: "+straight(s));
		System.out.println("threeOfKind: "+threeOfKind(s));
		System.out.println("twoPair: "+twoPair(s));
		System.out.println("pair: "+pair(s));
	}
	int level(String s){
		if( straight(s) && flush(s) ) return 9;
		else if ( fourOfKind(s) )     return 8;
		else if ( fullHouse(s) )      return 7;
		else if ( flush(s) )          return 6;
		else if ( straight(s) )       return 5;
		else if ( threeOfKind(s) )    return 4;
		else if ( twoPair(s) )        return 3;
		else if ( pair(s) ) 		  return 2;
		return 1;//high card
	}
	String getWinner(int b, int w){
		return b > w ? "Black wins." : "White wins.";
	}
	String play(String black, String white){
		int scoreB, scoreW;
		scoreB = level(black);
		scoreW = level(white);
		if(scoreB != scoreW) return getWinner(scoreB, scoreW); 
		//else b == w; same level
		char[] B = black.toCharArray();
		char[] W = white.toCharArray();
		switch(scoreB){
		//straightFlush; high card is right card
		case 9: scoreB = value.indexOf(B[8]);
				scoreW = value.indexOf(W[8]);
//				System.out.println("9: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//fourOfKind; [0 2 4 6] 8, 0 [2 4 6 8]: 4 is common
		case 8: scoreB = value.indexOf(B[4]);
				scoreW = value.indexOf(W[4]);
//				System.out.println("8: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//fullHouse; [0 2 4] [6 8], [0 2] [4 6 8]: 4 is common
		case 7: scoreB = value.indexOf(B[4]);
				scoreW = value.indexOf(W[4]);
//				System.out.println("7: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//flush; high card is right card
		case 6: scoreB = value.indexOf(B[8]);
				scoreW = value.indexOf(W[8]);
//				System.out.println("6: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//straight; high card is right card
		case 5: scoreB = value.indexOf(B[8]);
				scoreW = value.indexOf(W[8]);
//				System.out.println("5: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//threeOfKind; [0 2 4] [6 8], 0 [2 4 6] 8, [0 2] [4 6 8]: 4 is common
		case 4: scoreB = value.indexOf(B[4]);
				scoreW = value.indexOf(W[4]);
//				System.out.println("4: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//twoPair
		case 3: //highest pair
				if(B[0]==B[2] && B[4]==B[6]){
					scoreB = value.indexOf(B[6]);
				}
				else if(B[0]==B[2] && B[6]==B[8]){
					scoreB = value.indexOf(B[8]);
				}
				else if(B[2]==B[4] && B[6]==B[8]){
					scoreB = value.indexOf(B[8]);
				}
				// White
				if(W[0]==W[2] && W[4]==W[6]){
					scoreW = value.indexOf(W[6]);
				}
				else if(W[0]==W[2] && W[6]==W[8]){
					scoreW = value.indexOf(W[8]);
				}
				else if(W[2]==W[4] && W[6]==W[8]){
					scoreW = value.indexOf(W[8]);
				}
//				System.out.println("3: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
				//other pair
				if(B[0]==B[2] && B[4]==B[6]){
					scoreB = value.indexOf(B[2]);
				}
				if(B[0]==B[2] && B[6]==B[8]){
					scoreB = value.indexOf(B[2]);
				}
				if(B[2]==B[4] && B[6]==B[8]){
					scoreB = value.indexOf(B[4]);
				}
				//
				if(W[0]==W[2] && W[4]==W[6]){
					scoreW = value.indexOf(W[2]);
				}
				if(W[0]==W[2] && W[6]==W[8]){
					scoreB = value.indexOf(W[2]);
				}
				if(W[2]==W[4] && W[6]==W[8]){
					scoreW = value.indexOf(W[4]);
				}
//				System.out.println("3: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//case pair
		case 2: if(B[0]==B[2]){
					scoreB = value.indexOf(B[2]);
				}
				if(B[2]==B[4]){
					scoreB = value.indexOf(B[4]);
				}
				if(B[4]==B[6]){
					scoreB = value.indexOf(B[6]);
				}
				if(B[6]==B[8]){
					scoreB = value.indexOf(B[8]);
				}
				//white
				if(W[0]==W[2]){
					scoreW = value.indexOf(W[2]);
				}
				if(W[2]==W[4]){
					scoreW = value.indexOf(W[4]);
				}
				if(W[4]==W[6]){
					scoreW = value.indexOf(W[6]);
				}
				if(W[6]==W[8]){
					scoreW = value.indexOf(W[8]);
				}
//				System.out.println("2: "+scoreB+" "+scoreW);
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		//highCard
		case 1: int i=8;
				while(i>=0 && scoreB==scoreW){
					scoreB = value.indexOf(B[i]);
					scoreW = value.indexOf(W[i]);
					i -= 2;
				}
				if(scoreB != scoreW) return getWinner(scoreB, scoreW);
		}
		return "Tie.";
	}
	boolean fourOfKind(String s){
		char[] c = s.toCharArray();
		// 0 2 4 6
		if(c[0]==c[2] && c[2]==c[4] && c[4]==c[6]) return true;
		// 2 4 6 8
		if(c[2]==c[4] && c[4]==c[6] && c[6]==c[8]) return true;
		return false;
	}
	boolean fullHouse(String s){
		char[] c = s.toCharArray();
		// [0 2 4] [6 8] 
		if(c[0]==c[2] && c[2]==c[4] && c[6]==c[8]) return true;
		// [0 2] [4 6 8]
		if(c[0]==c[2] && c[4]==c[6] && c[6]==c[8]) return true;
		return false;
	}
	boolean flush(String s){
		char[] c = s.toCharArray();
		if(c[1]==c[3] && c[3]==c[5] && c[5]==c[7] && c[7]==c[9]) return true;
		return false;
	}
	boolean straight(String s){
		char[] c = s.toCharArray();
		String x = ""+c[0]+c[2]+c[4]+c[6]+c[8];
		if(value.indexOf(x) != -1) return true;
		return false;
	}
	boolean threeOfKind(String s){
		char[] c = s.toCharArray();
		// 0 2 4
		if(c[0]==c[2] && c[2]==c[4]) return true;
		// 2 4 6
		if(c[2]==c[4] && c[4]==c[6]) return true;
		// 4 6 8
		if(c[4]==c[6] && c[6]==c[8]) return true;
		return false;
	}
	boolean twoPair(String s){
		char[] c = s.toCharArray();
		// [0 2] [4 6] 8
		if(c[0]==c[2] && c[4]==c[6]) return true;
		// [0 2] 4 [6 8]
		if(c[0]==c[2] && c[6]==c[8]) return true;
		// 0 [2 4] [6 8]
		if(c[2]==c[4] && c[6]==c[8]) return true;
		return false;
	}
	boolean pair(String s){
		char[] c = s.toCharArray();
		if(c[0]==c[2] || c[2]==c[4] || c[4]==c[6] || c[6]==c[8]) return true;
		return false;
	}
	String format(StringTokenizer st){//SORT BY VALUE THEN SUIT
		String card[] = new String[5];
		for(int i=0;i<5; card[i++] = st.nextToken());
		for(int i=0;i<4;i++){
			for(int j=i+1;j<5;j++){
				int Ival = value.indexOf(card[i].charAt(0));
				int Jval = value.indexOf(card[j].charAt(0));
				if(Ival > Jval){
					String temp = card[i];
					card[i] = card[j];
					card[j] = temp;
				}
				else if(Ival == Jval){
					Ival = suit.indexOf(card[i].charAt(1));
					Jval = suit.indexOf(card[j].charAt(1));
					if(Ival > Jval){
						String temp = card[i];
						card[i] = card[j];
						card[j] = temp;
					}
				}
			}
		}
		return card[0]+card[1]+card[2]+card[3]+card[4];
	}
}