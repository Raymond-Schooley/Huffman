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

    /** String or List<Byte> bits - a public data member that is the message encoded using the
     Huffman codes. */
    public List<Byte> myBits;


    private PriorityQueue<?> myQueue;

    /**
     * Constructor that takes the text of a message to be compressed.
     * The constructor is responsible for calling all private methods that carry out
     * the Huffman coding algorithm.
     */
    public CodingTree(String theFile) {
        myCodes = new HashMap<>();
        myBits = new ArrayList<>();
        myQueue = new PriorityQueue<>();

        //System.out.println(theFile);
    }
}
