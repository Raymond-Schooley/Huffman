
/*
 * Raymond Schooley
 * David Dean
 * 02-20-2017
 * TCSS 342 - Project 2
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CodingTree {

    /** a public data member that is a map of characters in the
     message to binary codes (Strings of ‘1’s and ‘0’s) created by your tree. */
    public Map<Character, String> myCodes;

    /** String - a public data member that is the message 
     * encoded using the Huffman codes. */
    public String mySmallString;

    private String myBigString;
    
    private String myPath;

    private PriorityQueue<CharacterNode> myQueue;
    
    private CharacterNode myRoot;

    /**
     * Constructor that takes the text of a message to be compressed.
     * The constructor is responsible for calling all private methods that carry 
     * out the Huffman coding algorithm.
     */
    public CodingTree(String theFile) {
    	myCodes = new HashMap<>();
        myBigString = theFile;
        mySmallString = "";
        myPath = "";
        ArrayList<CharacterNode> charList = createArray();
        myQueue = buildQueue(charList);
//        while(myQueue.peek() != null) {
//        	System.out.println(myQueue.poll().myData);
//        }
      //Use the PriorityQueue to build a huffman tree
        myRoot =  buildTree(myQueue);
        //Use the huffman tree to create a coding scheme
        buildCode(myRoot);
        
        for (CharacterNode node: charList) {
        	System.out.print("Char = " + node.myData + " ");
        	System.out.println("Code = " + myCodes.get(node.myData));
        }
    }
    
    /**
     * Now use the huffman tree to build a code cipher.  We will also use the 
     * ArrayList because it has all the characters that we need.
     * @param theArr ArrayList to use to iterate threw each character and build
     * the corresponding code.
     */
    private void buildCode(CharacterNode theRoot) {
        if (theRoot.myLeft == null && theRoot.myRight == null) {
        	myCodes.put(theRoot.myData, myPath);
        	myPath = myPath.substring(0, myPath.length()-1);
        	return;
        }
        myPath += 0;
        buildCode(theRoot.myLeft);
        myPath += 1;
        buildCode(theRoot.myRight);
        if (myPath.length() > 0)
        	myPath = myPath.substring(0, myPath.length() - 1);
        return;
        
    }
    
    /**
     * Add all the node representing character and frequency of a file to a 
     * priority queue.
     * @param theArr ArrayList of CharacterNodes.
     * @return PriortityQueue where least occurring character is at front.
     */
    private PriorityQueue buildQueue(ArrayList<CharacterNode> theArr) {
    	PriorityQueue<CharacterNode> temp = new PriorityQueue<>();
    	for (CharacterNode node: theArr) {
    		temp.add(node);
    	}
    	
    	return temp;
    }
    
    /**
     * Building the tree by extracting the two least frequent nodes, bind them
     * with a placeholder node and put it back into the queue. After this method
     * is run the PriorityQueue should be empty.
     * @param theQueue PriorityQueue used get least occurring node.
     * @return Root to the new Huffman tree.  
     */
    CharacterNode buildTree(PriorityQueue<CharacterNode> theQueue) {
    	while(theQueue.size() != 1) {
    		CharacterNode left = theQueue.poll();
    		CharacterNode right = theQueue.poll();
    		CharacterNode newRoot = new CharacterNode((char) 0, left.myCount 
    				+ right.myCount, left, right);
    		theQueue.add(newRoot);
    	}
//    	System.out.println("Good stopping point");
        return theQueue.poll();
    }
    
    private ArrayList<CharacterNode> createArray() {
    	ArrayList<CharacterNode> result = new ArrayList<>();
    	String temp = myBigString;
    	result.add(new CharacterNode(temp.charAt(0)));
    	
    	while (!temp.isEmpty()) {
    		//if Node exists increment the count
    	    boolean hasFound = findElement(result, temp.charAt(0));
    	    if (!hasFound) {
    	    	//If node doesn't exist then create it
    	    	result.add(new CharacterNode(temp.charAt(0)));
    	    }
    	    temp = temp.substring(1);
    	}
    	
    	return result;
    }
    
    private boolean findElement(ArrayList<CharacterNode> theArr, char c) {
    	boolean hasFound = false;
    	//iterate through the list if we find a node already representing
    	//this character then just increment the count and flip flag.
    	for (int i = 0; i < theArr.size() && !hasFound; i++) {
    		if (theArr.get(i).myData == c) {
    			theArr.get(i).myCount++;
    			hasFound = true;
    		}
    	}
    	
    	return hasFound;
    }
    
    /**
     * The CharacterNodes will be used when parsing the text to keep track or 
     * the frequency of each character.  Then the nodes will be stored in the
     * priority queue. Finally they will be extracted to build the huffman tree.
     * @author Raymond Schooley and David Dean
     * @version 2/22/2017
     */
    private class CharacterNode implements Comparable<CharacterNode> {
    	/**Actually character this nodes represents.*/
        char myData;
        /**Frequency of this character for collection of characters.*/
    	int myCount;
    	/**Reference to a left subtree.*/
    	CharacterNode myLeft;
    	/**Reference to a right subtree.*/
    	CharacterNode myRight;
    	
    	/**
    	 * Contructs a CharacterNode object.
    	 * @param theData character for this node to represent.
    	 */
    	 CharacterNode(char theData) {
    		myData = theData;
    		myCount = 1;
    		myLeft = null;
    		myRight = null;
    	}
    	 
    	 /**
     	 * Contructs a CharacterNode object.
     	 * @param theData character for this node to represent.
     	 */
     	 CharacterNode(char theData, int theCount, CharacterNode theLeft
     			 , CharacterNode theRight) {
     		myData = theData;
     		myCount = theCount;
     		myLeft = theLeft;
     		myRight = theRight;
     	}
     	 
    /**
     * Allows the character nodes to be compared according to their frequency.
     * @param theOther Other CharacterNode to be compared.
     * @return Integer indication the parameters value relative the implicit 
     * parameter.
     */
      public int compareTo(CharacterNode theOther) {
    	  int result = 0;
    	  if(this.myCount < theOther.myCount) 
    		  result = -1;
    	  else if (this.myCount > theOther.myCount)
    		  result = 1;
    	  
    	  return result;
      }
    }
}
