import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class BusinessAccumulator implements ActionListener {

	// globalUserInputValueMath is the User's input string in correct double
	// form
	static double globalUserInputValueMath = 0;
	static double accumulatedValue = 0;
	
	//Grant's GUI variables
	public JFrame	accWindow			= new JFrame();
	public JButton clearButton			= new JButton("Clear");
	public JTextField inNumber			= new JTextField(10); //sets the length of the text field
	public JTextField tot				= new JTextField(5); // sets the length of the text field
	public JTextArea logArea			= new JTextArea("");
	public JTextField errorMsg 			= new JTextField();
	public JScrollPane logAreaScroll	= new JScrollPane(logArea);
	public JPanel clrtotnum				= new JPanel();
	public JLabel totLabel				= new JLabel("Total ="); //Text right beside the "tot" text field
	public JLabel inNumLabel			= new JLabel("Enter amount to add =>"); // Text right beside the "inNumber" text field 
	
	
	
	/*
	***********************************************************************************
	GUI Construction and Function
	
	GUI Construction Current Status: 1 more issue
	GUI Construction Current Issues: Can't seem to figure out the scroll bar...Simple I know.
											ayyy lmao
	
	GUI Function Current Status: Almost done.
	GUI Function Current Issues: 
	
	***********************************************************************************
	*/
	
	public BusinessAccumulator() {
		//Build the GUI
		
		
		accWindow.setSize(700,300);
		accWindow.setLocation(250, 200);
		accWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		accWindow.getContentPane().add(logAreaScroll, "Center");
		accWindow.getContentPane().add(clrtotnum,"North");
		
		accWindow.getContentPane().add(errorMsg,"South");
		
        
		
		
		
		
		
		accWindow.setTitle("Business Accumulator - Enter an amount to be added (minus sign OK)");
		
		clrtotnum.add(clearButton);	// --
		clrtotnum.add(totLabel);	//	 |
		clrtotnum.add(tot);			//	 | - This section sets the panel on the top of the GUI
		clrtotnum.add(inNumLabel);	//	 |
		clrtotnum.add(inNumber);	// --
		
		
		logAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tot.setText("$0");
		tot.setEditable(false);
		logArea.setBackground(Color.WHITE); 	//
		logArea.setEditable(false);				//INITILIZING THE GUI BACKGROUND
		errorMsg.setBackground(Color.WHITE);	//
		errorMsg.setEditable(false);			//
		
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		
		tot.setBackground(Color.CYAN);			//MAKE IT BLUE!?
		
		
		inNumber.addActionListener(this);		// The reason that this is on the inNumber TEXTFIELD is because the actionListener by default
												// listens for the ENTER key. When the enter key is pressed in the inNumber textfield, an a
												// action will occur.
		clearButton.addActionListener(this);
		
		accWindow.setVisible(true);				//ARE YOU NOT ENTERTAINED!?!?!?!?!
		//End of GUI
	}

	
	public void actionPerformed(ActionEvent e) {
		
		final String newLine = "\n";
		if(e.getSource()==inNumber) {
			System.out.println("Number has been entered...");
			String userInput = inNumber.getText();
			if(inNumber.getText().trim().isEmpty()) {
				inNumber.setText("");
				return;
			}
			if((CheckForUserInput(userInput))) {
				inNumber.setText("");
				System.out.println("No Errors");
				errorMsg.setBackground(Color.WHITE);
				errorMsg.setText("");
				//No errors in textArea
				//Continue on to accumulate the value and displaying it in the "log" textArea
				//Alden's code already has set the value that will be used to accumulate
				AccumulateTotal();
				logArea.setText(logArea.getText() + newLine);
				
			}
			
			else {
				
				System.out.println("User Input was: " + userInput);
				errorMsg.setBackground(Color.PINK);
				errorMsg.setFont(new Font("default",Font.BOLD,13));
				errorMsg.setText(errorWithUserInput);
				inNumber.setText("");
				return;
			}
			
		
		
		}
		
		if(e.getSource()==clearButton) {
			System.out.println("Clear Button Pressed...");
			accumulatedValue = 0;
			tot.setText("$0");
			inNumber.setText("");
			errorMsg.setBackground(Color.WHITE);
			errorMsg.setText("");
			tot.setBackground(Color.cyan);
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
	
	Current status: ALMOST DONE
	Issues: Treats EVERY numerical input as a double.
			If the new accumulated value is nonnegative, then the "tot" text field background needs to be CYAN
			If the new accumulated valie is negative, then the "tot" text field background needs to be RED
	
	***********************************************************************************
	*/
	
	void AccumulateTotal() {
 		//Local string variable to be added to LOGAREA
 		double oldAccumulate=accumulatedValue;
 		
 		if(globalUserInputValueMath>=0) {
 			//POSITIVE NUMBER TYPED
 			//Shows up in "log" textArea as "old_value + typed_value = new_value"
 			//Check Bowman's program if unsure of "log" format
 			
 			//Set string newAccumulate to what it should look like here
 			accumulatedValue = globalUserInputValueMath + accumulatedValue;
 			accumulatedValue = Math.round(accumulatedValue * 100.0) / 100.0;
 			logArea.append(oldAccumulate + " + " + globalUserInputValueMath + " = " + accumulatedValue);
 			//oldAccumulate + " + " + globalUserInputValueMath + " = " + accumulatedValue; 
 			tot.setText("$" + accumulatedValue);
 		}
 		
 		else {
 			//NEGATIVE NUMBER TYPED
 			//Shows up in "log" textArea as "old_value - absolute_value_of(typed_value) = new_value"
  			//Check Bowman's program if unsure of "log" format
  			
  			//Set string newAccumulate to what it should look like here
 			double absolute_userinput= Math.abs(globalUserInputValueMath);//takes absolute value of our globalUserInputValueMath
 			absolute_userinput = -1*(globalUserInputValueMath);//takes absolute value of our globalUserInputValueMath
  			accumulatedValue = globalUserInputValueMath + accumulatedValue;//calculates new  accumulated Value
  			accumulatedValue = Math.round(accumulatedValue * 100.0) / 100.0;
  			logArea.append(oldAccumulate + " - " + absolute_userinput + " = " + accumulatedValue);
  			tot.setText("$" + accumulatedValue);
 		}
 		if((double)((int)accumulatedValue)-(accumulatedValue)==0) {
 			//Value has non-zero digits beyond decimal point (integer)
 			int noTrailZero = (int) accumulatedValue;
 			tot.setText("$" + noTrailZero);
 		}
 		
 		
 		
 		
 		logArea.setCaretPosition(logArea.getDocument().getLength());
 		
 		if(accumulatedValue>=0) {
 			tot.setBackground(Color.CYAN);			//MAKE IT BLUE!?
 		}
 		else {
 			tot.setBackground(Color.red);
 		}
 		
 		
	}
	
	
	
	
	/*
	***********************************************************************************
	Alden's Error-checking Method
	
	Checks for errors and returns false if the value to be accumulated has errors
	Sets a global variable to what the user inputed as a double
	
	Status: ALMOST DONE
	Issue: 
	
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
		int userInputContainsThreeAfterComma = 0;
		boolean userInputContainsDoubleParseError = false;
		double userValue = 0;
		String storedUserInput = userInput;

		//Remove excess whitespace and handles a blank input
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
			if ((userInput.length() - 3) == decimalLocation) {
				userInputContainsTwoAfterDecimal = 2;
				// //Trace Statements
				//System.out.println(userInput.length());
				//System.out.println(decimalLocation);
				System.out.println("truee");
			}
			else
				System.out.println("problem");

		}

		int commaCount = userInput.length() - userInput.replace(",", "").length();

		if (commaCount > 0) {
			if (userInput.contains(",")) {
				userInputContainsThreeAfterComma = 1;
				// Trace Statement: System.out.println("contains ,");
				int commaLocation = userInput.lastIndexOf(',');
				int correctLocationFactor = userInput.lastIndexOf(',') + 1; // not
																			// sure
																			// of
																			// these
																			// numbers

				// Trace Statement: System.out.println(commaLocation);

				if (userInputContainsTwoAfterDecimal == 2) {
					correctLocationFactor = userInput.lastIndexOf(',') + 1; // not
																			// sure
																			// of
																			// these
																			// numbers
					// Trace Statement: System.out.println("has decimal");
				}
				// Trace Statement: System.out.println("correctLocationFactor: " + correctLocationFactor);
				// Trace Statement: System.out.println("length: " + userInput.length());
				if (((userInput.indexOf('.') - userInput.lastIndexOf(',')) != 4)
						&& (userInputContainsTwoAfterDecimal == 2)) {
					errorWithUserInput = ("Illegal use of comma in " + userInput);
					return false;
				}
				if ((userInput.length() - correctLocationFactor) < 4) {
					userInputContainsThreeAfterComma = 2;
					// Trace Statements
					// Trace Statement: System.out.println("no decimal and works");
				} else if ((userInput.length() - (correctLocationFactor)) < 7) {
					userInputContainsThreeAfterComma = 2;
					// Trace Statements
					// Trace Statement: System.out.println("decimal and works");
				}

			}
		}
		// For when there are more than 1 comma
		if (commaCount > 1) {
			int previousCommaLocation = 0;
			int nextCommaLocation = 0;
			nextCommaLocation = userInput.indexOf(',');
			String checkCommaPlacements = userInput;
			while (commaCount > 1) {

				// Trace Statement: System.out.println("previousCommaLocation: " + previousCommaLocation);
				// Trace Statement: System.out.println("nextCommaLocation: " + nextCommaLocation);
				// For first case running through the string
				if (previousCommaLocation == 0) {
					// Check to see if there is more than three numbers in front
					// of the comma or none
					if ((nextCommaLocation > 3) || (nextCommaLocation == 0)) {
						// Trace System.out.println("Entered more than one
						// number in front of the comma");
						errorWithUserInput = ("Illegal use of comma in " + userInput);
						return false;

					}
					// Remove Decimal part to make easier
					if (userInputContainsTwoAfterDecimal == 2) {
						checkCommaPlacements = checkCommaPlacements.substring(0, checkCommaPlacements.indexOf('.'));

					}
				}
				// Condition to check if the next pair of commas does not have 3
				// digits between each other
				else if ((nextCommaLocation - previousCommaLocation) != 4) {

					// Trace Statement: System.out.println("Check the correct number between commas");
					errorWithUserInput = ("Illegal use of comma in " + userInput);
					return false;
				}

				// Creates a new string with the furthest left comma removed
				// Trace Statement: System.out.println("1st checkCommaPlacements :" + checkCommaPlacements);
				checkCommaPlacements = checkCommaPlacements.substring(previousCommaLocation, nextCommaLocation) + '&'
						+ checkCommaPlacements.substring(nextCommaLocation + 1, checkCommaPlacements.length());

				previousCommaLocation = nextCommaLocation;
				nextCommaLocation = checkCommaPlacements.indexOf(',');
				commaCount = commaCount - 1;
				// Trace Statement: System.out.println("2nd checkCommaPlacements :" + checkCommaPlacements);

				// 343,454,234
			}

		}
		if (userInputContainsThreeAfterComma == 2) {
			userInput = userInput.replace(",", "");
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
		} else if (userInput.startsWith("0") && (!(Math.abs(userValue) < 1))){
		errorWithUserInput = "The amount should not have a leading 0.";
		globalUserInputValueMath = 0;
		return false;

		} else if (userInputContainsTwoAfterDecimal == 1) {
			errorWithUserInput = "Two decimal digits are required following a decimal point.";
			return false;
		} else if (userInputContainsThreeAfterComma == 1) {
			errorWithUserInput = ("Illegal use of comma in " + userInput);
			return false;
		} else {
			// CheckUserInput method changes the global variable to equal it's
			// math form instead of a string
			globalUserInputValueMath = userValue;
			return true;

		}

	}


}
