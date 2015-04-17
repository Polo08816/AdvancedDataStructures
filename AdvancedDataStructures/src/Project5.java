import java.io.*;
import java.util.*;

/**
 * @author Kevin Kuo
 * 
 * 
 * To Do List:
 * 
 * 1.  add
 * 2.  search
 * 6.  count leaf nodes
 * 5.  count nodes
 * 9.  copy BSTree
 * 3.  print in order
 * 
 * 4.  print post order
 * 7.  delete
 * 8.  swap
 *
 */


public class Project5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree T1 = new BinarySearchTree();
		T1.runBTO();

	}

}

class BinarySearchTree{
	
	BinarySearchTreeNode root;
	ArrayList<Integer> inputIntegers;
	
	/**
	 * Constructor for BinarySearchTree that sets the root BST node to null.
	 */
	public BinarySearchTree(){
		this.root = null;
	}
	
	
	/**
	 * Runs BinarySearchTree operations.
	 */
	public void runBTO(){
		
		inputIntegers = new ArrayList<Integer>();
		
		readInput(inputIntegers);
		
//		printArrayList(inputIntegers);	
		
//		System.out.println("Number of leaf nodes: " +root.leafCount(root));
		
//		System.out.println("Number of nodes: " +root.countNodes(root));
		
		
		
	}
	
	/**
	 * Creates root if root does not exist for the Binary Search Tree.
	 * 
	 * @param value Integer
	 * @return true 
	 */
	public boolean add(int value){
		if (root == null){			
			root = new BinarySearchTreeNode(null, value, null);
			return true;
		} else {
			return root.add(value);
		}
	}
	
	/**
	 * Reads a string of integers, tokenizes the string for integers, and places those integers into an ArrayList for future access.
	 * 
	 * @param al ArrayList<Integers
	 */
	private void readInput(ArrayList<Integer> al){
		
		String inputIntegerString;
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.println("Enter a list of integers separated by a comma: \n");
		
		//tokenize input string
		try {
			inputIntegerString = br.readLine();
			
//			System.out.println(inputIntegerString);
			
			st = new StringTokenizer(inputIntegerString, " ,");
			
			//adds valid tokens to ArrayList
			while (st.hasMoreTokens()){
				int temp = Integer.parseInt(st.nextToken());
				al.add(temp);
				// calls the add method in the BinarySearchTree which has an add method in the BinarySearchTreeNode
				addOperation(temp);
			}
			
			System.out.println("ArrayList<Integer> inputIntegersArray size :" + al.size() + "\n");
		} catch (NumberFormatException h){
			al.clear();
			System.out.println("\nWarning: Not all elements were able to be parsed correctly.\nCheck the delimiters used in input file.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error tokenizing the input stream.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Adds a value to a BinarySearchTree first by checking if the root node of the tree exists.
	 * 
	 * @param tree
	 * @param insertValue
	 * @return
	 */
	private boolean addOperation(int insertValue){
		
		//check if tree exist
		if (root == null){
//			System.out.println("\nTree does not yet exist.\n");
			root = new BinarySearchTreeNode(null, insertValue, null);
		} else {
			//calls the add method from BinarySearchTreeNode - NOT BinarySearchTree
			return root.add(insertValue);
		}
		
		return true;
	}
	
	/**
	 * Prints values in ArrayList.
	 * 
	 * @param intAl ArrayList of Integers
	 */
	private static void printArrayList(ArrayList<Integer> al){
		
		System.out.println("\nPrinting contents of ArrayList:\n");
		
		Iterator<Integer> it = al.iterator();
		while (it.hasNext()){
			int temp = it.next();
			String seperator = "\t";
			System.out.println(temp + seperator);
		}
	}
	
	
	
}

class BinarySearchTreeNode{
	
	BinarySearchTreeNode left;
	BinarySearchTreeNode right;
	int value;
	
	/**
	 * Constructor for BinarySearchTreeNode object.
	 * 
	 * @param left Left child.
	 * @param item Value of child.
	 * @param right Right child.
	 */
	public BinarySearchTreeNode(BinarySearchTreeNode left, int value, BinarySearchTreeNode right){
		
		this.left = left;
		this.right = right;
		this.value = value;
		
		//do you need to know parent? is this useful?
		
	}
	
	/**
	 * Adds a value to a BinarySearchTree by determining where to place the value.
	 * 
	 * @param value
	 * @return
	 */
	public boolean add(int value){
		
		//check if duplicate
		if (value == this.value){
			//duplicate is found - no child added because this is a BinarySearchTree
			return false;
		//if value is LESS than current BSTNode, then look at left child
		} else if (value < this.value){
			//no left child
			if (left == null){
				left = new BinarySearchTreeNode(null, value, null);
				//no parent
				return true;
			//left child exists - run recursion
			} else {
				return left.add(value);
			}
		//if value is GREATER than current BSTNode, then look at right child
		} else if (value > this.value){
			//no right child
			if (right == null){
				right = new BinarySearchTreeNode(null, value, null);
			//right child exists - run recursion
			} else {
				return right.add(value);
			}
		}
		//no child has been added
		return false;	
	}
	
	/**
	 * Searches to determine whether a value exists in the current BinarySearchTree.
	 * 
	 * @param value
	 * @return boolean True if value found.  False if value has not been found.
	 */
	public boolean searchValue(int value){
		//if value is found in root node
		if (value == this.value){
			return true;
		//if value is less than root node go to search left child
		} else if (value < this.value){
			//check if left child exists
			if (left == null){
				return false;
			} else {
				//left child exists - do recursion
				return left.searchValue(value);
			}
		// if value is greater than root node go to search right child
		} else if (value > this.value){
			//check if right child exists
			if (right == null){
				return false;
			//right child exists - do recursion
			} else {
				return right.searchValue(value);
			}
		}
		return false;
	}
	
	/**
	 * Counts the number of leaf nodes in the BinarySearchTree.
	 * 
	 * @param root Root BinarySearchTreeNode
	 * @return count of leaf BinarySearchTreeNodes
	 */
	public int leafCount(BinarySearchTreeNode root){
		//if root node does not exist
		if (root == null){
			return 0;
		//if root has at least one child
		} else if ((root.left != null) ||(root.right != null)){
			return (leafCount(root.left) + leafCount(root.right));
		//root node has no children so root node is considered a leaf node
		} else {
			return 1;
		}

	}
	
	/**
	 * Counts the total number of BinarySearchTreeNodes within a Binary Search Tree
	 * 
	 * @param root BinarySearchTreeNode
	 * @return int of BinarySearchTreeNodes
	 */
	public int countNodes(BinarySearchTreeNode root){
		//if root node does not exist
		if (root == null){
			return 0;
		//if root has at least one child
		} else if ((root.left != null) ||(root.right != null)){
			return (1 + countNodes(root.left) + countNodes(root.right));
		//root node has no children so root node is considered a leaf node
		} else {
			return 1;
		}
	}
	
	/**
	 * Performs a deep copy from the specified BinarySearchTreeNode.
	 * 
	 * @return
	 */
	private BinarySearchTreeNode copyBinarySearchTreeNode(BinarySearchTreeNode root){
		
		BinarySearchTreeNode left = null;
		BinarySearchTreeNode right = null;
		
		if (root.left != null){
			left = copyBinarySearchTreeNode(root.left);
		}
		
		if (root.right != null){
			right = copyBinarySearchTreeNode(root.right);
		}
		
		return new BinarySearchTreeNode(left, value, right);
	}
	
	/**
	 * Performs a In Order traversal and prints the nodes.
	 * 
	 * @param seed
	 */
	public void inOrderTraversal(BinarySearchTreeNode seed){
		
		if (seed.left != null ){
			inOrderTraversal(seed.left);
		}
		
		System.out.print("In-order traversal: " + seed.value + ", ");
		
		if (seed.right != null){
			inOrderTraversal(seed.right);
		}			
	}
	
	/**
	 * Performs a Post Order traversal and prints the nodes.
	 * 
	 * @param seed
	 */
	public void postOrderTraversal(BinarySearchTreeNode seed){
		
		if (seed.left != null ){
			inOrderTraversal(seed.left);
		}
		
		if (seed.right != null){
			inOrderTraversal(seed.right);
		}	
		
		System.out.print("Post-order traversal: " + seed.value + ", ");
		
	}
	
}