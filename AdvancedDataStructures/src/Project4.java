import java.io.*;
import java.util.StringTokenizer;


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
	
	private Node head = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		String command = "";
		StringTokenizer st;
		
		do {
			
			System.out.println("Enter command: ");			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				
				command = br.readLine();
						
				while (command != null){
					st = new StringTokenizer(command);
					while (st.hasMoreTokens()){
						try {

//							tempInt = Integer.parseInt(st.nextToken());
						} catch (NumberFormatException h){
							System.out.println("\nWarning: Not all elements were able to be parsed correctly.\nAll elements are expected to be integers.  \nCheck the delimiters used in input file.\n");
							h.printStackTrace();
//							System.out.println("\n" + inputFileName + ": Attempted to parse: " + st.toString());
							continue;
						}
						
					}
					command = br.readLine();
				}	
				br.close();
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
	
	private void insertLine(String line){
		
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