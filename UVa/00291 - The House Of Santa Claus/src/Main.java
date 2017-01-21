public class Main {
	//00291 - The House Of Santa Claus
	int edgeCnt;
	boolean matrix[][];
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		matrix = new boolean[6][6];
		for(int i=1;i<=5;i++){
			for(int j=i+1;j<=5;j++){
				if(j==4 && i < 3) continue;
				matrix[i][j] = matrix[j][i] = true;
			}
		}
		edgeCnt = 8;
		traversal(1,"1");
	}
	void traversal(int i, String path){
		if(edgeCnt==0) System.out.println(path);
		else{
			for(int j=1;j<=5;j++){
				if(matrix[i][j]){
					matrix[i][j] = matrix[j][i] = false;//remove
					edgeCnt--;
					traversal(j,path+j);
					edgeCnt++;
					matrix[i][j] = matrix[j][i] = true;//add
				}
			}
		}
	}
}