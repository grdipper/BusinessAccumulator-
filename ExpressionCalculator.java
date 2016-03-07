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
		String test = "4+3-10*-5+24*(-5-2)";
		System.out.println(replaceUnaryOperator(test));
		
		
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
		answer = evaluateExpression(replaceUnaryOperator(expression));
		
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
		String priorityExpr;
		
		//FIND PRIORITY PARENTHESES (returns string inside the highest priority parentheses)
		while(containsBinaryOperator(expression)) {
		
			if(expression.contains("("))
				priorityExpr = findPriorityParentheses(expression);
			else
				priorityExpr = expression;
			//Replace the highest priority expression with the solved expression
			
			expression = expression.replace(priorityExpr, simplifyExp(expression));
			expression = replaceUnaryOperator(expression);
		}
		
		
		if(numberOfUnaryOperators(expression)%2==0) {
			
			expression = expression.replace("u", "");
			expression = expression.replace("(", "");
			expression = expression.replace(")", "");
			
		}
		else {
			System.out.println("Odd number of u's");
			expression = expression.replace("u", "");
			expression = expression.replace("(", "");
			expression = expression.replace(")", "");
			expression = "-" + expression;
		}
			
		
		return expression;
	}
	public int numberOfUnaryOperators(String expression) {
		int i;
		int count = 0;
		char[] exprArray = expression.toCharArray();
		
		for(i=0;i<exprArray.length;i++) {
			if(exprArray[i]=='u')
				count++;
		}
		
		
		
		return count;
	}
	public String replaceUnaryOperator(String expression) {
		int i;
		
		char[] exprArray = expression.toCharArray();
		if(expression.contains("-")) {
			
			
			for(i=0;i<exprArray.length-1;i++) {
				if(exprArray[i]=='-') {
					if(i==0) {
						exprArray[i] = 'u';
					}
					else {
						
						if((exprArray[i-1]=='-')||(exprArray[i-1]=='('))
							exprArray[i]='u';
						else if((exprArray[i-1]=='+')||(exprArray[i-1]=='-')
								||(exprArray[i-1]=='*')||(exprArray[i-1]=='/')
								||(exprArray[i-1]=='r')||(exprArray[i-1]=='^'))
							exprArray[i]='u';
					}
				}
			}
			
			
		}
		else
			return expression;
		
		System.out.println(new String(exprArray));
		return new String(exprArray);
	}
	
	public String simplifyExp(String expression) {
		String priorityAns;
		//All unary operators should be expressed as a 'u'
		//Remove parentheses from beginning and end of expression
		//There should never be nested parentheses when this method is called
		System.out.println(expression);
		if(expression.startsWith("("))
			expression = expression.replace("(", "");
		if(expression.startsWith(")"))
			expression = expression.replace(")", "");
		
		//Parentheses have been removed and it is time to begin solving the expression
		
		int priority = 0;
		int i;
		int indexOfOperator = -1;
		int indexOfOp1 = -1;
		int indexOfOp2 = -1;
		String priorityExpr = "";
		char[] exprArray = expression.toCharArray();
		
		//Find index of operator
		for(i=1;i<exprArray.length;i++) {
			if(((exprArray[i]=='^')||(exprArray[i]=='r'))&&(priority<4)) {
				priority = 4;
				indexOfOperator = i;
			}
			else if(((exprArray[i]=='*')||(exprArray[i]=='/'))&&(priority<3)) {
				priority = 3;
				indexOfOperator = i;
			}
			else if(((exprArray[i]=='+')||(exprArray[i]=='-'))&&(priority<2)) {
				priority = 2;
				indexOfOperator = i;
			}
				
		}
		System.out.println(indexOfOperator);
		
		//Find index of operand 1
		
		for(i=indexOfOperator-1;i>=0;i--) {
			// If it's not a number
			if(exprArray[i]=='u') {
				indexOfOp1 = i;
				i = 0;
			}
			else if(i==0) {
				indexOfOp1 = 0;
				
			}
			else if((((exprArray[i]>'9')||(exprArray[i]<'0')||(i==0)))&&(exprArray[i]!='.')) {
				indexOfOp1 = i+1;
				i=0;
			}
		}
		
		for(i=indexOfOperator+1;i<exprArray.length;i++) {
			if(exprArray[i]=='u')
				System.out.println("Negative!");
			else if(i==exprArray.length-1)
				indexOfOp2 = exprArray.length-1;
			else if((((exprArray[i]>'9')||(exprArray[i]<'0')||(i==exprArray.length-1)))&&(exprArray[i]!='.')) {
				indexOfOp2 = i;
				i = exprArray.length;
			}
			
			
		}
		
		
		
		
		
		if(indexOfOp2==exprArray.length-1)
			priorityExpr = expression.substring(indexOfOp1,indexOfOp2+1);
		
		else priorityExpr = expression.substring(indexOfOp1,indexOfOp2);
		System.out.println(priorityExpr);
		priorityAns = solveMathExpression(priorityExpr);
		if(priorityExpr.equals(expression))
			expression = expression.replace(priorityExpr, priorityAns);
		
		else {
			String prefix = expression.substring(0,indexOfOp1);
			String suffix = expression.substring(indexOfOp2,expression.length());
			expression = prefix + solveMathExpression(priorityExpr) + suffix;
		}
		System.out.println("Solved priority expression: "+expression);
		expression = replaceUnaryOperator(expression);
		if(containsBinaryOperator(expression))
			expression = simplifyExp(expression);
		
		
		
		
		return expression;
	}
	
	public String solveMathExpression(String expression) {
		String answer = "0";
		String operator = "-";
		
		if(expression.contains("^"))
			operator = "^";
		else if(expression.contains("r"))
			operator = "r";
		else if(expression.contains("*"))
			operator = "*";
		else if(expression.contains("/"))
			operator = "/";
		else if(expression.contains("+"))
			operator = "+";
		else if(expression.contains("-"))
			operator = "-";
		String op1 = expression.substring(0,expression.indexOf(operator));
		String op2 = expression.substring(expression.indexOf(operator)+1, expression.length());
		
		if(op1.contains("u"))
			op1 = String.valueOf(Double.parseDouble(op1.substring(1) )*-1);
		if(op2.contains("u"))
			op2 = String.valueOf(Double.parseDouble(op2.substring(1) )*-1);
		
		
		if(operator=="+") 
			answer = String.valueOf(Double.parseDouble(op1) + Double.parseDouble(op2));
		else if(operator=="-") 
			answer = String.valueOf(Double.parseDouble(op1) - Double.parseDouble(op2));
		else if(operator=="*") 
			answer = String.valueOf(Double.parseDouble(op1) * Double.parseDouble(op2));
		else if(operator=="/") 
			answer = String.valueOf(Double.parseDouble(op1) / Double.parseDouble(op2));
		else if(operator=="^") 
			answer = String.valueOf(Math.pow(Double.parseDouble(op1), Double.parseDouble(op2)));
		else if(operator=="r") 
			answer = String.valueOf(Math.pow(Double.parseDouble(op1), 1/Double.parseDouble(op2)));
		
		//round answer to avoid weird bugs with 5*5 = 25.0000005
		answer = String.valueOf(Math.round(Double.parseDouble(answer) * 1000000.0) / 1000000.0);
		return answer;
	}
	
	
	public String findPriorityParentheses(String expression){
		//
		//for loop
		/* finds the indices of the top priority open and close parentheses and  truncates the string to include the parentheses at those indecies
		*/
		expression = "(3+4)";
		return expression; 
	}
	
	//**************************************************
		// Method: containsBinaryOperator
		// Status: Incomplete
		// Arguments: String expression
		//
		// Who calls this method: 
		//	The exaluateExpression() method calls upon
		//  this method to check for the completion of
		//  the program.
		//
		// Purpose/Structure:
		//	The purpose of this method is to check for
		//  any binary operators.  Returns true if there
		//  are any.
		//
		//
		//
   //**************************************************
	public boolean containsBinaryOperator(String expression) {
		//Check for binary operators: + / * r ^
		if((expression.contains("+"))||(expression.contains("-"))||(expression.contains("/"))||(expression.contains("r"))||
				(expression.contains("*"))||(expression.contains("^")))
					return true;
		
		char[] expArray = expression.toCharArray();
		int i;
		
		// Check for "-" binary operator
		for(i=1;i<expArray.length;i++) {
			if((((expArray[i-1]>='0')&&(expArray[i-1]<='9'))||(expArray[i-1]==')'))&&(expArray[i]=='-')) {
				System.out.println(i);
				return true;
			}
				
		}
		
		System.out.println("No binary operators found");
		return false;
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

