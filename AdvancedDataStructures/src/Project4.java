import java.io.*;
import java.util.StringTokenizer;


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
		
		Driver dr = new Driver();
		dr.runDriver();
		
	}
	
}

class Driver{
	
	private static final String INSERT_TOKEN = "$insert";
	private static final String DELETE_TOKEN = "$delete";
	private static final String PRINT_TOKEN = "$print";
	private static final String LINE_TOKEN = "$line";
	private static final String SEARCH_TOKEN = "$search";
	private static final String DONE_TOKEN = "$done";
	
	private static Node head;
	
	public void runDriver(){
		
	
		Node head = null;
		
		String command = "";
		StringTokenizer st = null;
		
		do {
			
			System.out.println("Enter command: ");			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				
				command = br.readLine();
						
				st = new StringTokenizer(command, " ,");
				br.close();
				
			} catch (IOException e){
				System.out.println("IO error trying to read command!");
				e.printStackTrace();
			}
			
			//tokenize command
			
			switch (st.nextToken().toString()){
				case INSERT_TOKEN:
					insertLine(head);
					break;
				case DELETE_TOKEN:
					break;
				case PRINT_TOKEN:
					break;
				case LINE_TOKEN:
					break;
				case SEARCH_TOKEN:
					break;
				case DONE_TOKEN:
					break;
				default:
					System.out.println("Invalid command.  Enter a valid command.");
					break;
			
			}
			
		} while (command.equalsIgnoreCase(DONE_TOKEN) == false);	
		
		

	}
	
	private void insertLine(Node n){
		
		System.out.println("Enter lines to insert: ");			
		BufferedReader br;
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			while (br.readLine() != null){
				Node insertNode = new Node(n, br.readLine());
				head = insertNode;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void deleteLines(int start, int finish){
		
	}
	
	/**
	 * Prints all the Strings in each Node.
	 * 
	 * @param n Head node
	 */
	private void printLines(Node n){
		
		while (n.next != null){
			System.out.println(n.st.toString());
		}
		
	}
	
	private void printLines(int start, int finish){
		
	}
	
	private void searchString(String regex){
		
	}
	
	private void doneExecution(){
		
	}

}

enum Command{
	
	INSERT_TOKEN("$insert"),
	DELETE_TOKEN("$delete"),
	PRINT_TOKEN("$print"),
	LINE_TOKEN("$line"),
	SEARCH_TOKEN("$search"),
	DONE_TOKEN("$done");	
	
	private String command_token;
	
	private Command(String token){
		this.command_token = token;
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