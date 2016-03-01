import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExpressionCalculator implements ActionListener {
	
	//GUI Objects
	JFrame expressionWindow = new JFrame();
	JTextField inputField = new JTextField(20);
	JTextField outputField = new JTextField(20);
	JTextField variableField = new JTextField(5);
	JButton submitButton = new JButton("Evaluate");
	JLabel varLabel = new JLabel("x = ");
	JPanel topPanel = new JPanel();
	JTextArea workArea = new JTextArea("Work for solved problems will show up here.");
	JScrollPane workScroll = new JScrollPane(workArea);
	JLabel errorLabel = new JLabel();
	JPanel errorPanel = new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ExpressionCalculator();
	}
	
	
	public ExpressionCalculator() {
		//EXAMPLE OF FONT
		//outChatTextArea.setFont(new Font("default",Font.BOLD,20));
		//BUILDING THE GUI
		expressionWindow.getContentPane().add(topPanel,"North");
		expressionWindow.getContentPane().add(errorPanel, "South");
		expressionWindow.getContentPane().add(workScroll, "Center");
		
		errorPanel.add(errorLabel);
		errorPanel.setBackground(Color.pink);
		
		errorLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		errorLabel.setText("Errors will show up here");
		
		topPanel.add(submitButton);
		topPanel.add(inputField);
		topPanel.add(varLabel);
		topPanel.add(variableField);
		topPanel.add(outputField);
		
		submitButton.addActionListener(this);
		inputField.addActionListener(this);
		
		workArea.setEditable(false);
		outputField.setEditable(false);
		
		expressionWindow.setLocation(100, 100);
		expressionWindow.setSize(800, 400);
		expressionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		expressionWindow.setVisible(true);
		//BUILDING THE GUI
		
	}

	@Override
	//**************************************************
	// Method: actionPerformed
	// Status: Complete
	// Arguments: ActionEvent e
	//
	// Who calls this method: 
	// 	When the "enter" key is pressed while inside
	// 	the inputField, this method is called. Also,
	// 	when the submitButton is pressed this method
	//      is called.
	//
	// Purpose/Structure:
	//	Starts the expressionCalculator algorithm by:
	//	1. Checking to see if the user has typed
	//	anything into the inputField. If not, it
	//	displays a message in the outputField, returns, 
	//	and waits for the user to hit "enter"
	//	or the submitButton.
	//	2. Calls the checkForErrors() method and passes
	//	whatever the user has typed into the inputField
	//	as a parameter.  If it returns true, then an
	//	error was found and this method will return. The 
	//	error message is handled by the checkForErrors()
	//	method.
	//	3. At this point, the user has typed in a valid
	//	expression that we are ready to solve. Now we 
	//	call the evaluateExpression() method and pass
	//	the expression String to it. We assign the 
	//	returned String to our "answer" variable and
	//	display it in the outputField.
	//
	//
	//
	//**************************************************
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String expression = inputField.getText();
		String answer;
		
		// Check to see if anything was typed in
		// If it was blank, then the user probably didn't mean to 
		if(expression.trim().length()==0) {
			inputField.setText("");
			outputField.setText("Nothing typed...");
			return;
		}
		
		//Check for errors and TRUE means ERROR
		if(checkForErrors(expression))
			return;
		
		//Evaluate the expression and set it equal to the answer
		answer = evaluateExpression(expression);
		
		outputField.setText(answer);
		
		
	}
	
	//**************************************************
	// Method: checkForErrors
	// Status: Incomplete
	// Arguments: String expression
	//
	// Who calls this method: 
	//	The actionPerformed() method will call this
	//	method to check for errors in the user's typed
	//	expression in the inputField.
	//
	// Purpose/Structure:
	//	The sole purpose of this method is to check
	//	for every possible error with the original
	//	expression.  This method calls numerous
	//	specific error-checking methods and this 
	//	method will return false (no error) if every
	//	error-checking method returns false as well.
	//
	//
	//
	//**************************************************
	public boolean checkForErrors(String expression) {
		// Check for all errors
		// TRUE MEANS ERROR
		
		//Possibly translate all unary operators here
		
		
		
		
		// End of translation of unary operators
		
		if(missingParenthesesError(expression)) {
			printError("Missing Parentheses Error");
			return true;
		}
		if(unmatchedParenthesesError(expression)) {
			printError("Unmatched Parentheses Error");
			return true;
		}
		if(unidentifiedOperatorError(expression)) {
			// Print the error message within the method
			// so that a more descriptive error could be given
			// as to what character caused the error
			// Example: "7%3-10*5" 
			// This should give an error message about the '%' character specifically
			// DOES NOT INCLUDE LETTERS
			return true;
		}
		if(missingOperatorError(expression)) {
			printError("Missing Operator Error");
			return true;
		}
		if(divideByZeroError(expression)){
			printError("Divide by Zero Error");
			return true;
		}
		if(adjacentBinaryOperators(expression)) {
			printError("Adjacent Operator Error");
			return true;
		}
		if(unknownSymbolError(expression)) {
			// Once again, put the printError message in the method
			// because we want to give the user an accurate message
			// of what symbol caused the error
			// Example: "y*3+37"
			// This should give an error message about the 'y' character
			// being an unknown symbol
			return true;
		}
		
		
		
		
		// NO ERRORS FOUND, return false
		return false;
	}
	
	public String evaluateExpression(String expression) {
		//EVALUATE THE EXPRESSION AND RETURN THE ANSWER STRING
		//CALLS METHODS....
		//FIND PRIORITY PARANTHESES (returns string inside the highest priority parantheses)
		return expression;
	}
	
	public String findPriorityParantheses(String expression){
		//
		//for loop
		/* finds the indecies of the top priority open and close parantheses and  truncates the string to include the parentheses at those indecies
		*/
		return expression; 
	}
	public void printError(String error) {
		//PRINT ERROR STRING TO ERROR LABEL
		errorLabel.setText("Error: "+error);
	}
	
	public boolean missingParenthesesError(String expression) {
		//CHECK FOR MISSING PARENTHESES ERROR
		//Should find error if number of open parentheses does not match number of closes parentheses
		
		
		return false;
	}
	
	public boolean unmatchedParenthesesError(String expression) {
		
		return false;
	}
	
	public boolean unidentifiedOperatorError(String expression) {
		
		return false;
	}
	
	public boolean missingOperatorError(String expression) {
		
		return false;
	}
	
	public boolean divideByZeroError(String expression) {
		
		return false;
	}
	
	public boolean adjacentBinaryOperators(String expression) {
		
		return false;
	}
	
	public boolean unknownSymbolError(String expression) {
		
		return false;
	}
	
	

}
