import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	//10051 - Tower of Cubes
	int N;
	int[][] blocks;
	ArrayList<Block>[] list;
	String[] side = { "front", "back", "left", "right", "top", "bottom" };
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int testcase = 1;
		StringBuilder str = new StringBuilder();
		while((N = readInt())!=0){
			
			blocks = new int[N][6];
			for(int i=0;i<N;i++){
				for(int j=0;j<6;j++)
					blocks[i][j] = readInt();
			}
			
			int maxLength = 0;
			list = new ArrayList[N+1];
			for(int i=0;i<=N;i++) list[i] = new ArrayList<Block>();
			
			for(int i=0;i<N;i++){ //each block //weight = i+1
				for(int j=0;j<6;j++){//each side of block [bottom]
					
					int newMax = maxLength;
					boolean connected = false;
					Block cur = new Block(i+1, blocks[i][j], side[j^1], null);//(weight, bottom color, topFace, topBlock)
					
					for(int len = maxLength;len>=0;len--){
						if(len == 0) list[1].add(cur);
						else{
							for(Block top : list[len]){
								if(top.weight < cur.weight && blocks[i][j^1] == top.bottom){//weight & color
									
									cur.topBlock = top; list[len+1].add(cur);
									if(len == maxLength) newMax = maxLength + 1;
									
									connected = true;
									break;
								}
							}
							if(connected) break;
						}
					}
					maxLength = maxLength == 0 ? 1 : newMax;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			Block cur = list[maxLength].get(0);
			
			do{
				sb.insert(0, cur.toString()+"\n");
				cur = cur.topBlock;
			}
			while(cur != null);
			
			str.append("Case #"+(testcase++)+"\n");
			str.append(maxLength+"\n");
			str.append(sb+"\n");
		}
		str.deleteCharAt(str.length()-1);
		System.out.print(str);
	}
	public class Block{
		int weight, bottom;
		String topSide;
		Block topBlock;
		public Block(int w, int b, String top, Block tb){
			weight = w;
			bottom = b;
			topSide = top;
			topBlock = tb;
		}
		public String toString() { return ""+weight+" "+topSide; }
	}
}
