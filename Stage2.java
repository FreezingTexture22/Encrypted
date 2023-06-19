package Encrypted;

import java.util.Scanner;

public class Stage2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		int shift = scanner.nextInt();
		StringBuilder output = new StringBuilder();

		while (shift > 26) {
			shift = shift - 26;
		}

		for (int i = 0; i < input.length(); i++) {
			if (!Character.isLetter(input.charAt(i))) {
				output = output.append(input.charAt(i));
			} else {
				int intChar = input.charAt(i);

				char c = (char) (intChar + shift);

				if (c > 122) {
					c = (char) (96 + (c - 122));
				}

				output = output.append(c);
			}
		}

		System.out.println(output);

	}
}
