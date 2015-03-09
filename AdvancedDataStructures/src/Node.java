/**
 * 
 */

/**
 * @author Kevin Kuo
 *
 */
public class Node {
	
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