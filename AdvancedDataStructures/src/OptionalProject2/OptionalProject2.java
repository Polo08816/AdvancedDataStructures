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
 * 2.  Analyze system time for the following algorithms:
 * 		a.	Insertion sort
 * 		b.	Quick sort
 * 		c.	Merge sort
 * 		d.  Heap sort
 */
public class OptionalProject2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			writeRandomIntegers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * Checks to see if an output file exists.  Replaces output file with a warning.
	 * 
	 * @return outputFile returns the output File
	 */
	private static FileWriter createOutputFile(){
		
		//creates output File
		String outputFileName = "OptionalProject2_Input.txt";
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
		
		for (int i = 0; i < 20001; i++){
			
			bw.write(String.valueOf(randomGenerator.nextInt()));
			bw.newLine();
			bw.flush();
			
		}
		
		bw.close();
		
		System.out.println("\nOutput file complete.");	
		
	}
	

}
