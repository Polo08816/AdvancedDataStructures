import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;



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
