import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;


public class BusinessAccumulator implements ActionListener, KeyListener {

	// globalUserInputValueMath is the User's input string in correct double
	// form
	static double globalUserInputValueMath = 0;
	static double accumulatedValue = 0;
	
	//Grant's GUI variables
	JFrame	accWindow	= new JFrame();
	JButton accButton	= new JButton();
	JButton clearButton	= new JButton("Clear");
	JTextField inNumber	= new JTextField(10); //sets the length of the text field
	JTextField tot		= new JTextField(5); // sets the length of the text field
	JTextField logArea	= new JTextField();
	JTextField errorMsg = new JTextField();
	JScrollBar scroll	= new JScrollBar();
	JPanel clrtotnum	= new JPanel();
	JLabel totLabel		= new JLabel("Total ="); //Text right beside the "tot" text field
	JLabel inNumLabel	= new JLabel("Enter amount to add =>"); // Text right beside the "inNumber" text field 
	
	
	
	
	
	public BusinessAccumulator() {
		//Build the GUI
		accWindow.setSize(700,300);
		accWindow.setLocation(250, 200);
		accWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accWindow.getContentPane().add(accButton,"Center");
		accWindow.getContentPane().add(logArea,"Center");
		accWindow.getContentPane().add(errorMsg,"South");
		accWindow.getContentPane().add(clrtotnum,"North");
		
		accWindow.setTitle("Business Accumulator - Enter an amount to be added (minus sign OK)");
		accButton.setMnemonic(KeyEvent.VK_ENTER);
		
		clrtotnum.add(clearButton);
		clrtotnum.add(totLabel);
		clrtotnum.add(tot);
		clrtotnum.add(inNumLabel);
		clrtotnum.add(inNumber);
		
		tot.setText("$0");
		tot.setBackground(Color.CYAN);
		
		
		
		accWindow.setVisible(true);
		
		
		accButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		
		
		
		
		
		
		
		//End of GUI
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==accButton) {
			System.out.println("Acc Button Pressed");
			//CHECK FOR ERRORS IN THE textArea
			
			if(CheckForUserInput(inNumber.getText())) {
			
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
		
		if(e.getSource()==clearButton) {
			tot.setText("$0");
			inNumber.setText("");
	
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
	
	void AccumulateTotal() {
		//Local string variable to be added to LOGAREA
		
		String newAccumulate = "";
		
		if(globalUserInputValueMath>=0) {
			//POSITIVE NUMBER TYPED
			//Shows up in "log" textArea as "old_value + typed_value = new_value"
			//Check Bowman's program if unsure of "log" format
			
			//Set string newAccumulate to what it should look like here
			
			
		}
		
		else {
			//NEGATIVE NUMBER TYPED
			//Shows up in "log" textArea as "old_value - absolute_value_of(typed_value) = new_value"
			//Check Bowman's program if unsure of "log" format
			
			//Set string newAccumulate to what it should look like here
			
			
		}
		
		//Add the newAccumulate string to the "log" textArea and scroll down to bottom
		
		
		//GRANT - Feel free to change the names of these, I made them called LOGAREA
		// before you made the GUI 
		//logArea.append(newAccumulate);
		logArea.setCaretPosition(logArea.getDocument().getLength()); 
	
	
	
	}
	
	
	
	
	
	/*
	***********************************************************************************
	Alden's Error-checking Method
	
	Checks for errors and returns false if the value to be accumulated has errors
	Sets a global variable to what the user inputed as a double
	
	
	
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