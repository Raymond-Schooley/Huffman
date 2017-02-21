/*
 * Raymond Schooley
 * David Dean
 * 02-20-2017
 * TCSS 342 - Project 2
 */

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String TXT_FILE = "WarAndPeace.txt";
    private static final String CODE_FILE = "codes.txt";
    private static final String COMPRESS_FILE = "compressed.txt";

    /** Main Method. */
    public static void main(String[] args) throws IOException {
        long uncompressed = 0;
        long compressed = 0;
        long ratio = 0;
        long time = 0;

        // Read in from a text file.
        String fileToCode = readFile(TXT_FILE);
        CodingTree theCode = new CodingTree(fileToCode);

        uncompressed = getFileSize(TXT_FILE);
        compressed = getFileSize(COMPRESS_FILE);
        ratio = compressed * 100 / uncompressed;
        displayInfo(uncompressed, compressed, ratio, time);
    }

    /** Display the info. */
    private static void displayInfo(long myUncompressed, long myCompressed, long myRatio, long myRunningTime) {
        System.out.println("Uncompressed file size: " + myUncompressed + " bytes");
        System.out.println("Compressed file size: " + myCompressed + " bytes");
        System.out.println("Compression ratio: " + myRatio + "%");
        System.out.println("Running Time: " + myRunningTime + " milliseconds");
    }

    /** Get file size. */
    private static long getFileSize(String myFile) {
        File file = new File(myFile);
        return file.length();
    }

    /** Read the txt file into a string. */
    private static String readFile(String myFile) throws IOException {
        File file = new File(myFile);

        StringBuilder returnFile = new StringBuilder();

        Scanner in = new Scanner(new FileReader(file));
        while (in.hasNextLine()) {
            returnFile.append(System.lineSeparator());
            returnFile.append(in.nextLine());
        }
        in.close();

        return returnFile.toString();
    }

    /** Write the coded file. */
    private static void writeCodedFile() {

    }

    /** Write the compressed file. */
    private static void writeCompressFile() {

    }
}
