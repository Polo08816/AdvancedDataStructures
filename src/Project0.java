
import java.io.*;

/**
 * 
 * The Project0 class is a simple Java program that serves as an introduction
 * to very basic IO for Java.
 * 
 * @return string simple output from the command line
 * 
 * @author Kevin Kuo
 * @version 1.0
 *
 */
public class Project0 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			
			//initial version
			System.out.println("Hello Towson!\n");
			writer.println("Hello Towson!\n");
			
			//new version addition
			System.out.println("How are you today?");
			writer.println("How are you today?");
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}