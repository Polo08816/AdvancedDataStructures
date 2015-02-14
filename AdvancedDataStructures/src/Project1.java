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

/**
 * Reads data from text file and stores into an ArrayList.
 * 
 * @param intAl an ArrayList<Integer> of scores
 */
class Driver {
	
	public static void readFile(List<Integer> intAl){
	
		BufferedReader br;
		String line;
		StringTokenizer st;
		
		int sum = 0;
		
		try {
			
			 System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + "input.txt");
			 br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "input.txt"));

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nNumber of ArrayList elements: " + intAl.size());
		
		System.out.println("\nSum: " + sum);
	
		
	}
	
	
}

/**
 * @param args
 */
class AssignScore {
	
	public double calculateAverage(List<Integer> intAl, int sum){
		
		return sum/(intAl.size());
		
	}
	
	void assignScore(){
		
	}
	
	
	
}