import java.io.*;


/**
 * @author Kevin Kuo
 *
 */
public class Project4 {
	
	private static final String INSERT_TOKEN = "$insert";
	private static final String DELETE_TOKEN = "$delete";
	private static final String PRINT_TOKEN = "$print";
	private static final String LINE_TOKEN = "$line";
	private static final String SEARCH_TOKEN = "$search";
	private static final String DONE_TOKEN = "$done";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String command = "";
		
		do {
			
			System.out.println("Enter command: ");			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				command = br.readLine();
			} catch (IOException e){
				System.out.println("IO error trying to read command!");
				e.printStackTrace();
			}
			
			//tokenize command
			
			switch (command){
				case INSERT_TOKEN: 
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