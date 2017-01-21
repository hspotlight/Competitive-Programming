import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
	//00230 - Borrowers
	int[] book; //val: 0 = on shelve, 1 = borrow, 2 = return
	ArrayList<Book> shelve = new ArrayList<Book>();
	HashMap<String, Integer> atoi = new HashMap<String, Integer>();
	HashMap<Integer, String> itoa = new HashMap<Integer, String>();
	public static void main(String[] args) throws Exception{
		new Main().run();
	}
	void run()throws Exception{	
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while(!(line = bfr.readLine()).equals("END")){
			int DQpos = line.indexOf("\"", 1);
			String title = line.substring(0, DQpos + 1);
			String author = line.substring(DQpos + 5);
			shelve.add(new Book(title, author));
		}
		
		Collections.sort(shelve);
		for(int i=0;i<shelve.size();i++){
			atoi.put(shelve.get(i).title, i);
			itoa.put(i, shelve.get(i).title);
		}
		book = new int[shelve.size()];
		
		while(!(line = bfr.readLine()).equals("END")){
			String cmd = line.substring(0, 6);
			if(cmd.equals("SHELVE")){
				ArrayList<Integer> s = new ArrayList<Integer>();
				ArrayList<Integer> r = new ArrayList<Integer>();
				ArrayList<Integer> m = new ArrayList<Integer>();
				for(int i=0;i<book.length;i++){
					if(book[i]==0) s.add(i);
					else if (book[i]==2) { r.add(i); book[i] = 0; }
				}
				//merge s and r
				int i = 0, j = 0;
				while(i < s.size() && j < r.size()){
					int a = s.get(i), b = r.get(j);
					if(a < b) { m.add(a); i++; }
					else{//print
						if(m.isEmpty()){
							System.out.println("Put "+itoa.get(b)+" first");
						}
						else{
							System.out.println("Put "+itoa.get(b)+" after "+itoa.get(m.get(m.size()-1)));
						}
						m.add(b);
						j++;
					}
				}
				while(j < r.size()){
					int b = r.get(j);
					if(m.isEmpty()){
						System.out.println("Put "+itoa.get(b)+" first");
					}
					else{
						System.out.println("Put "+itoa.get(b)+" after "+itoa.get(m.get(m.size()-1)));
					}
					m.add(b);
					j++;
				}
				System.out.println("END");
			}
			else{
				String title = line.substring(7, line.length());
				int i = atoi.get(title);
				if(cmd.equals("BORROW")){
					if(book[i]!=1) book[i] = 1;
				}
				else{
					if(book[i]==1) book[i] = 2;
				}
			}
		}
	}
	public class Book implements Comparable<Book>{
		String title, author;
		public Book(String title, String author){
			this.title = title; this.author = author;
		}
		public int compareTo(Book b){
			if(author.compareTo(b.author) == 0)
				return title.compareTo(b.title);
			return author.compareTo(b.author);
		}
		public String toString(){ return title; }
	}
}
