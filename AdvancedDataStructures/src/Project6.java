import java.io.*;
import java.util.*;


public class Project6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver dr = new Driver();
		
		ArrayList<String> statesArray = new ArrayList<String>();
		statesArray = dr.readFileStates(statesArray, "project6_inputState.txt");
		
//		dr.printArrayList(statesArray);
		
		

	}
	
	

}


class Driver{
	
	public ArrayList<String> readFileStates(ArrayList<String> statesArray, String fileName){
		
		BufferedReader br;
		String line, tempLine;
		StringTokenizer st;
		
		statesArray.add("");
		
		try {
				
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + fileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + fileName));

			line = br.readLine();
			
			int count = 0;
			
			
			while (line != null && count < 48){
				tempLine = "";
				st = new StringTokenizer(line);
				while (st.hasMoreTokens()){
					try {
						tempLine = tempLine + " " + st.nextToken();
						
					} catch (NumberFormatException h){
//						h.printStackTrace();
					}					
				}
				statesArray.add(tempLine);
				count++;
				line = br.readLine();
			}	
			br.close();
		} catch (FileNotFoundException f){
			statesArray.clear();
			System.out.println("\n" + fileName + " was not found in the current working directory.\n");
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			statesArray.clear();
			System.out.println("Error tokenizing the input stream.");
			e.printStackTrace();
		}
		
		System.out.println("\nNumber of ArrayList elements: " + statesArray.size());
		
		return statesArray;		
	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param inputArray ArrayList of String
	 */
	public void printArrayList(ArrayList<String> inputArray){
		
		System.out.println("\nPrinting contents of ArrayList:\n");
		
		Iterator<String> it = inputArray.iterator();
		while (it.hasNext()){
			System.out.println(it.next().toString());			
		}
	}
	
}

class GraphNode{
	
	
	
}
