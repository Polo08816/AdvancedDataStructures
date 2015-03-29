import java.io.*;
import java.util.*;

/**
 * 
 */

/**
 * @author Kevin Kuo
 *
 */
public class OptionalProject1 {

	/**
	 * Creates and executes the Driver class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver dr = new Driver();

	}
}
	

class Driver{
	
	/**
	 * Runs the input from the main. 
	 */
	
	private Stack<String> operatorQueue = new Stack<String>();
	private Queue operandQueue = new LinkedList<String>();
	
	public void runDriver(){
		
		String expressionEvaluate = "";
		StringTokenizer st = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			while (expressionEvaluate.equalsIgnoreCase("quit") == false){
				System.out.println("Enter in infix expression OR 'done' if finished");
				expressionEvaluate = br.readLine();
			}
			
			//process string
			
		} catch (NoSuchElementException g){
			System.out.println("Incorrect command!");
			g.printStackTrace();
		} catch (IOException e){
			System.out.println("IO error trying to read command!");
			e.printStackTrace();
		}			
	}

}
