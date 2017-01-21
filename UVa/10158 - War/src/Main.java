import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	//10158 - War
	int N;
	int parent[];
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int) stk.nval; }
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		stk = new StreamTokenizer(bfr);
		
		N = readInt();
		parent = new int[N+N];
		for(int i=0;i<parent.length;i++) parent[i] = i;
		
		while(true){
			int op = readInt(), u = readInt(), v = readInt();
			if(op==0 && u==0 && v==0) break; //terminate case
			switch(op){
			case 1: //setFriend()
				if (!setFriend(u, v)) System.out.println("-1");
				break;
			case 2: //setEnemy()
				if(!setEnemy(u, v)) System.out.println("-1");
				break;
			case 3: //areFriend()
				System.out.println(areFriend(u, v)? 1 : 0);
				break;
			case 4: //areEnemy()
				System.out.println(areEnemy(u, v)? 1 : 0);
				break;
			}
		}
	}
	
	int find(int node){
		return parent[node] == node ? node : (parent[node] = find(parent[node]));
	}
	boolean setFriend(int u, int v){
		if(areEnemy(u, v)) return false;
		//else them can become friend
		int setU = find(u*2 + 0), setUTwin = find(u*2 + 1);
		int setV = find(v*2 + 0), setVTwin = find(v*2 + 1);
		parent[setU] = setV;
		parent[setUTwin] = setVTwin;
		return true;
	}
	boolean setEnemy(int u, int v){
		if(areFriend(u, v)) return false;
		int setU = find(u*2 + 0), setUTwin = find(u*2 + 1);
		int setV = find(v*2 + 0), setVTwin = find(v*2 + 1);
		parent[setU] = setVTwin;
		parent[setUTwin] = setV;
		return true;
	}
	boolean areFriend(int u, int v){
		int setU = find(u*2 + 0);
		int setV = find(v*2 + 0);
		if(setU == setV) return true;
		return false;
	}
	boolean areEnemy(int u, int v){
		int setU = find(u*2 + 0);
		int setV = find(v*2 + 1);
		if(setU == setV) return true;
		return false;
	}
}
