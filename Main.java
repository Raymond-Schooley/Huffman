import java.io.IOException;


/*
 * Project 2 - Main.
 * Leah Ruisenor, Lorenzo Pacis, Alex Merk.
 */
/**
 * Main driver for CodingTree.
 * @author Leah Ruisenor, Lorenzo Pacis, Alex Merk.
 * @version February 16, 2017.
 */
public class Main {
	
	private static final int BYTE_SIZE = 8;
	private static final String FILE_NAME = "src/Input/WarAndPeace.txt";
	private static final String CODE_OUTPUT_FILE_NAME = "src/Output/codes.txt";
	private static final String COMP_FILE_NAME = "src/Output/compressed.txt";


	public static void main(final String[] args) throws IOException {
		double myTimer = System.currentTimeMillis();
	
		
		//the message to be encoded from the string builder.
		String messageToCode = new ReadFile(FILE_NAME).readFile();
		//constructs the coding tree with the message from the file.
		CodingTree myHuffmanTree = new CodingTree(messageToCode);
		
		CodeFileOutput codeFile = new CodeFileOutput(CODE_OUTPUT_FILE_NAME, myHuffmanTree);
		codeFile.writeCodeOutput();
		CompressedFileOutput compress = new CompressedFileOutput(COMP_FILE_NAME, messageToCode, myHuffmanTree);
		compress.writeCompressedFile();
		
		//the cost of the uncompressed message
		double unCompressedCost = messageToCode.length()*BYTE_SIZE;
		

		StringBuilder displayInfo = new StringBuilder();
		unCompressedCost = unCompressedCost/BYTE_SIZE;
		double compressionCost = compress.getCompressionCost()/BYTE_SIZE;
		displayInfo.append("Size of uncompressed file in bytes: " + unCompressedCost +"\n");
		displayInfo.append("Size of compressed file in bytes: " + compressionCost + "\n");
		displayInfo.append("File Compression Ratio: " + compressionCost * 100 / unCompressedCost + "%\n");
		displayInfo.append("Total running time in miliseconds: " + (System.currentTimeMillis() - myTimer));
		System.out.println(displayInfo.toString());
		
	}
}