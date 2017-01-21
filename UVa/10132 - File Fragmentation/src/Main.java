import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
	//10132 - File Fragmentation
	public static void main(String [] args) throws Exception{
		new Main().run();
	}
	void run() throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bfr.readLine());		bfr.readLine();
		while(tc-->0){
			int cnt = 0, sum = 0;
			ArrayList<String>[] list = new ArrayList[256]; //0-255
			for(int i=0;i<256;i++) list[i] = new ArrayList<String>();
			String line;
			while((line = bfr.readLine()) != null){//last test case
				if(line.isEmpty()) break;//end of test case
				int len = line.length();
				list[len].add(line);
				sum += len;
				cnt += 1;
			}
			
			if(cnt%2==0){
				int orgLen = ((sum*2)/cnt); //must be biggest
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				
				for(int i=0;i<256;i++){
					if(!list[i].isEmpty()){
						int j = orgLen - i;
						if(0<=j && j<256 && !list[j].isEmpty()){
							for(String I : list[i]){
								for(String J : list[j]){
									String IJ = I+J;
									if(!map.containsKey(IJ)) map.put(IJ, 1);
									else map.put(IJ, map.get(IJ)+1);
								}
							}
						}
					}
				}
				
				Set<Entry<String, Integer>> set = map.entrySet();
				int max = -1;
				String org = "";
				for(Entry<String, Integer> e : set){
					if(e.getValue()>max){
						max = e.getValue();
						org = e.getKey();
					}
				}
				
				System.out.println(org);
				if(tc>0) System.out.println();
			}
		}
	}
}