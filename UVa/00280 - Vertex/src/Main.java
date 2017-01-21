import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	//00280 - Vertex
	int n;
	ArrayList<ArrayList<Integer>> path;
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		StringTokenizer st;
		while(n!=0){
			path = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<n;i++) path.add(new ArrayList<Integer>());
			while(true){
				int start = sc.nextInt();
				if(start==0) break;
				int end = sc.nextInt();
				while(end!=0){
					path.get(start-1).add(end-1);
					end = sc.nextInt();
				}
			}
			int m = sc.nextInt();
			for(int i=0;i<m;i++)  BFS(sc.nextInt()-1);
			n = sc.nextInt();
		}
	}
	void BFS(int start){
		Queue<Integer> qe = new LinkedList<Integer>();
		boolean visited[] = new boolean[n];
		int count = 0;
		qe.add(start);
		while(!qe.isEmpty()){
			int v1 = qe.poll();
			for(Integer v2 : path.get(v1)){
				if(!visited[v2]){
					visited[v2] = true;
					qe.add(v2);
					count++;
				}
			}
		}
		System.out.print(n-count);
		for(int i=0;i<n;i++){
			if(!visited[i]) System.out.print(" "+(i+1));
		}
		System.out.println();
	}
}
