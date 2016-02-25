import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class BusinessAccumulator implements ActionListener {

	public BusinessAccumulator() {
		// TODO Auto-generated constructor stub
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
		// globalUserInputValueMath is the User's input string in correct double
	// form
	static double globalUserInputValueMath = 0;
	// errorWithUserInput is a string that contains the error with the user's
	// input
	static String errorWithUserInput = "Nothing is wrong.";

	private static boolean CheckForUserInput(String userInput) {
		boolean userInputContains_d = false;
		boolean userInputContains_$ = false;
		// for InputContainsTwoAfterDecimal, 0 indicates no decimal present, 1
		// indicates decimal present and
		// satisfies parameters, 2 indicates that there is a decimal present and
		// it follows parameters
		int userInputContainsTwoAfterDecimal = 0;
		boolean userInputContainsDoubleParseError = false;
		double userValue = 0;
		String storedUserInput = userInput;

		// Remove excess whitespace and handles a blank input
		userInput = userInput.trim();

		if (userInput == "") {
			errorWithUserInput = "";
			globalUserInputValueMath = 0;
			return false;
		}

		// Removes d from the string and sets the boolean value to true
		if (userInput.endsWith("d")) {
			if (userInput.length() > 0 && userInput.charAt(userInput.length() - 1) == 'd') {
				userInput = userInput.substring(0, userInput.length() - 1);
				userInputContains_d = true;
			}

		}
		// removes $ from the string and sets the boolean value to true
		if (userInput.startsWith("$")) {
			if (userInput.length() > 0 && userInput.charAt(0) == '$') {
				userInput = userInput.substring(1, userInput.length());
				userInputContains_$ = true;
			}

		}

		if (userInput.contains(".")) {
			userInputContainsTwoAfterDecimal = 1;
			int decimalLocation = userInput.indexOf('.');
			if ((userInput.length() - 3) <= decimalLocation) {
				userInputContainsTwoAfterDecimal = 2;
				// //Trace Statements
				// System.out.println(userInput.length());
				// System.out.println(decimalLocation);
				// System.out.println("truee");
			}

		}

		// Tests the string for everything else
		try {
			userValue = Double.parseDouble(userInput);
		} catch (IllegalArgumentException ex) {

			userInputContainsDoubleParseError = true;
		}

		globalUserInputValueMath = 0;

		if (userInputContainsDoubleParseError == true) {
			errorWithUserInput = "amount is not numeric";
			return false;

		} else if (userInputContainsTwoAfterDecimal == 1) {
			errorWithUserInput = "Two decimal digits are required following a decimal point.";
			return false;
		} else {
			// CheckUserInput method changes the global variable to equal it's
			// math form instead of a string
			globalUserInputValueMath = userValue;
			return true;

		}

	}
}
