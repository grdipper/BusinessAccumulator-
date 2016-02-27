import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class BusinessAccumulator implements ActionListener {

	// globalUserInputValueMath is the User's input string in correct double
	// form
	static double globalUserInputValueMath = 0;
	static double accumulatedValue = 0;
	
	//Grant's GUI variables
	
	
	
	public BusinessAccumulator() {
		// TODO Auto-generated constructor stub
		
		//Build the GUI
		
		
		
		
		
		
		
		
		
		
		
		//End of GUI
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ACCUMULATEBUTTON) {
			
			//CHECK FOR ERRORS IN THE textArea
			
			if(CheckForUserInput(textArea.getText())) {
			
				//No errors in textArea
				//Continue on to accumulate the value and displaying it in the "log" textArea
				//Alden's code already has set the value that will be used to accumulate
				
				AccumulateTotal();
			
			}
			
			else {
				
				//Show error in the GUI
				
				return;
			
			}
		
		
		}
		
		if(e.getSource()==CLEARBUTTON) {
			// Code for clear button
		
		
		
		}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BusinessAccumulator();
	}
	
	
	
	/*
	***********************************************************************************
	AccumulateTotal() Method
	
	Accumulates new total and sets the global variable accumulatedValue to be 
	the new calculated value.
	Places it in the "log" textArea and scrolls to the bottom 
	
	Current status: incomplete
	
	***********************************************************************************
	*/
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
	
	void AccumulateTotal() {
		//Local string variable to be added to LOGAREA
		
		String newAccumulate = "";
		double oldAccumulate=accumulatedValue;
		if(globalUserInputValueMath>=0) {
			//POSITIVE NUMBER TYPED
			//Shows up in "log" textArea as "old_value + typed_value = new_value"
			//Check Bowman's program if unsure of "log" format
			
			//Set string newAccumulate to what it should look like here
			accumulatedValue =globalUserInputValueMath + accumulatedValue;
			newAccumulate= oldAccumulate + " + " + globalUserInputValueMath + " = " + accumulatedValue; 
			
		}
		
		else {
			//NEGATIVE NUMBER TYPED
			//Shows up in "log" textArea as "old_value - absolute_value_of(typed_value) = new_value"
			//Check Bowman's program if unsure of "log" format
			
			//Set string newAccumulate to what it should look like here
			double absolute_userinput=abs(globalUserInputValueMath);//takes absolute value of our globalUserInputValueMath
			accumulatedValue = globalUserInputValueMath + accumulatedValue;//calculates new  accumulated Value 
			newAccumulate= oldAccumulate + " - " + absolute_userinput + " = " + accumulatedValue; 
			
		}
		
		//Add the newAccumulate string to the "log" textArea and scroll down to bottom
		
		
		//GRANT - Feel free to change the names of these, I made them called LOGAREA
		// before you made the GUI 
		LOGAREA.append(newAccumulate);
		LOGAREA.setCaretPosition(LOGAREA.getDocument().getLength()); 
	
	
	
	}
	
	
	
	
	
	/*
	***********************************************************************************
	Alden's Error-checking Method
	
	Checks for errors and returns false if the value to be accumulated has errors
	Sets a global variable to what the user inputed as a double*/
	
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
	/*
	***********************************************************************************
	*/
	
	
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
