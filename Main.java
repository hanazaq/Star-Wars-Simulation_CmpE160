/**
 * 
 */
package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import project.activeParts.CreateObjects;
import project.activeParts.PrintResult;

/**
 * Main Class connection between input and output file
 * @author Hanaa Zaqout
 *
 */
public class Main {

	/**
	 * reads from input file 
	 * calls createObjectsHere method where objects are created and events called
	 * calls printFinalResult method to write results on output file 
	
	 */
	public static void main(String[] args) {
		// read file names
		Scanner in = null;
		String inputFile = args[0];
		String outputFile = args[1];
		File file = new File(inputFile);

		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(outputFile));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		System.setOut(out);

		//create objects from given input
		CreateObjects create= new CreateObjects(in);
		create.createObjectsHere();
		
		//write results
		PrintResult print= new PrintResult();
		print.printFinalResult();
		
//		for(int i=1; i<CreateObjects.getWarships().size();i++)
//	 { Warship w=CreateObjects.getWarships().get(i);
//			System.out.println(w.getName()+" "+w.getCrew().toString());
//		}


	}

}
