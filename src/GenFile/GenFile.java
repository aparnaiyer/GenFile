package GenFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Generates randomly filled files of a specified name and size.
 * @author Warren Gray
 * @version 1.1
 */
public class GenFile {

	public static void main(String[] args) {

		// Set up defaults.
		String fileName = "file.out";
		int fileSize = 1024;
		int chunkSize = 1024 * 1024 * 100;
		
		// Override defaults, if applicable.
		if (args.length >= 1){
			fileName = args[0];
		}
		
		if (args.length >= 2){
			fileSize = Integer.parseInt(args[1]);
		}
		
		if (args.length >= 3){
			chunkSize = Integer.parseInt(args[2]);
		}		
		
		// Create the file.
		createFile(fileName, fileSize, chunkSize);
		System.exit(0);
	}
	
	/**
	 * Creates a file of the specified name and size.
	 * @param fileName - the file path to create a file for.
	 * @param fileSize - the size of the file to create.
	 */
	public static void createFile(String fileName, int fileSize, int maxMem){
		System.out.println("Creating file " + fileName + " of size " + fileSize + " byte(s).");
		
		byte[] bytes = new byte[maxMem]; 
		Random rng = new Random();
		rng.nextBytes(bytes);
		
		// Write bytes to file.
		FileOutputStream fos = null;
		try{
			int numBytesLeft = fileSize;
			fos = new FileOutputStream(fileName);
			while(true){
				
				// If we have less than maxMem left to write, simply redeclare the array
				// to preserve the exact bytes specified in the arguments.
				if (numBytesLeft < maxMem){
					bytes = new byte[numBytesLeft];
				}
				
				// Fill the byte array with random data and write it to the file.
				rng.nextBytes(bytes);
				fos.write(bytes);
				
				// Reduce the number of bytes left to write and stop looping if necessary.
				numBytesLeft -= maxMem;
				if (numBytesLeft <= 0){
					break;
				}
			}
			fos.close();
		}catch (FileNotFoundException e){
			System.err.println("Could not open " + fileName + " for editing: " + e.getMessage());
		}catch (IOException e){
			System.err.println("Could not write to " + fileName + ": " + e.getMessage());
		}
	}
}
