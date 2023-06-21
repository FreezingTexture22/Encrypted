package Encrypted;

import java.io.Reader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stage4 {
	static int shift = 0; // -key
	static String input = ""; // -data
	static String mode = "enc"; // enc or dec
	static String inPath = ""; // path to input file
	static String outPath = ""; // path to output file
	static boolean printToFile = false; // should program print to file?
	static boolean mustReadFromFile = false; // should program read from file?
	static boolean isDataPresent = false; // is there -data argument present?
	static boolean isInPresent = false; // is there -in argument present?
	static boolean isOutPresent = false; // is there -out argument present?

	// get arguments on main
	public static void main(String[] args) throws FileNotFoundException, IOException {

		// check if args even, if not - throw error (there must be argument + value)
		if (args.length % 2 == 1) {
			System.out.println("Error - check your arguments!");
			return;
		}

		// check if there argument present and mark it: -in -out -data
		for (String argument : args) {
			if (argument.equals("-in")) {
				isInPresent = true;
			} else if (argument.equals("-out")) {
				isOutPresent = true;
			} else if (argument.equals("-data")) {
				isDataPresent = true;

			}
		}



		for (int i = 0; i < args.length; i += 2) {
			String key = args[i];
			String value = args[i + 1];

			// check -mode arg
			if (key.equals("-mode")) {
				if (value.equals("dec")) {

					mode = "dec";
				} else if (value.equals("enc")) {

					mode = "enc";
				} else {
					System.out.println("Error - argument '-mode' must have a value 'enc' or 'dec'.");
					return;
				}

				// check -key arg
			} else if (key.equals("-key")) {

				if (value.matches("\\d+")) {
					shift = Integer.parseInt(value);

				} else {
					System.out.println("Error - argument '-key' must be a natural number.");
					return;
				}
			}

			// check if there is no -data and no -in
			if (!isDataPresent && !isInPresent) {
				input = "";
			}

			// If there is no -out argument, the program must print data to the standard
			// output;
			if (!isOutPresent) {
				printToFile = false;
			}

			// check -input arg
			if (key.equals("-data")) {
				if (value.length() > 0) {
					input = value;
				} else {
					input = "";
				}

			}

			if (key.equals("-in") && isDataPresent) {
				mustReadFromFile = false;

			} else if (key.equals("-in") && !isDataPresent) {
				// input file
				mustReadFromFile = true;
				inPath = value;
				readFromFile(inPath);

			}

			if (key.equals("-out")) {
				// output file
				printToFile = true;
				outPath = value;				

			}

		}

		hello(mode);
	}

	// initial input, tell program should it encode, decode, or exit
	private static void hello(String mode) throws IOException {
		boolean stopProgram = false;

		while (!stopProgram) {

			if (mode.equals("enc")) {
				enc();
				stopProgram = true;

			} else if (mode.equals("dec")) {
				dec();
				stopProgram = true;

			} else if (mode.equals("exit")) {
				stopProgram = true;

			} else {
				stopProgram = false;
			}
		}

	}

	// reading data from file to 'input'
	private static void readFromFile(String inPath) throws FileNotFoundException, IOException {
		StringBuilder inputFromFile = new StringBuilder();

		try (Reader reader = new FileReader(inPath)) {
			
			// read text data stream until stream is closed
			int charAsNumber = reader.read();

			while (charAsNumber != -1) {
				char c = (char) charAsNumber;
				inputFromFile = inputFromFile.append(c);
				charAsNumber = reader.read();
			}

			input = inputFromFile.toString();

		} catch (FileNotFoundException e) {
			System.out.println("Error! FileNotFoundException");
		} catch (IOException e) {
			System.out.println("Error! IOException");
		}

	}

	// writing result to file
	private static void writeToFile(String outPath, String outputStr) throws IOException {
		
		try {
			CharArrayWriter text = new CharArrayWriter(); // with what to write
			FileWriter fileWriter = new FileWriter(outPath, false); // where to write

			text.write(outputStr); // what we will write
			text.writeTo(fileWriter); // where to write

			text.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Error! IOException");
		}
	}

	// encryption part
	private static void enc() throws IOException {

		StringBuilder output = new StringBuilder();
		String outputStr = null;

		// check letter by letter
		for (int i = 0; i < input.length(); i++) {

			// encode all chars
			int intChar = input.charAt(i);
			char c = (char) (intChar + shift);
			output = output.append(c);
			outputStr = output.toString();

		}

		// if writing to file - then execute THIS and STOP
		if (printToFile) {
			writeToFile(outPath, outputStr);
			return;

		}

		// if standart output to console - execute THIS
		else
			System.out.println(outputStr);

	}

	// decryption part
	private static void dec() throws IOException {

		
		StringBuilder output = new StringBuilder();
		String outputStr = null;

		// check letter by letter
		for (int i = 0; i < input.length(); i++) {

			// encode all chars
			int intChar = input.charAt(i);
			char c = (char) (intChar - shift);
			output = output.append(c);
			outputStr = output.toString();

		}

		// if writing to file - then execute THIS and STOP
		if (printToFile) {
			writeToFile(outPath, outputStr);

			return;
			
		} 

		// if standart output to console - execute THIS
		else System.out.println(outputStr);

	}

}
