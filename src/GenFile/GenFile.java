package GenFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Generates randomly filled files of a specified name and size.
 * @author Warren Gray
 * @version 1.0
 */
public class GenFile {

	public static void main(String[] args) {

		// Set up defaults.
		String fileName = "file.out";
		int fileSize = 1024;
		
		// Override defaults, if applicable.
		if (args.length >= 1){
			fileName = args[0];
		}
		
		if (args.length >= 2){
			fileSize = Integer.parseInt(args[1]);
		}
		
		// Create the file.
		createFile(fileName, fileSize);
		System.exit(0);
	}
	
	/**
	 * Creates a file of the specified name and size.
	 * @param fileName - the file path to create a file for.
	 * @param fileSize - the size of the file to create.
	 */
	public static void createFile(String fileName, int fileSize){
		System.out.println("Creating file " + fileName + " of size " + fileSize + " byte(s).");
		
		// Generate random bytes.
		byte[] bytes = new byte[fileSize]; 
		Random rng = new Random();
		rng.nextBytes(bytes);
		
		// Write bytes to file.
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(fileName);
			fos.write(bytes);
			fos.close();
		}catch (FileNotFoundException e){
			System.err.println("Could not open " + fileName + " for editing: " + e.getMessage());
		}catch (IOException e){
			System.err.println("Could not write to " + fileName + ": " + e.getMessage());
		}
	}
}
