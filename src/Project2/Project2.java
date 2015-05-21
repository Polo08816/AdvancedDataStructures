package Project2;

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
		
		ArrayList<BankAccount> al = new ArrayList<BankAccount>();
		
		Driver dr = new Driver();
		dr.readFile(al);
		
		printArrayList(al);
		
		applyInterest(al);
		
		
		
		FileWriter outputFileWriter = dr.createOutputFile();
		
		if (outputFileWriter == null){
			System.out.println("Error writing to output file");
			return;
		}
		
		writeArrayList(al, outputFileWriter);

	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param intAl ArrayList of Integers
	 * @throws IOException 
	 */
	private static void writeArrayList(ArrayList<BankAccount> al, FileWriter fw) {
		
		String seperator = "\t\t";
		BufferedWriter bw = new BufferedWriter(fw);
		
		System.out.println("\nPrinting contents of ArrayList:\n");
		
		Iterator<BankAccount> it = al.iterator();
		while (it.hasNext()){
			BankAccount ba = it.next();
			
			
			try {
				bw.write(ba.getName() + seperator + ba.getAccountNumber() + seperator + ba.getPhoneNumber() + seperator + ba.getSocialSecurityNumber() + seperator + ba.getOpenBalance() + seperator + ba.getCloseBalance());
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param intAl ArrayList of Integers
	 */
	private static void printArrayList(ArrayList<BankAccount> al){
		
		System.out.println("\nPrinting contents of ArrayList:\n");
		
		Iterator<BankAccount> it = al.iterator();
		while (it.hasNext()){
			BankAccount ba = it.next();
			String seperator = "\t\t";
			System.out.println(ba.getName() + seperator + ba.getAccountNumber() + seperator + ba.getPhoneNumber() + seperator + ba.getSocialSecurityNumber() + seperator + ba.getOpenBalance() + seperator + ba.getCloseBalance());
		}
	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param intAl ArrayList of Integers
	 */
	private static void applyInterest(ArrayList<BankAccount> al){
		
		System.out.println("\nApplying interest to closing balances.\n");
		
		Iterator<BankAccount> it = al.iterator();
		while (it.hasNext()){
			BankAccount ba = it.next();
			
			if (ba instanceof CheckingAccount){
				ba.setCloseBalance(ba.getOpenBalance()*1.025);
			} else if (ba instanceof SavingsAccount){
				if (ba.getOpenBalance() < 5000){
					ba.setCloseBalance(ba.getOpenBalance()*1.04);
				} else {
					ba.setCloseBalance(ba.getOpenBalance()*1.05);
				}
			} else {
				//ba is instanceof Business Account
				ba.setCloseBalance(ba.getOpenBalance());
			}
			
			String seperator = "\t\t";
			System.out.println(ba.getName() + seperator + ba.getAccountNumber() + seperator + ba.getPhoneNumber() + seperator + ba.getSocialSecurityNumber() + seperator + ba.getOpenBalance() + seperator + ba.getCloseBalance());
		}
	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param intAl ArrayList of Integers
	 */
	private static void endMonth(ArrayList<BankAccount> al){
		
		System.out.println("\nSetting closing balances to next month's opening balances.\n");
		
		Iterator<BankAccount> it = al.iterator();
		while (it.hasNext()){
			BankAccount ba = it.next();
			
			ba.setOpenBalance(ba.getCloseBalance());
			
			String seperator = "\t\t";
			System.out.println(ba.getName() + seperator + ba.getAccountNumber() + seperator + ba.getPhoneNumber() + seperator + ba.getSocialSecurityNumber() + seperator + ba.getOpenBalance() + seperator + ba.getCloseBalance());
		}
	}

}

class BankAccount{
	
	private String fullName, accountNumber, socialSecurityNumber, phoneNumber;
	private double openBalance, closeBalance;
	
	/**
	 * Public constructor for a base class of Accounts
	 */
	public BankAccount(){
	
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return fullName;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.fullName = name;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the socialSecurityNumber
	 */
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	/**
	 * @param socialSecurityNumber the socialSecurityNumber to set
	 */
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * @return the openBalance
	 */
	public double getOpenBalance() {
		return openBalance;
	}

	/**
	 * @param openBalance the openBalance to set
	 */
	public void setOpenBalance(double openBalance) {
		this.openBalance = openBalance;
	}

	/**
	 * @return the closeBalance
	 */
	public double getCloseBalance() {
		return closeBalance;
	}

	/**
	 * @param closeBalance the closeBalance to set
	 */
	public void setCloseBalance(double closeBalance) {
		this.closeBalance = closeBalance;
	}

	/**
	 * 
	 */
	public void endMonthBalace(){
		this.closeBalance = this.openBalance;
	}

}

class CheckingAccount extends BankAccount{
		
}

class BusinessAccount extends BankAccount{
	
}

class SavingsAccount extends BankAccount{
	
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
	public void readFile(ArrayList<BankAccount> baAl){
	
		BufferedReader br;
		String line, inputFileName;
		StringTokenizer st;
		
		//Specifies the file name in the current working directory
		inputFileName = "project2_input.txt";
		
		try {
			
			//Reads "input.txt"		
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + inputFileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + inputFileName));

			line = br.readLine();

			while (line != null){
				st = new StringTokenizer(line);
				BankAccount ba = new BankAccount();
				String type;
				while (st.hasMoreTokens()){
					try {
						
						String name, accountNumber, phoneNumber, socialSecurityNumber;
						double openBalance;
						
						name = st.nextToken();
						accountNumber = st.nextToken();
						phoneNumber = st.nextToken();
						socialSecurityNumber = st.nextToken();
						openBalance = Double.parseDouble(st.nextToken());
						
						type = st.nextToken();
						switch (type) {
							case "B":
								BusinessAccount busaccount = new BusinessAccount();
								busaccount.setName(name);
								busaccount.setAccountNumber(accountNumber);
								busaccount.setPhoneNumber(phoneNumber);
								busaccount.setSocialSecurityNumber(socialSecurityNumber);
								busaccount.setOpenBalance(openBalance);
								baAl.add(busaccount);
								break;
							case "C":
								CheckingAccount checkaccount = new CheckingAccount();
								checkaccount.setName(name);
								checkaccount.setAccountNumber(accountNumber);
								checkaccount.setPhoneNumber(phoneNumber);
								checkaccount.setSocialSecurityNumber(socialSecurityNumber);
								checkaccount.setOpenBalance(openBalance);
								baAl.add(checkaccount);
								break;
							case "S":
								SavingsAccount savaccount = new SavingsAccount();
								savaccount.setName(name);
								savaccount.setAccountNumber(accountNumber);
								savaccount.setPhoneNumber(phoneNumber);
								savaccount.setSocialSecurityNumber(socialSecurityNumber);
								savaccount.setOpenBalance(openBalance);
								baAl.add(savaccount);
								break;
							default:
								System.out.println("Account type not recognized");
								baAl.add(ba);
								break;
						}
					} catch (NumberFormatException h){
						System.out.println("\nWarning: Not all elements were able to be parsed correctly.\nCheck the delimiters used in input file.\n");
						h.printStackTrace();
						continue;
					}
					
				}
				line = br.readLine();
			}	
			br.close();
		} catch (FileNotFoundException f){
			baAl.clear();
			System.out.println("\n" + inputFileName + " was not found in the current working directory.\n");
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			baAl.clear();
			System.out.println("Error tokenizing the input stream.");
			e.printStackTrace();
		}
		
		System.out.println("\nNumber of ArrayList elements: " + baAl.size());		
	
	}
	
	/**
	 * Checks to see if an output file exists.  Replaces output file with a warning.
	 * 
	 * @return outputFile returns the output File
	 */
	public FileWriter createOutputFile(){
		
		//creates output File
		String outputFileName = "project2_output.txt";
		File outputFile = new File(System.getProperty("user.dir") + File.separator + outputFileName);
		
		//check if file exists and if it does, warn the user
		if (outputFile.exists() && !outputFile.isDirectory()){
			System.out.println("\nWARNING!  You are over-writing an existing file!");
		}
		
		FileWriter outputFileWriter;
		try {
			outputFileWriter = new FileWriter(outputFile, false);
			return outputFileWriter;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//This should not happen because check for directory is already done earlier.
			System.out.println("FileWriter points to a directory, not a file.");
			e.printStackTrace();
		}
		
		return null;
		
	}
}
