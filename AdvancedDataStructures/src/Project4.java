import java.io.*;
import java.util.*;


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
	private static Node currentLine;
	
	public void runDriver(){
		
	
		head = null;
		currentLine = null;
		
		String command = "";
		String firstCommand = "";
		StringTokenizer st = null;
		int mLow, nHigh;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			
			System.out.println("Enter command: ");			
			
			
			try {
				
				command = br.readLine();
						
				st = new StringTokenizer(command, " ,");
				firstCommand = st.nextToken().toString().trim();
				
				
			} catch (NoSuchElementException g){
				System.out.println("Incorrect command!");
				g.printStackTrace();
			} catch (IOException e){
				System.out.println("IO error trying to read command!");
				e.printStackTrace();
			}
			
			
			
			switch (firstCommand){
				case INSERT_TOKEN:
					insertLine(head);
					break;
				case DELETE_TOKEN:				
					
					if (st.hasMoreTokens()){
						try{
							mLow = Integer.parseInt(st.nextToken());
							nHigh = Integer.parseInt(st.nextToken());							
							if (mLow > nHigh && mLow < 1 || nHigh < 1){
								System.out.println("deleteLines: incorrect argument(s): first line # > last line # AND/OR line number less than 1");
							} else {
								deleteLines(mLow, nHigh);
							}
						} catch (NoSuchElementException f){
							System.out.println("Missing arguments");
							f.printStackTrace();
						}
					} else {
						printLines(head);
					}		
					break;
				case PRINT_TOKEN:
					if (st.hasMoreTokens()){
						try{
							mLow = Integer.parseInt(st.nextToken());
							nHigh = Integer.parseInt(st.nextToken());							
							if (mLow > nHigh && mLow < 1 || nHigh < 1){
								System.out.println("printLines: incorrect argument(s): first line # > last line # AND/OR line number less than 1");
							} else {
								
							}
						} catch (NoSuchElementException f){
							System.out.println("Missing arguments");
							f.printStackTrace();
						}
					} 
					//printLines
					printLines(head);
					break;
				case LINE_TOKEN:
					break;
				case SEARCH_TOKEN:
					break;
				case DONE_TOKEN:
					System.out.println("Program is exiting.");
					break;
				default:
					System.out.println("Invalid command.  Enter a valid command.");
					break;
			
			}
			
		} while (firstCommand.equalsIgnoreCase(DONE_TOKEN) == false);
		
//		br.close();

	}
	
	private void insertLine(Node n){
		
		System.out.println("Enter lines to insert: ");			
		BufferedReader br;
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while (br.readLine() != null){
				Node insertNode = new Node(n, br.readLine());
				head = insertNode;
				System.out.println("Enter lines to insert: ");
			}
			
			//logic to stop 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("insertLine error!");
			e.printStackTrace();
		}
		
	}
	
	private void deleteLines(int start, int finish){
		
	}
	
	/**
	 * Prints all the Strings in each Node.
	 * 
	 * @param headNode Head node
	 */
	private void printLines(Node headNode){
		
		int count = 0;
		
		while (headNode != null && headNode.lineString.toString().isEmpty() == false){
			count++;
			System.out.println("Line " + count + " : " + headNode.lineString.toString());
			headNode = headNode.next;
		}
		
	}
	
	/**
	 * Prints all the Strings in each Node from M to N inclusive.
	 * 
	 * @param headNode Head node
	 * @param m Earlier line number.
	 * @param n Later line number.
	 */
	private void printLines(Node headNode, int m, int n){
		
		int count = 0;
		
		while (headNode != null && headNode.lineString.toString().isEmpty() == false){
			count++;
			System.out.println("Line " + count + " : " + headNode.lineString.toString());
			headNode = headNode.next;
		}
		
	}
	
	private void searchString(String regex){
		
	}
	
	private void doneExecution(){
		
	}

}

//enum Command{
//	
//	INSERT_TOKEN("$insert"),
//	DELETE_TOKEN("$delete"),
//	PRINT_TOKEN("$print"),
//	LINE_TOKEN("$line"),
//	SEARCH_TOKEN("$search"),
//	DONE_TOKEN("$done");	
//	
//	private String command_token;
//	
//	private Command(String token){
//		this.command_token = token;
//	}
//	
//}

class Node {
	
	String lineString;
	Node next;
	
	public Node(){
		
		this.lineString = "";
		this.next = null;
		
	}
	
	public Node(Node next, String st){
		
		this.next = next;
		this.lineString = st;
		
	}
	
}