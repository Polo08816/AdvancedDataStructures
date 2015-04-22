package Project5;
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
 * 4.  print post order
 * 7.  delete
 * 8.  swap
 * 
 *
 */


public class Project5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//read integers and construct tree T1
		BinarySearchTree T1 = new BinarySearchTree();
		T1.runBTO();
		
		//print T1 INORDER
		
		System.out.print("T1 In-order traversal: ");
		T1.root.inOrderTraversal(T1.root);
//		System.out.print("\nT1 Post-order traversal: ");
//		T1.root.postOrderTraversal(T1.root);
		
		//count number of LEAF nodes on T1
		
		System.out.print("\nT1 leaf count: ");
		System.out.print(T1.root.leafCount(T1.root) + "\n");
		
		//SWAP nodes on T1 and create T2
		BinarySearchTree T2 = new BinarySearchTree();
		T2.root = T1.root.copyBinarySearchTreeNode(T1.root);
		
		
		T2.root.swapChildren(T2.root);
		
		//print POST order of T2
		
//		System.out.print("\n\nT2 In-order traversal: ");
//		T2.root.inOrderTraversal(T2.root);
		System.out.print("\nT2 Post-order traversal: ");
		T2.root.postOrderTraversal(T2.root);
		
		//compare if T1 is equal to T2
		
		System.out.print("\n\nCompare T1 to T2: " + T1.compareOperation(T1.root, T2.root));
		
		//read another sequences of integers for T3
		
		System.out.println("\n\nFor T3...");
		BinarySearchTree T3 = new BinarySearchTree();
		T3.runBTO();
		
		//compare if T1 is equal to T3
		
		System.out.print("\n\nCompare T1 to T3: " + T1.compareOperation(T1.root, T3.root));
		
		//delete node 15 from T3
		
		System.out.print("\nDelete 15 from T3... ");
		T3.removeOperation(15);
		
		System.out.print("Deleting '15' done. ");
		
		//print IN ORDER after deletion
		
		System.out.print("\nT3 In-order traversal: ");
		T1.root.inOrderTraversal(T3.root);
		
		//display 5th node of T3 IN ORDED

		String stString = "";
		stString = T3.root.inOrderTraversalDisplaySpecificNode(T3.root, stString);
		
		StringTokenizer st = new StringTokenizer(stString, ",");
		
		int count = 1;
		
		while (st.hasMoreElements()){
			String temp = st.nextToken();
			if (count == 5){
				System.out.println("\n5th node of T3: " + temp);
				break;
			}
			count++;
		}
		
		System.out.print("\n\nDone. ");
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
		
		
		System.out.println("\n\nEnter a list of integers separated by a comma: \n");
		
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
			
//			System.out.println("ArrayList<Integer> inputIntegersArray size :" + al.size() + "\n");
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
	
	public boolean removeOperation(int value){
		
		if (root == null){
			return false;
		} else {
			if (root.value == value){
				BinarySearchTreeNode newRoot = new BinarySearchTreeNode(null, 0, null);
				newRoot.left = root;
				boolean result = root.removeBinarySearhTreeNode(value, newRoot);
				root = newRoot.left;
				return result;
			} else {
				return root.removeBinarySearhTreeNode(value, null);
			}
		}		
		
	}
	
	public boolean swapOperation(BinarySearchTreeNode root){
		
		return false;
	}
	
	
	/**
	 * 
	 * http://stackoverflow.com/questions/9597188/the-most-efficient-way-to-test-two-binary-trees-for-equality
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public boolean compareOperation(BinarySearchTreeNode root1, BinarySearchTreeNode root2){
		
		if (root1 == root2){
			return true;
		}
		
		if (root1 == null || root2 == null){
			return false;
		}
		

		
		return ( (root1.value == root2.value) &&				
				compareOperation(root1.left, root2.left) &&
				compareOperation(root1.right, root2.right));
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
	public BinarySearchTreeNode copyBinarySearchTreeNode(BinarySearchTreeNode root){
		
		BinarySearchTreeNode left = null;
		BinarySearchTreeNode right = null;
		
		if (root.left != null){
			left = copyBinarySearchTreeNode(root.left);
		}
		
		if (root.right != null){
			right = copyBinarySearchTreeNode(root.right);
		}
		
		return new BinarySearchTreeNode(left, root.value, right);
	}
	
	/**
	 * Performs a In Order traversal and prints the nodes.
	 * 
	 * @param seed
	 */
	public static void inOrderTraversal(BinarySearchTreeNode seed){
		
		if (seed.left != null ){
			inOrderTraversal(seed.left);
		}
		
		System.out.print(seed.value + ", ");
		
		if (seed.right != null){
			inOrderTraversal(seed.right);
		}			
	}
	
	public static String inOrderTraversalDisplaySpecificNode(BinarySearchTreeNode seed, String temp){
		
		if (seed.left != null ){
			temp = inOrderTraversalDisplaySpecificNode(seed.left, temp);
		}
		
		temp = temp + String.valueOf(seed.value) + ",";
		
		if (seed.right != null){
			temp = inOrderTraversalDisplaySpecificNode(seed.right, temp);
		}
		
		return temp;
	}
	
	/**
	 * Performs a Post Order traversal and prints the nodes.
	 * 
	 * @param seed
	 */
	public void postOrderTraversal(BinarySearchTreeNode seed){
		
		if (seed.left != null ){
			postOrderTraversal(seed.left);
		}
		
		if (seed.right != null){
			postOrderTraversal(seed.right);
		}	
		
		System.out.print(+ seed.value + ", ");
		
	}
	
	/**
	 * Removes a BinarySearchTreeNode with the specified value.
	 * 
	 * Note: A value only appears once in a BinarySearchTree so there should be no duplicates.
	 * 
	 * @param value
	 * @param parent
	 * @return
	 */
	public boolean removeBinarySearhTreeNode(int value, BinarySearchTreeNode parent){
		
		if (value < this.value){
			if (left != null){
				return left.removeBinarySearhTreeNode(value, this);
			} else {
				return false;
			}
			
		} else if (value > this.value){
			if (right != null){
				return right.removeBinarySearhTreeNode(value,  this);
			} else {
				return false;
			}
		} else {
			if (left != null && right != null){
				this.value = right.minValue();
				right.removeBinarySearhTreeNode(this.value, this);
			} else if (parent.left == this){
				parent.left = (left != null) ? left : right;
			} else if (parent.right == this){
				parent.right = (left != null ? left : right);
			}
			return true;
		}
		
	}
	
	public int minValue(){
		
		if (left == null){
			return value;
		} else {
			return left.minValue();
		}
	}
	
	public boolean swapChildren(BinarySearchTreeNode swapNode){
		
		if (swapNode != null){
			
			if (swapNode.left != null){
				swapChildren(swapNode.left);
			}
			
			if (swapNode.right != null){
				swapChildren(swapNode.right);
			}
			
			BinarySearchTreeNode temp;
			temp = swapNode.left;
			swapNode.left = swapNode.right;
			swapNode.right = temp;
			
			return true;
			
		}
		
		return false;
		
	}
	
}