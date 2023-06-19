package Encrypted;

import java.util.Scanner;

public class Stage3 {

	public static void main(String[] args) {
		hello();
	}

	// initial input, tell program should it encode, decode, or exit
	private static void hello() {
		boolean stopProgram = false;
		Scanner scanner = new Scanner(System.in);

		while (!stopProgram) {

			String helloInput = scanner.nextLine();

			if (helloInput.equals("enc")) {
				enc();
				stopProgram = true;

			} else if (helloInput.equals("dec")) {
				dec();
				stopProgram = true;

			} else if (helloInput.equals("exit")) {
				stopProgram = true;

			} else {
				stopProgram = false;
			}
		}

	}

	// encryption part
	private static void enc() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine(); // phrase input
		int shift = scanner.nextInt(); // shift for characters, Caesar cypher
		StringBuilder output = new StringBuilder();

		// check letter by letter
		for (int i = 0; i < input.length(); i++) {

			// encode all chars
			int intChar = input.charAt(i);
			char c = (char) (intChar + shift);
			output = output.append(c);

		}

		System.out.println(output);

	}

	// decryption part
	private static void dec() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine(); // phrase input
		int shift = scanner.nextInt(); // shift for characters, Caesar cypher
		StringBuilder output = new StringBuilder();

		// check letter by letter
		for (int i = 0; i < input.length(); i++) {

			// encode all chars
			int intChar = input.charAt(i);
			char c = (char) (intChar - shift);
			output = output.append(c);

		}

		System.out.println(output);

	}

}
