import java.io.*;


/**
 * @author Kevin Kuo
 *
 */
public class Project4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter command: ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String command = null;
		
		try {
			command = br.readLine();
		} catch (IOException e){
			System.out.println("IO error trying to read command!");
			e.printStackTrace();
		}
		

	}
	
	private void insertLine(){
		
	}
	
	private void deleteLines(int start, int finish){
		
	}
	
	private void printLines(){
		
	}
	
	private void printLines(int start, int finish){
		
	}
	
	private void searchString(String regex){
		
	}
	
	private void doneExecution(){
		
	}

}

class Node {
	
	String st;
	Node next;
	
	public Node(){
		
		this.st = "";
		this.next = null;
		
	}
	
	public Node(Node next, String st){
		
		this.next = next;
		this.st = st;
		
	}
	
}