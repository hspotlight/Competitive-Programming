import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//10092 - The Problem with the Problem Setter
	int N, P;
	int source, sink;
	int adjMatrix[][], parent[], belongTo[];
	StreamTokenizer stk;
	int readInt() throws Exception{ stk.nextToken(); return(int)stk.nval; }
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		stk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		while((N=readInt())!=0 && (P=readInt())!=0){
			
			adjMatrix = new int[N+P+2][N+P+2]; //[0 - N-1][N - N+P-1]
			belongTo = new int[P];
			Arrays.fill(belongTo, -1);
			source = N+P; sink = source+1;
			
			int sumCapacity = 0;
			for(int i=0;i<N;i++){
				int capacity = readInt();
				sumCapacity += (adjMatrix[source][i] = capacity);
			}
			
			for(int i=0;i<P;i++){
				int n = readInt();
				while(n-->0){
					int category = readInt() - 1;
					adjMatrix[category][N+i] = 1;
				}
			}
			
			for(int i=0;i<P;i++){
				adjMatrix[N+i][sink] = 1;
			}
			
			//********************************************************//
			int problemCounter = 0;
			while(true){
				parent = new int[N+P+2];
				Arrays.fill(parent, -1);
				
				//find aungmented path
				BFS();
				if(parent[sink]==-1) break;
				
				//update max flow
				problemCounter++;
				
				//bottleneck = 1
				//construct residual graph
				int v = sink, u = parent[v];
				do{
					adjMatrix[u][v] -= 1;
					adjMatrix[v][u] += 1;
					//u = category && v = problem
					if((u < N) && (N<=v && v<N+P)) belongTo[v-N] = u;
					//v = category && u = problem
					if((v < N) && (N<=u && u<N+P)) belongTo[u-N] = -1;
					v = u; u = parent[v];
				}
				while(u!=-99);
			}
			
			if(problemCounter == sumCapacity){
				ArrayList<Integer>[] categories = new ArrayList[N];
				for(int i=0;i<N;i++) categories[i] = new ArrayList<Integer>();
				for(int i=0;i<P;i++) {
					if(belongTo[i]!=-1) categories[ belongTo[i] ].add(i);
				}
				System.out.println(1);
				for(int i=0;i<N;i++){
					boolean first = true;
					String cat = "";
					for(int p : categories[i]){
						cat += (first?"":" ")+(p+1);
						first = false;
					}
					System.out.println(cat);
				}
			}
			else System.out.println(0);
		}
	}
	void BFS(){
		Queue<Integer> qe = new LinkedList<Integer>();
		qe.add(source); parent[source] = -99;
		while(!qe.isEmpty()){
			int u = qe.poll();
			for(int v=0;v<N+P+2;v++){
				if(parent[v]==-1 && adjMatrix[u][v]>0){
					parent[v] = u;
					if(v == sink) return;
					qe.add(v);
				}
			}
		}
	}
	
}
