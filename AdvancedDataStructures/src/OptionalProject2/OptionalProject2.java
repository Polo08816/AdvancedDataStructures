/**
 * 
 */
package OptionalProject2;

import java.io.*;
import java.util.*;


/**
 * @author Kevin Kuo
 *	
 * To Do:
 * 
 * 1.  Generate a file of 20,000 random numbers.
 * 2.  Read random numbers from file into <file structure>
 * 3.  Analyze system time for the following algorithms:
 * 		a.	Insertion sort
 * 		b.	Quick sort
 * 		c.	Merge sort
 * 		d.  Heap sort
 */
public class OptionalProject2 {

	/**
	 * @param args
	 */
	
	private final static String outputFileName = "OptionalProject2_Input.txt";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			writeRandomIntegers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		int[] baseIntegerArray = new int[20000];
		
		readFromFile(baseIntegerArray);
		
		printIntegerArray(baseIntegerArray);
		
	}
	
	/**
	 * Checks to see if an output file exists.  Replaces output file with a warning.
	 * 
	 * @return outputFile returns the output File
	 */
	private static FileWriter createOutputFile(){
		
		//creates output File
//		String outputFileName = "OptionalProject2_Input.txt";
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
	
	/**
	 * Writes random numbers to a text file delimited by new line characters.
	 * 
	 * @throws IOException
	 */
	private static void writeRandomIntegers() throws IOException{
			
		//create new File
		FileWriter outputFileWriter = createOutputFile();
		if (outputFileWriter == null){
			System.out.println("Error writing to output file");
			return;
		}
		
		BufferedWriter bw = new BufferedWriter(outputFileWriter);
		
		Random randomGenerator = new Random();
		
		for (int i = 0; i < 20000; i++){
			
			bw.write(String.valueOf(randomGenerator.nextInt()));
			bw.newLine();
			bw.flush();
			
		}
		
		bw.close();
		
		System.out.println("\nOutput file complete.");	
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	private static boolean readFromFile(int[] inputArray){
		
		BufferedReader br;
		String inputLine;
		StringTokenizer st;
		
		int count = 0;
		
		//Specifies the file name in the current working directory
		
		try {
			
			//Reads "input.txt"		
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + outputFileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + outputFileName));
			
			while ((inputLine = br.readLine()) != null){
				
				st = new StringTokenizer(inputLine);
				
				while (st.hasMoreTokens()){
					
					int temp = Integer.parseInt(st.nextToken());
					
					if (count == 20000){
						return true;
					}
					
					inputArray[count] = temp;
					
					
					
					count++;
				}
				
			} 
			
			br.close();
			return true;
			
		} catch (NumberFormatException h){
			System.out.println("\nWarning: Not all elements were able to be parsed correctly.\nCheck the delimiters used in input file.\n");
			h.printStackTrace();
			return false;
		} catch (FileNotFoundException f){
			System.out.println("\n" + outputFileName + " was not found in the current working directory.\n");
			f.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error tokenizing the input stream.");
			e.printStackTrace();
			return false;
		}		
	}
	
	private static void printIntegerArray(int[] inputArray){
		
		int count = 0;
		
		while (count < 20000){
			System.out.println(inputArray[count]);
			count++;
		}
	}

}
