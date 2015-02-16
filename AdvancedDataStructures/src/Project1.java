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
	 * @param args None.
	 */	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//initializes ArrayList<Integer>
		ArrayList<Integer> intAl = new ArrayList<Integer>();
		
		//Constructs Driver object to handle File reading
		Driver driv = new Driver();
		
		
		int sum = driv.readFile(intAl);
		
		//Debugging purposes
		if (intAl.size() > 0){
			printArrayList(intAl);
		} else {
			System.out.println("Size of intAl ArrayList<Integer> is " + intAl.size() + "\n");
		}
		
		//Assign Score
		
		AssignScore as = new AssignScore();
		
		try {
			as.assignScore(intAl, sum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Exit main gracefully
		intAl.clear();		

	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param intAl ArrayList of Integers
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
	 * Reads data from text file and stores data an ArrayList.
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
		inputFileName = "input.txt";
		
		try {
			
			//Reads "input.txt"		
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + inputFileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + inputFileName));

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

class AssignScore {	
	
	/**
	 * Assigns scores (Outstanding, Satisfactory, and Unsatisfactory) to all individual scores based on average.
	 * 
	 * @param intAl ArrayList of Integers of scores
	 * @param sum sum of all scores
	 * @throws IOException Error with BufferedWriter
	 */
	public void assignScore(ArrayList<Integer> intAl, int sum) throws IOException{
		
		//create new File
		FileWriter outputFileWriter = createOutputFile();
		
		//calculate average
		double average = (double)sum/(intAl.size());
		System.out.println("\nAverage is: " + average + "\n");
		
		//range defintions of scores.
		double outstanding = average * 1.10;
		double unsatisfactory = average * .90;	
		
		BufferedWriter bw = new BufferedWriter(outputFileWriter);
		
		for (int i: intAl){
			//compare score;
			
			if (i > outstanding){
				String outstandingString = "Score: " + i + "\t\tGrade: Outstanding";
				System.out.println(outstandingString);
				bw.write(outstandingString);
				bw.newLine();
			} else if (i < unsatisfactory){
				String unsatisfactoryString = "Score: " + i + "\t\tGrade: Unsatisfatory";
				System.out.println(unsatisfactoryString);
				bw.write(unsatisfactoryString);
				bw.newLine();
			} else {
				String satisfactoryString = "Score: " + i + "\t\tGrade: Satisfactory";
				System.out.println(satisfactoryString);
				bw.write(satisfactoryString);
				bw.newLine();
			}
						
		}
		
		bw.close();
		
		System.out.println("\nOutput file complete.");
		
		
		
	}
	
	
	/**
	 * Checks to see if an output file exists.  Replaces output file with a warning.
	 * 
	 * @return outputFile returns the output File
	 */
	private FileWriter createOutputFile(){
		
		//creates output File
		String outputFileName = "output.txt";
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