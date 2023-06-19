package Encrypted;

public class Stage1 {
	public static void main(String[] args) {
		String input = "we found a treasure!";
		String anyChar = "[a-z]";
		StringBuilder output = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++ ) {
			if (input.charAt(i) == ' ' || input.charAt(i) == '!') {
				output = output.append(input.charAt(i));
			} else {
				char tmp = (char) (219 - input.charAt(i));
				output = output.append(tmp);
				
			}
			
		}
		
		System.out.println(output);
		System.out.println(output);
		
		
	}
}
