import java.io.*;
import java.util.*;


public class Project6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Driver dr = new Driver();
		
		ArrayList<String> statesArray = new ArrayList<String>();
		statesArray = dr.readFileStates(statesArray, "project6_inputState.txt");
		
		dr.printArrayList(statesArray);		
		
		boolean[] visitedArray = new boolean[49];
		Arrays.fill(visitedArray, false);
		
		System.out.println("Size of visitedArray: " + visitedArray.length);

		Graph stateGraph = new Graph();		
		stateGraph.populateGraphArraywithStateArray(statesArray);
		
		dr.readFileAdjacentStates(stateGraph.graphNodeArray, "project6_inputAdjacency.txt");
		
		dr.printGraphArrayList(stateGraph.graphNodeArray);
		
		
		System.out.println("\nDepth First Search:\n");
		
		stateGraph.depthFirstSearch(stateGraph, 1, visitedArray, statesArray);

		
		System.out.println("\nBreadth First Search:\n");
		Arrays.fill(visitedArray, false);
	}

}

class Graph{
	
	ArrayList<GraphNode> graphNodeArray;
	
	public Graph(){
		
		this.graphNodeArray = new ArrayList<GraphNode>();
		this.graphNodeArray.add(new GraphNode(-1));
		
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
		if (it.hasNext()){
			it.next();
		} else {
			return;
		}	
		
		while (it.hasNext()){
//			System.out.println(it.next().toString());
			GraphNode temp = new GraphNode(inputArray.indexOf(it.next()));
			this.graphNodeArray.add(temp);
			
		}
	}
	
	public void depthFirstSearch(Graph graphObject, int startState, boolean[] visitedArray, ArrayList<String> statesArray){
		
		if (!visitedArray[startState]){
			visitedArray[startState] = true; //visited == FALSE
		} else {
			return; //visited == TRUE; base case
		}
		
		System.out.print(graphObject.getGraphNode(startState).toStringStateOnly(statesArray));
		
		Iterator it = graphObject.getGraphNode(startState).getAdjacentStates().iterator();
	
		while(it.hasNext()){
			depthFirstSearch(graphObject, Integer.parseInt(it.next().toString()), visitedArray, statesArray);
		}

	}
	
	public void breadthFirstSearch(Graph graphObject, int startState, boolean[] visitedArray, ArrayList<String> statesArray){
		
		Queue q = new LinkedList<GraphNode>();
		
		q.add(graphObject.getGraphNode(startState));
		
		System.out.println(statesArray.get(startState));
		visitedArray[startState] = true;
		
		while(!q.isEmpty()){
			GraphNode n = (GraphNode)q.remove();
			GraphNode child = null;
			
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
	public int getNodeValue() {
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
	public int getColorValue() {
		return colorValue;
	}

	/**
	 * @param colorValue the colorValue to set
	 */
	public void setColorValue(int colorValue) {
		this.colorValue = colorValue;
	}

	/**
	 * @return the adjacentStates
	 */
	public LinkedList<Integer> getAdjacentStates() {
		return adjacentStates;
	}
	
	/**
	 * @param adjacentStates the adjacentStates to add
	 */
	public void addAdjacentStates(int stateInt) {
		this.adjacentStates.add(stateInt);
	}
	
	public int searchAdjacentState(int stateInt){
		Iterator<Integer> it = this.adjacentStates.iterator();
		return 1;
	}

	public String toString(){		
		
		return "State number: " + this.nodeValue + " \nLinkedList size: " + this.adjacentStates.size() + "\nLinkedList contents: " + this.adjacentStates.toString() + " \n";
		
	}
	
	public String toStringStateOnly(ArrayList<String> statesArray){		
		
		return statesArray.get(this.nodeValue) + " -> ";
		
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
						tempLine = tempLine.trim();
						
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
	
public ArrayList<GraphNode> readFileAdjacentStates(ArrayList<GraphNode> graphNodeArrayList, String fileName){
		
		BufferedReader br;
		String line, tempLine;
		StringTokenizer st;
		
		try {
				
			System.out.println("\nCurrent working directory: " + System.getProperty("user.dir") + File.separator + fileName);
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + fileName));

			line = br.readLine();
			
			int index = 1;
			while (line != null){
				tempLine = "";
				st = new StringTokenizer(line);
				
				while (st.hasMoreTokens()){
					int tempInt = -1;
					try {						
						
						tempInt = Integer.parseInt(st.nextToken());
						
//						System.out.println("State added to LL: " + tempInt);
						
						graphNodeArrayList.get(index).addAdjacentStates(tempInt);
						
					} catch (NumberFormatException h){
//						h.printStackTrace();
					}					
				}
				index++;
				line = br.readLine();
			}	
			br.close();
		} catch (FileNotFoundException f){
			System.out.println("\n" + fileName + " was not found in the current working directory.\n");
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error tokenizing the input stream.");
			e.printStackTrace();
		}
		
		System.out.println("\nGraph ArrayList size: " + graphNodeArrayList.size());
		
		return graphNodeArrayList;		
	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param inputArray ArrayList of String
	 */
	public void printArrayList(ArrayList<String> inputArray){
		
		System.out.println("\nPrinting contents of States ArrayList:\n");
		
		Iterator<String> it = inputArray.iterator();
		while (it.hasNext()){
			System.out.println(it.next().toString());			
		}
	}
	
	
	/**
	 * Prints integer (state) in ArrayList.
	 * 
	 * @param inputArray ArrayList of String
	 */
	public void printGraphArrayList(ArrayList<GraphNode> inputArray){
		
		System.out.println("\nPrinting contents of Graph ArrayList:\n");
		
		Iterator<GraphNode> it = inputArray.iterator();
		while (it.hasNext()){
			System.out.println(it.next().toString());			
		}
	}
	
}
