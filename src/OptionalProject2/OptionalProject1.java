package OptionalProject2;



import java.io.*;
import java.util.*;


/**
 * The OptionalProject1 creates a text file of randomly generated integers and compares the run time
 * of insertion, quick, merge, and heap sort.
 * 
 * @param None.
 * @return Run time of algorithm.
 * 
 * @author Kevin Kuo
 * @version 1.0
 */
public class OptionalProject1 {

	/**
	 * @param args
	 */
	
	private final static String outputFileName = "OptionalProject1_Input.txt";
	
	
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
		
//		printIntegerArray(baseIntegerArray);
		
		int[] insertionSortArray = new int[20000];
		int[] quickSortArray = new int[20000];
		int[] mergeSortArray = new int[20000];
		int[] heapSortArray = new int[20000];
		
		System.arraycopy(baseIntegerArray, 0, insertionSortArray, 0, 19999);
		System.arraycopy(baseIntegerArray, 0, quickSortArray, 0, 19999);
		System.arraycopy(baseIntegerArray, 0, mergeSortArray, 0, 19999);
		System.arraycopy(baseIntegerArray, 0, heapSortArray, 0, 19999);
		
//		printIntegerArray(insertionSortArray);
		
		//Insertion Sort
		insertionSort(insertionSortArray);
		
		
		//Quick Sort
		System.out.println("\nTesting QUICK sort.");
		long beginTime, endTime;
		beginTime = System.currentTimeMillis();
		System.out.println("Begin: " + beginTime);
		
		quickSort(quickSortArray, 0, 19999);
		
		endTime = System.currentTimeMillis();
		System.out.println("End: " + endTime);
		System.out.println("Total: "  + (endTime - beginTime));
		
		
		
		//Merge Sort
		
		System.out.println("\nTesting MERGE sort.");
		beginTime = System.currentTimeMillis();
		System.out.println("Begin: " + beginTime);
		
		mergeSort(mergeSortArray);
		
		endTime = System.currentTimeMillis();
		System.out.println("End: " + endTime);
		System.out.println("Total: "  + (endTime - beginTime));
		
		
		//Heap Sort
		
		System.out.println("\nTesting HEAP sort.");
		beginTime = System.currentTimeMillis();
		System.out.println("Begin: " + beginTime);
		
		heapSort(quickSortArray);
		
		endTime = System.currentTimeMillis();
		System.out.println("End: " + endTime);
		System.out.println("Total: "  + (endTime - beginTime));
		
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
	
	private static void insertionSort(int[] inputArray){
		
		System.out.println("\nTesting INSERTION sort.");
		long beginTime, endTime;
		beginTime = System.currentTimeMillis();
		System.out.println("Begin: " + beginTime);
		
		for (int i = 0; i < inputArray.length; i++){
			int temp1 = inputArray[i];
			int temp2;
			for (temp2 = i - 1; temp2 >= 0 && temp1 < inputArray[temp2]; temp2--){
				inputArray[temp2 + 1] = inputArray[temp2];
			}
			
			inputArray[temp2 + 1] = temp1;
		}
		
		endTime = System.currentTimeMillis();
		System.out.println("End: " + endTime);
		System.out.println("Total: "  + (endTime - beginTime));
	}
	
	private static void quickSort(int[] inputArray, int start, int end){
		
		int i = start;
		int k = end;
		
		if (end - start >= 1){
			
			int pivot = inputArray[start];       

            while (k > i)                   
            {
                    while (inputArray[i] <= pivot && i <= end && k > i)  
                            i++;                                    
                    while (inputArray[k] > pivot && k >= start && k >= i) 
                        k--;                                        
                    if (k > i)                                      
                            swap(inputArray, i, k);                      
            }
            swap(inputArray, start, k);          
                                           
            quickSort(inputArray, start, k - 1); 
            quickSort(inputArray, k + 1, end);                
			
		} else {
			return;
		}
		
	}
	
	private static void swap (int array[], int index1, int index2){
		int temp = array[index1];           // store the first value in a temp
		array[index1] = array[index2];      // copy the value of the second into the first
		array[index2] = temp;
	}
	
	
	private static void mergeSort(int[] inputArray) {
				
		int[] tempArray = new int[inputArray.length];
		
		doMergeSort(inputArray, tempArray, 0, inputArray.length - 1);
		
    }
 
    private static void doMergeSort(int[] inputArray, int[] tempArray, int lowIndex, int highIndex) {
         
    	if (highIndex <= lowIndex){
    		return;
    	} 
    	
		int middle = (highIndex + lowIndex) / 2;
        // Below step sorts the left side of the array
        doMergeSort(inputArray, tempArray, lowIndex, middle);
        // Below step sorts the right side of the array
        doMergeSort(inputArray, tempArray, middle + 1, highIndex);
        // Now merge both sides
        mergeParts(inputArray, tempArray, lowIndex, middle, highIndex);

    }

    private static void mergeParts(int[] inputArray, int[] tempArray, int lowerIndex, int middle, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
        	tempArray[i] = inputArray[i];
        }
        
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        
        while (i <= middle && j <= higherIndex) {
            if (tempArray[i] <= tempArray[j]) {
            	inputArray[k] = tempArray[i];
                i++;
            } else {
            	inputArray[k] = tempArray[j];
                j++;
            }
            k++;
        }
        
        while (i <= middle) {
        	inputArray[k] = tempArray[i];
            k++;
            i++;
        }
        
    }
    
    private static void heapSort(int[] inputArray){
    	
    	int heapSize = inputArray.length - 1;
    	
    	for (int i = (heapSize/2); i >= 0; i--){
    		doHeapSort(inputArray, i, heapSize);
    	}
    	
    	for (int j = (heapSize); j > 0; j--){
    		swap(inputArray, 0, j);
    		heapSize--;
    		doHeapSort(inputArray, 0, heapSize);
    	}
    	
    }
    
    private static void doHeapSort(int[] heapArray, int heapIndex, int heapSize){
    	
    	int leftIndex = heapIndex * 2;
    	int rightIndex = (leftIndex + 1);
    	int highestIndex = heapIndex;
    	
    	if ((leftIndex <= heapSize) && (heapArray[leftIndex] > heapArray[heapIndex])){
    		
    		highestIndex = leftIndex;
    		
		}
    	
		if ((rightIndex <= heapSize) && (heapArray[rightIndex] > heapArray[highestIndex])){
			
			highestIndex = rightIndex;
			
		}
		
		if (highestIndex != heapIndex){
			
			swap(heapArray, heapIndex, highestIndex);
			doHeapSort(heapArray, highestIndex, heapSize);
			
		}
		
    }

	

}
