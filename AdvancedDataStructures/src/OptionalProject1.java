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
	private Queue<String> operandQueue;
	private List<String> postfixString;
	
	public void runDriver(){
		
		operatorStack = new Stack<String>();
		operandQueue = new LinkedList<String>();
		postfixString = new LinkedList<String>();
		
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
					token = st.nextToken().toString().trim();
					
					Pattern pattern = Pattern.compile("([0-9]*)(\\.[0-9]*)?");
					Matcher matcher = pattern.matcher(token);
					boolean matches = matcher.matches();
					
					// is a number - enqueue in operandQueue
					if (matches){
						operandQueue.add(token);
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
							while (operatorStack.empty() == false && operatorStack.peek().equalsIgnoreCase("(") == false){
								//add to operandQueue everything that you pop off operator stack
								operandQueue.add(operatorStack.pop());
							}
							if (operatorStack.isEmpty() == false){
								operatorStack.pop();
							}							
						}
						// check priority of Ps and Ptop
						while (operatorStack.isEmpty() == false && lowerPriority(token, operatorStack.peek())){
							operandQueue.add(operatorStack.pop());
						}						
					}
					operatorStack.push(token);
					
				}
				
				while (operatorStack.empty() == false){
					operandQueue.add(operatorStack.pop());
				}
				
				postfixString.clear();
				
				
				while (operandQueue.peek() != null){
					postfixString.add(operandQueue.element());
					operandQueue.remove();
				}
				
				System.out.println(postfixString);
				
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
	
	private boolean isOperator(char c){
		return c == '+'  ||  c == '-'  ||  c == '*'  ||  c == '/'  ||  c == '^'
		           || c=='(' || c==')';
	}
	
	
	/**
	 * Determines whether first operator has a lower precedence than second operator.
	 * 
	 * First operator is on the left.  Second operator is on the right.
	 * 
	 * @param firstOperator
	 * @param secondOperator
	 * @return
	 */
	private boolean lowerPriority(String firstOperator, String secondOperator){
		
		switch (firstOperator){
		
			case "+":
			case "-":
				return !(secondOperator.equalsIgnoreCase("+") || secondOperator.equalsIgnoreCase("-"));
			
			case "*":
			case "/":
				return secondOperator.equalsIgnoreCase("^") || secondOperator.equalsIgnoreCase("(");
		
			case "^":
				return secondOperator.equalsIgnoreCase("(");
				
			case "(":
				return true;
				
			default:
				return false;
		}
	}

}
