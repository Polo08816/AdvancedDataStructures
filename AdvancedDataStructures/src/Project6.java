import java.io.*;
import java.util.*;


public class Project6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver dr = new Driver();
		
		ArrayList<String> statesArray = new ArrayList<String>();
		statesArray = dr.readFileStates(statesArray, "project6_inputState.txt");
		
//		dr.printArrayList(statesArray);		
		
		Graph stateGraph = new Graph();		
		stateGraph.populateGraphArraywithStateArray(statesArray);
		
		

	}

}

class Graph{
	
	ArrayList<GraphNode> graphNodeArray;
	
	public Graph(){
		
		this.graphNodeArray = new ArrayList<GraphNode>();
		
	}
	
	public void addGraphNode(GraphNode graphNode){
		
		this.graphNodeArray.add(graphNode);
		
	}
	
	public GraphNode getGraphNode(int index){
		
		if (graphNodeArray.size() < index){
			return null;
		}
		
		try{
			return graphNodeArray.get(index);
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Populate graphNodeArray with Strings from inputArray.
	 * 
	 * @param inputArray ArrayList of String
	 */
	public void populateGraphArraywithStateArray(ArrayList<String> inputArray){
				
		Iterator<String> it = inputArray.iterator();
		
		while (it.hasNext()){
//			System.out.println(it.next().toString());
			GraphNode temp = new GraphNode(inputArray.indexOf(it.next()));
			this.graphNodeArray.add(temp);
			
		}
	}
	
	
	
}


class GraphNode{
	
	private int nodeValue;
	private LinkedList<Integer> adjacentStates;
	private int colorValue;
	
	public GraphNode(int nodeValue){
		this.nodeValue = nodeValue;
		this.adjacentStates = new LinkedList<Integer>();
	}
	
	/**
	 * @return the nodeValue
	 */
	private int getNodeValue() {
		return nodeValue;
	}

	/**
	 * @param nodeValue the nodeValue to set
	 */
	private void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}

	/**
	 * @return the colorValue
	 */
	private int getColorValue() {
		return colorValue;
	}

	/**
	 * @param colorValue the colorValue to set
	 */
	private void setColorValue(int colorValue) {
		this.colorValue = colorValue;
	}

	/**
	 * @return the adjacentStates
	 */
	private LinkedList<Integer> getAdjacentStates() {
		return adjacentStates;
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
