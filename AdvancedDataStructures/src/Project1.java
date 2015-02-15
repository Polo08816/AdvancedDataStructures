import java.io.*;
import java.util.*;

/**
 * @author Kevin Kuo
 *
 */
public class Project1 {
	
	/**
	 * Main class.
	 * 
	 * @param args
	 */	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> intAl = new ArrayList<Integer>();
		
		Driver driv = new Driver();
		
		Driver.readFile(intAl);
		
		printArrayList(intAl);
		

	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param args
	 */
	private static void printArrayList(ArrayList<Integer> intAl){
		
		System.out.println("\nPrinting contents of ArrayList:\n");
		
		Iterator<Integer> it = intAl.iterator();
		while (it.hasNext()){
			System.out.println(it.next().toString());			
		}
	}

}


class Driver {
	
	/**
	 * Reads data from text file and stores into an ArrayList.
	 * 
	 * <p>
	 * 
	 * This function expects to read "input.txt" from the current working directory.  Error handling
	 * for this function includes FileNotFound and general IOExcetions.
	 * 
	 * @param intAl an ArrayList<Integer> of scores
	 */
	public static void readFile(ArrayList<Integer> intAl){
	
		BufferedReader br;
		String line, fileName;
		StringTokenizer st;
		
		int sum = 0;
		fileName = "input.txt";
		
		try {
			
			//Reads "input.txt"		
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + fileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + fileName));

			line = br.readLine();

			while (line != null){
				st = new StringTokenizer(line);
				while (st.hasMoreTokens()){
					int tempInt = Integer.parseInt(st.nextToken());
					intAl.add(tempInt);
					sum = sum + tempInt;
				}
				line = br.readLine();
			}		
		} catch (FileNotFoundException f){
			System.out.println("\n" + fileName + " was not found in the current working directory.\n");
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error tokenizing the input stream");
			e.printStackTrace();
		}
		
		System.out.println("\nNumber of ArrayList elements: " + intAl.size());		
		System.out.println("\nSum: " + sum + "\n");		
	}
	
}

class AssignScore {	
	
	/**
	 * Assigns scores (Outstanding, Satisfactory, and Unsatisfactory) to all individual scores based on average.
	 * 
	 * @param intAl ArrayList<Integer> of scores
	 * @param sum sum of all scores
	 */
	void assignScore(ArrayList<Integer> intAl, int sum){
		
		
		
	}
	
	
	
}