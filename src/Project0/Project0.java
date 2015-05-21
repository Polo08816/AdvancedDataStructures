package Project0;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Kevin Kuo
 * 
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