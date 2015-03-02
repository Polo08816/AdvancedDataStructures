
import java.io.*;
import java.util.*;



/**
 * @author Kevin Kuo
 *
 */
public class Project2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class BankAccount{
	
	private String name;
	private int accountNumber;
	private double balance;
	
	/**
	 * Public constructor for a base class of Accounts
	 */
	public BankAccount(){
	
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

}

class CheckingAccount extends BankAccount{
	
	private double interest;
	
	
	
}

class Driver {
	
	/**
	 * Reads data from text file and stores data an ArrayList of Objects.
	 * 
	 * <p>
	 * 
	 * This function expects to read "input.txt" from the current working directory.  This function also
	 * performs summation of all scores read.  
	 * Error handling for this function includes FileNotFound and general IOExcetions.
	 * 
	 * @param intAl an ArrayList of Integers of scores
	 * @return sum Sum of all scores in ArrayList
	 */
	public int readFile(ArrayList<Integer> intAl){
	
		BufferedReader br;
		String line, inputFileName;
		StringTokenizer st;
		
		int sum = 0;
		
		//Specifies the file name in the current working directory
		inputFileName = "project2_input.txt";
		
		try {
			
			//Reads "input.txt"		
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + inputFileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + inputFileName));

			line = br.readLine();

			while (line != null){
				st = new StringTokenizer(line);
				while (st.hasMoreTokens()){
					int tempInt = 0;
					try {
						tempInt = Integer.parseInt(st.nextToken());
					} catch (NumberFormatException h){
						System.out.println("\nWarning: Not all elements were able to be parsed correctly.\nAll elements are expected to be integers.  \nCheck the delimiters used in input file.\n");
						h.printStackTrace();
//						System.out.println("\n" + inputFileName + ": Attempted to parse: " + st.toString());
						continue;
					}
					
					intAl.add(tempInt);
					sum = sum + tempInt;
				}
				line = br.readLine();
			}	
			br.close();
		} catch (FileNotFoundException f){
			intAl.clear();
			System.out.println("\n" + inputFileName + " was not found in the current working directory.\n");
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			intAl.clear();
			System.out.println("Error tokenizing the input stream");
			e.printStackTrace();
		}
		
		System.out.println("\nNumber of ArrayList elements: " + intAl.size());		
		System.out.println("\nSum: " + sum + "\n");
		
		return sum;		
	}
	
}
