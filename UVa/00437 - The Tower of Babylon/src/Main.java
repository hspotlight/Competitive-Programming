import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	//00437 - The Tower of Babylon
	int N;
	StreamTokenizer stk;
	int readInt() throws Exception { stk.nextToken(); return (int) stk.nval; }
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		int c = 1;
		while((N = readInt())!=0){
			ArrayList<Block> list = new ArrayList<Block>();
			for(int i=0;i<N;i++){
				int x = readInt(), y = readInt(), z = readInt();
				list.add(new Block(x,y,z));
				list.add(new Block(y,z,x));
				list.add(new Block(z,x,y));
			}
			Collections.sort(list);
			int size = list.size();
			int totalHeight[] = new int[size];
			int max = totalHeight[0] = list.get(0).height;
			for(int i=1;i<size;i++){//current block
				Block cur = list.get(i);
				for(int j=0;j<i;j++){//small block
					Block last = list.get(j);
					if(last.width < cur.width && last.length < cur.length){//can concat
						if(totalHeight[j] > totalHeight[i]){
							totalHeight[i] = totalHeight[j]; 
						}
					}
				}
				totalHeight[i] += cur.height;//itself height
				max = Math.max(max, totalHeight[i]);
			}
			System.out.println("Case "+(c++)+": maximum height = "+max);
		}
	}
	public class Block implements Comparable<Block>{
		int height;
		int width;
		int length;
		public Block(int h, int w, int l){
			height = h;
			if(w<l){ width = w; length = l; }
			else { width = l; length = w; }
		}
		public int compareTo(Block b){
			if(width != b.width) return width - b.width;
			else if (length != b.length) return length - length;
			return height - b.height;
		}
		public String toString(){ return height+" ("+width+", "+length+")"; }
	}
}