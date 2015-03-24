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
		
		boolean insertMode = false;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			
			System.out.println("Enter command or line: ");		
			
			try {
				
				command = br.readLine();
						
				st = new StringTokenizer(command, " ,");
				firstCommand = st.nextToken().toString().trim();
				
			} catch (NoSuchElementException g){
				System.out.println("Incorrect command!");
//				g.printStackTrace();
			} catch (IOException e){
				System.out.println("IO error trying to read command!");
//				e.printStackTrace();
			}			
			
			switch (firstCommand){
				case INSERT_TOKEN:
					insertMode = true;
					insertLine(head, "");
					break;
				case DELETE_TOKEN:				
					insertMode = false;
					if (st.hasMoreTokens()){
						try{
							mLow = Integer.parseInt(st.nextToken());
							nHigh = Integer.parseInt(st.nextToken());							
							if (mLow > nHigh || mLow < 1 || nHigh < 1){
								System.out.println("deleteLines: incorrect argument(s): first line # > last line # AND/OR line number less than 1");
							} else {
								deleteLines(head, mLow, nHigh);
							}
						} catch (NoSuchElementException f){
							System.out.println("Missing arguments");
//							f.printStackTrace();
						}
					} else {
						printLines(head);
					}		
					break;
				case PRINT_TOKEN:
					insertMode = false;
					if (st.hasMoreTokens()){
						try{
							mLow = Integer.parseInt(st.nextToken());
							nHigh = Integer.parseInt(st.nextToken());							
							if (mLow > nHigh && mLow < 1 || nHigh < 1){
								System.out.println("printLines: incorrect argument(s): first line # > last line # AND/OR line number less than 1");
							} else {
								printLines(head, mLow, nHigh);
							}
						} catch (NoSuchElementException f){
							System.out.println("Missing arguments");
//							f.printStackTrace();
						}
					} else {
						printLines(head);
					}
					break;
				case LINE_TOKEN:
					insertMode = false;
					setCurrentLine(head, Integer.parseInt(st.nextToken().toString()));
					break;
				case SEARCH_TOKEN:
					insertMode = false;
					String searchString = "";
					
					while (st.hasMoreTokens()){
						searchString = searchString.concat(st.nextToken() + " ");
					}
					
					if (searchString.equalsIgnoreCase("")){
						System.out.println("You did not enter a valid search String");
					} else {
						searchString(head, searchString);
					}
					
					break;
				case DONE_TOKEN:
					insertMode = false;
					System.out.println("Program is exiting.");
					return;
				default:
					if (insertMode == true){
						insertLine(head, command);
					} else {
						System.out.println("Invalid command.  Enter a valid command.");
						break;
					}		
			
			}
			
		} while (firstCommand.equalsIgnoreCase(DONE_TOKEN) == false);
		
//		br.close();

	}
	
	private void insertLine(Node n, String lineCommand){
		
		if (n == null){
			System.out.println("Enter lines to insert: ");	
		}
		

				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node insertNode;
		String line;		
		
		try {
			
			if (lineCommand.isEmpty()){
				line = br.readLine();
			} else {
				line = lineCommand;
			}
			
			if (n == null){
				
				insertNode = new Node(null, line);
				
				head = insertNode;
				return;
			}
			if (currentLine == null){
				while (n.next != null){
					n = n.next;				
				}
				insertNode = new Node(null, line);
				n.next = insertNode;
			} else if (currentLine == head){
				insertNode = new Node(head, line);
				head = insertNode;
			} else {
				insertNode = new Node(currentLine.next, line);
				currentLine.next = insertNode;
				currentLine = currentLine.next;
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("insertLine error!");
			e.printStackTrace();
		}
		
	}
	
	private void deleteLines(Node headNode, int start, int finish){
		
		if (headNode == null){
			System.out.println("There are no nodes to delete");
			return;
		}
		
		int count = 1;
		Node startNode, endNode;
		
		startNode = headNode;
		endNode = startNode;
		
		while (endNode != null){
			
			if (count + 1 < start){
				startNode = startNode.next;
			}
			
			if (count < finish){
				endNode = endNode.next;
			}
			
			if (count == finish){
				break;
			}
			
			count++;

		}
		
		if (start == 1){
			head = endNode.next;
		}
		
		startNode.next = endNode.next;
	}
	
	
	private void setCurrentLine(Node headNode, int m){
		
		if (headNode == null){
			System.out.println("Current node not set.");
			return;
		} 
		
		int count = -1;
		
//		if (m == 1){
//			currentLine = head;
//		} else {
//			while (headNode.next != null){
//				count++;
//				
//				if (count == (m - 1)){
//					
////					currentLine = headNode;
//						
//					System.out.println("Line SET: " + (count + 1) + " : " + headNode.lineString.toString());
//					break;
//				}
//				currentLine = headNode;
//				headNode = headNode.next;
//			}
//		}
		
		while (headNode != null){
			count++;
			
			if (count == (m - 1)){
				
				if (count == 0){
					currentLine = headNode;
				}			
					
				System.out.println("Line SET: " + (count + 1) + " : " + headNode.lineString.toString());
				break;
			}
			currentLine = headNode;
			headNode = headNode.next;
		}
		
		printLines(head, m-3, m+3);

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
			
			if (count >= m && count <= n){
				System.out.println("Line " + count + " : " + headNode.lineString.toString());
			}			
			headNode = headNode.next;
		}
		
	}
	
	private void searchString(Node headNode, String regex){
		
		int count = 0;
		
		while (headNode != null && headNode.lineString.toString().isEmpty() == false){
			count++;
			if (headNode.lineString.toString().contains(regex.trim())){
//				System.out.println("String searched: " + regex);
				System.out.println("Line " + count + " : " + headNode.lineString.toString());
				return;
			}
			headNode = headNode.next;
		}
		System.out.println("String searched: " + regex + " NOT FOUND!");
	}

}

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