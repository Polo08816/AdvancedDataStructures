import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		dr.runDriver();

	}
}
	

class Driver{
	
	/**
	 * Runs the input from the main. 
	 */
	
	private Stack<String> operatorStack;
	private Queue<Double> operandQueue;
	
	public void runDriver(){
		
		operatorStack = new Stack<String>();
		operandQueue = new LinkedList<Double>();
		
		String expressionEvaluate = "";
		StringTokenizer st = null;
		String token = "";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			while (expressionEvaluate.equalsIgnoreCase("quit") == false){
				System.out.println("Enter in infix expression OR 'done' if finished");
				expressionEvaluate = br.readLine();
				st = new StringTokenizer(expressionEvaluate);
				
				while (st.hasMoreTokens()){
					token = st.nextToken().toString();
					
					Pattern pattern = Pattern.compile("([0-9]*)(\\.[0-9]*)?");
					Matcher matcher = pattern.matcher(token);
					boolean matches = matcher.matches();
					
					// is a number - enqueue in operandQueue
					if (matches){
						operandQueue.add(Double.parseDouble(token));
					} else {
						// if stack is EMPTY push the token onto the stack
						if (operatorStack.isEmpty()){
							operatorStack.push(token);
						// if token equals '(' then push to stack
						} else if (token.equalsIgnoreCase("(")){
							operatorStack.push(token);
						// if token equals ')' pop everything that is not '('
						} else if (token.equalsIgnoreCase("(")){
							//check if next on stack is '('
							while (operatorStack.peek().equalsIgnoreCase("(") == false){
								//add to operandQueue everything that you pop off operator stack
								operandQueue.add(Double.parseDouble(operatorStack.pop()));
							}
							operatorStack.pop();
						}
						
						
					}
				}
				
				
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
