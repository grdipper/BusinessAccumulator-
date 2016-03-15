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

@SuppressWarnings("unused")
public class ExpressionCalculator implements ActionListener {
	
	//GUI Objects
	JFrame expressionWindow = new JFrame();
	JTextField inputField = new JTextField(20);
	JTextField outputField = new JTextField(25);
	JTextField variableField = new JTextField(5);
	JButton submitButton = new JButton("Evaluate");
	JLabel varLabel = new JLabel("x = ");
	JLabel varLabel2 = new JLabel("Type expression here: ");
	JPanel topPanel = new JPanel();
	//JTextArea workArea = new JTextArea("Work for solved problems will show up here.");
	//JScrollPane workScroll = new JScrollPane(workArea);
	JLabel errorLabel = new JLabel();
	JPanel errorPanel = new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ExpressionCalculator();
	}
	
	
	public ExpressionCalculator() {
		//System.out.println(findPriorityParentheses("(3+4*(3-1))"));
		//EXAMPLE OF FONT
		//outChatTextArea.setFont(new Font("default",Font.BOLD,20));
		//BUILDING THE GUI
		expressionWindow.getContentPane().add(topPanel,"North");
		expressionWindow.getContentPane().add(errorPanel, "South");
		//expressionWindow.getContentPane().add(workScroll, "Center");
		
		errorPanel.add(errorLabel);
		errorPanel.setBackground(Color.white);
		
		errorLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		errorLabel.setText("Errors will show up here");
		
		
		
		topPanel.add(submitButton);
		topPanel.add(varLabel2);
		topPanel.add(inputField);
		topPanel.add(varLabel);
		topPanel.add(variableField);
		topPanel.add(outputField);
		
		submitButton.addActionListener(this);
		inputField.addActionListener(this);
		variableField.addActionListener(this);
		
		//workArea.setEditable(false);
		outputField.setEditable(false);
		
		expressionWindow.setLocation(100, 100);
		expressionWindow.setSize(1000, 200);
		expressionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		expressionWindow.setVisible(true);
		
		//BUILDING THE GUI
		//String test = "4+3-10*-5+24*(-5-2)";
		//System.out.println(replaceUnaryOperator(test));
		
		
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
		String eX = variableField.getText();
		String answer;
		
	if(e.getSource() == variableField){
		if((expression.trim().length()==0) && (eX.trim().length() == 0)) {
			errorPanel.setBackground(Color.WHITE);
			inputField.setText("");
			errorLabel.setText("");
			variableField.setText("");
			outputField.setText("Nothing has been typed in either of the fields.");
			return;
		}
		
		if((expression.trim().length()==0) && (eX.trim().length() != 0)) {
			errorPanel.setBackground(Color.WHITE);
			inputField.setText("");
			errorLabel.setText("");
			variableField.setText("");
			outputField.setText("Nothing has been typed in the expression field.");
			return;
		}
		
		if((expression.contains("x")) && (eX.trim().length() == 0)){
			errorPanel.setBackground(Color.WHITE);
			inputField.setText("");
			errorLabel.setText("");
			variableField.setText("");
			outputField.setText("Nothing has been typed in the variable field.");
			return;
		}
	
		for(int a = 0; a < eX.length(); a++){
			if((Character.getNumericValue(eX.charAt(a)) == -1) && (eX.charAt(a) != '-')){
				errorPanel.setBackground(Color.PINK);
				inputField.setText("");
				variableField.setText("");
				outputField.setText("");
				errorLabel.setText("Error: Value entered in Variable Field is non-numeric");
			}
			if((expression.contains("x") && ((Character.getNumericValue(eX.charAt(a)) != -1)
				|| eX.charAt(a) == '-'))){
				expression = expression.replace("x", eX );
				if(adjacentBinaryOperators(expression)){
					inputField.setText("");
					variableField.setText("");
					errorPanel.setBackground(Color.PINK);
					outputField.setText("");
					errorLabel.setText("Error: Adjacent Operator Error");
					return;
					}
			}
			answer = evaluateExpression(replaceUnaryOperator(expression));
			inputField.setText("");
			variableField.setText("");
			errorPanel.setBackground(Color.WHITE);
			outputField.setText(answer);
			errorLabel.setText("");
		}
	}
		
	if(e.getSource() == inputField) {
		// Check to see if anything was typed in
		// If it was blank, then the user probably didn't mean to 
		if((expression.trim().length()==0) && (eX.trim().length() == 0)){
			errorPanel.setBackground(Color.WHITE);
			inputField.setText("");
			errorLabel.setText("");
			variableField.setText("");
			outputField.setText("Nothing has been typed in either of the fields.");
			return;
		}
		if((expression.contains("x")) && (eX.trim().length() == 0)){
			errorPanel.setBackground(Color.WHITE);
			inputField.setText("");
			errorLabel.setText("");
			variableField.setText("");
			outputField.setText("Nothing has been typed in the variable field.");
			return;
		}
		if((expression.trim().length()==0) && (eX.trim().length() != 0)) {
			errorPanel.setBackground(Color.WHITE);
			inputField.setText("");
			errorLabel.setText("");
			variableField.setText("");
			outputField.setText("Nothing has been typed in the expression field.");
			return;
		}
	for(int a = 0; a<=eX.length(); a++){
		if((expression.contains("x") && (Character.getNumericValue(eX.charAt(a)) != -1))){
			expression = expression.replace("x", eX );
			System.out.println("Trace 1");
			if(adjacentBinaryOperators(expression)){
				inputField.setText("");
				variableField.setText("");
				errorPanel.setBackground(Color.PINK);
				outputField.setText("");
				errorLabel.setText("Error: Adjacent Operator Error");
				}
			}
		}
		//Check for errors and TRUE means ERROR
		if(checkForErrors(expression)){
			inputField.setText("");
			errorPanel.setBackground(Color.PINK);
			outputField.setText("");
			return;
		}
		
		//Evaluate the expression and set it equal to the answer
				expression = expression.replace("x", eX );
				answer = evaluateExpression(replaceUnaryOperator(expression));
				inputField.setText("");
				variableField.setText("");
				errorPanel.setBackground(Color.WHITE);
				outputField.setText(answer);
				errorLabel.setText("");
	}
			
	if(e.getSource() == submitButton){
		// Check to see if anything was typed in
				// If it was blank, then the user probably didn't mean to 
				if((expression.trim().length()==0) && (eX.trim().length() == 0)){
					errorPanel.setBackground(Color.WHITE);
					inputField.setText("");
					errorLabel.setText("");
					variableField.setText("");
					outputField.setText("Nothing has been typed in either of the fields.");
					return;
				}
				if((expression.contains("x")) && (eX.trim().length() == 0)){
					errorPanel.setBackground(Color.WHITE);
					inputField.setText("");
					errorLabel.setText("");
					variableField.setText("");
					outputField.setText("Nothing has been typed in the variable field.");
					return;
				}
				if((expression.trim().length()==0) && (eX.trim().length() != 0)) {
					errorPanel.setBackground(Color.WHITE);
					inputField.setText("");
					errorLabel.setText("");
					variableField.setText("");
					outputField.setText("Nothing has been typed in the expression field.");
					return;
				}
			for(int a = 0; a<=eX.length(); a++){
				if((expression.contains("x") && (Character.getNumericValue(eX.charAt(a)) != -1))){
					expression = expression.replace("x", eX );
					System.out.println("Trace 1");
					if(adjacentBinaryOperators(expression)){
						inputField.setText("");
						variableField.setText("");
						errorPanel.setBackground(Color.PINK);
						outputField.setText("");
						errorLabel.setText("Error: Adjacent Operator Error");
						}
					}
				}
				//Check for errors and TRUE means ERROR
				if(checkForErrors(expression)){
					inputField.setText("");
					errorPanel.setBackground(Color.PINK);
					outputField.setText("");
					return;
				}
				
				//Evaluate the expression and set it equal to the answer
						expression = expression.replace("x", eX );
						answer = evaluateExpression(replaceUnaryOperator(expression));
						inputField.setText("");
						errorPanel.setBackground(Color.WHITE);
						outputField.setText(answer);
						errorLabel.setText("");
			}
		
		if(checkForErrors(expression)){
			inputField.setText("");
			errorPanel.setBackground(Color.PINK);
			outputField.setText("");
			return;
		}
	
		//Evaluate the expression and set it equal to the answer
				expression = expression.replace("x", eX );
				answer = evaluateExpression(replaceUnaryOperator(expression));
				inputField.setText("");
				variableField.setText("");
				errorPanel.setBackground(Color.WHITE);
				outputField.setText(answer);
				errorLabel.setText("");
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
		
		if((expression.startsWith("("))&&(expression.endsWith(")")))
			expression=expression.substring(1,expression.length()-1);
		
		//FIND PRIORITY PARENTHESES (returns string inside the highest priority parentheses)
		while(containsBinaryOperator(expression)) {
		
			if(expression.contains("(")) {
				priorityExpr = findPriorityParentheses(expression);
				expression = expression.replace(priorityExpr, evaluateExpression(priorityExpr));
				
				
			}
			else {
				priorityExpr = expression;
			//Replace the highest priority expression with the solved expression
			
			expression = expression.replace(priorityExpr, simplifyExp(priorityExpr));
			
			}
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
		if(expression.endsWith(")"))
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
			if(!containsBinaryOperator(suffix))
				suffix = "";
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
		System.out.println("boom: "+op1+" "+op2);
		
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
		
		System.out.println("BANG!: "+answer);
		//round answer to avoid weird bugs with 5*5 = 25.0000005
		answer = String.valueOf(Math.round(Double.parseDouble(answer) * 10000000.0) / 10000000.0);
		System.out.println("BANG!: "+answer);
		return answer;
	}
	
/**************************************************
	// Method: findPriorityParentheses
	// Status: Complete--------------------------------------------> NEEDS TESTING
	// Arguments: String expression
	 	Returns: String expression         (with open and close parentheses)
	//
	// Who calls this method: 
	//	evaluateExpression method, only if the expression in the method has found a parentheses
	//	
	//
	// Purpose/Structure:
	 	Finds the indices of the top priority open and close parentheses, and truncates the string
		to return that string.
	***********************************************/
	public String findPriorityParentheses(String expression){
		char[] exprArray = expression.toCharArray();
		boolean priorityParFound=false;
		int openParIndex=0;                        // index of priority parantheses
		int closeParIndex=expression.length()-1;
		/*(int x=0;
		int y=0;
		int z=0;
		int r=0;*/
		for(int i=0;i<=exprArray.length-1;i++){ 						//Outer Loop
			if(exprArray[i] == '(')
				{
				//z++;
				openParIndex=i; //saves Index of Open Parentheses
					for(int j=i+1;j<=exprArray.length;j++){             //Inner Loop
						{
							if(exprArray[j]=='(')
							{
								openParIndex=j; //New priority Index
								//x++;
							}
							if(exprArray[j]==')')
							{
								closeParIndex=j;      // 
								priorityParFound= true;
								j=exprArray.length+1; //Exits the inner for loop, Indicates if
								//y++;
							}
							}
						}
			
				}
			if(priorityParFound) break;
			//r++;
			}
	
		expression = expression.substring(openParIndex,closeParIndex+1);//needs to return a function surrounded with parentheses.
		//System.out.println("z=" + z + " x=" + x + " y=" + y + " r=" + r);
		//System.out.println(expression);
		
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
	
	public static boolean missingParenthesesError(String expression) {
		//CHECK FOR MISSING PARENTHESES ERROR
		//Should find error if number of open parentheses does not match number of closes parentheses
		char[] exprArray = expression.toCharArray();
		int paranCount=0;
		
		for(int i=0;i<exprArray.length;i++)
		{
			if(exprArray[i]=='(') paranCount++;
			else if(exprArray[i]==')') paranCount--;
			//System.out.println(paranCount);
		}
		
		//System.out.println("Final= " + paranCount);
		if(paranCount==0)return false;
		else return true;
	}

	
	//Method to check that each parentheses has a match 
	//Returns false if all parentheses having a matching pair
	//By Alden Cope
	public static boolean unmatchedParenthesesError(String expression) {
		String manipulatedExpression = expression;
		int parenthesesCounter = 0;
		for( int i=0; i<manipulatedExpression.length(); i++ ) {
			//if right facing parentheses found increase count by one
		    if( manipulatedExpression.charAt(i) == '(' ) {
		    	parenthesesCounter++;
		    } 
		  //if left facing parentheses found decrease count by one
		    if( manipulatedExpression.charAt(i) == ')' ) {
		    	parenthesesCounter--;
		    } 
		  //If there are move left facing parentheses encountered at first
		    if(parenthesesCounter < 0){
		    	return true;
		    }
		}
		if(parenthesesCounter == 0){
			return false;
		}
		// answer = "Parentheses unmatched";
		return true;
	}
	

	//For letters --- unknown symbol
	//anything --- unknown operator
	//Need to look for ..
	//illegalOperator contains the illegal Operator found in string
	static char illegalOperator = ' ';
	//illegalIndexLocation contains the location of the illegal Operator
	static int illegalIndexLocation = 0;
	
	public boolean unidentifiedOperatorError(String expression) {
		//FirstCheck checks all of the chars in the string for an illegal char, it returns the illegalOperator and it's illegalIndexLocation
		//System.out.println(FirstCheck(expression));
		if(FirstCheck(expression) == true){
			//WhatTypeError returns true if the problem is a letter
			if(whatTypeError(expression) == true){
				printError("Expression contained unknown symbol: " + illegalOperator);
				//System.out.println("Expression contained unknown symbol: " + illegalOperator);

				return true;
			}
			else{
				printError("Expression contained unknown operator: " + illegalOperator);
				System.out.println("Expression contained unknown operator: " + illegalOperator);
				return true;
			}
		}
		
		return false;
		
		
	}


	public static boolean whatTypeError(String expression){
		//specialAlphabet excludes known letter operators r,x
		String specialAlphabet = "abcdfghijklmnopqrstuvwyz";
		for(int i = 0; i < (specialAlphabet.length() - 1); i++){
			if(illegalOperator == specialAlphabet.charAt(i)){
				return true;
				
			}
		}
	
		return false;
	}
	//specialCharCheck is a function that checks a string for a certain char and returns back true or false
	public static boolean specialCharCheck(char specialChar, String expression){
		if(expression.indexOf(specialChar) == -1){
			return false;
		}
	
		return true;
	}
	
	public static boolean FirstCheck(String expression){
	
		
	char[] allowedChars = {'(',')','^','*','/','+','-',' ', '1','2','3','4','5','6','7','8','9','0','e','x','p','.','r'};
	boolean matchesAnAllowedChar = false; 
	
	
//	if(specialCharCheck( '#',  expression)){
//		illegalOperator = '#';
//		illegalIndexLocation = expression.indexOf('#');
//		return true;
//	}
	
	//First for loop looks through string
	for(int i = 0; i < expression.length() ; i++ ){
		//Second for loop uses character given to it and checks for all allowed chars
		for(int k = 0; k < 23;  k++){
			
			//If it is an allowed char
			if(expression.charAt(i) == allowedChars[k]){
				//System.out.println(expression.charAt(i));
				//System.out.println(allowedChars[k]);
				matchesAnAllowedChar = true;
				if(expression.charAt(i) == 'p'){
					//Checks if the p is a part of pi operator
					//System.out.println("Detects a p");

					if(expression.charAt(i+1) == 'i'){
						//Increase i by one to skip i of pi
						i = i + 1;
						//System.out.println("Detects an i");
					}
					else{
						matchesAnAllowedChar = false;
						illegalOperator = 'p';
						illegalIndexLocation = i;
						//System.out.println(illegalOperator);
						return true;
					}
					
				}
				//If anAllowedChar is found it breaks out of the second for loop
				if(matchesAnAllowedChar == true){
					//breaks out of for loop
					k = 25;
					matchesAnAllowedChar = false;
				}

			}
			//If it reaches the end of searching for allowed characters
			if(k == 21){
				
					matchesAnAllowedChar = false;
					illegalOperator = expression.charAt(i);
					illegalIndexLocation = i;
					//System.out.println(illegalOperator);

					return true;
				}
			}

			
		}
	
	illegalOperator = ' ';
	illegalIndexLocation = 0;
	return false;
	}
	
	public boolean missingOperatorError(String expression) {
		
		return false;
	}
	
	public boolean divideByZeroError(String expression) {
		
		return false;
	}
	
	public boolean adjacentBinaryOperators(String expression) {
		
		char[] bOperators = {'+','-','/','*'} ; // Creates an array of binary operators to reference
		for(int k = 0; k<expression.length(); k++){ // this FOR loop checks the expression that is entered
			for(int j = 0; j<bOperators.length; j++){ // this FOR loop then compares the elements in the expression entered to the elements in the operators array		
				
				//This IF statement gets the first operator and checks if the next element in the expression is a "+, *, or /" and if the next element is another one of these
				//operators, then there is an adjacent binary operator error, thus return is TRUE.
				if((expression.charAt(k) == bOperators[j]) && ((expression.charAt(k+1) == bOperators[0])//Adjacent operator is +
						|| (expression.charAt(k+1) == bOperators[2]) // Adjacent operator is /
						|| (expression.charAt(k+1) == bOperators[3]))){ // Adjacent operator is *
					System.out.println(expression.charAt(k) + " followed by " + expression.charAt(k+1));
					return true;
				}
				
				//This IF statement gets the first operator and checks if the next element in the expression is a "-", then it checks if the second element after the first operator
				//is another operator.  These statements are to check for non-unary operators. The getNumericValue returns the corresponding value 0-9 if a char is 0-9. If a char
				//is anything other than 0-9, it returns -1, thus that is why the last condition is checking to see if it less than 0.
				if((expression.charAt(k) == bOperators[j]) && (expression.charAt(k+1) == bOperators[1]) && ((Character.getNumericValue(expression.charAt(k+2)) < 0))){
					return true;
				}
				
				//This IF statement checks to see if the first operator is a "-" sign. It then checks the next element to see if that is a "-" as well. Finally it checks the next
				//element after that to see if it IS a decimal number. If it is, we then can treat the SECOND "-" in the expression as a UNARY operator, which is why the
				//replaceUnaryOperator method is called.
				if((expression.charAt(k) == bOperators[1]) && (expression.charAt(k+1) == bOperators[1]) && ((Character.getNumericValue(expression.charAt(k+2)) >= 0))){
					expression = replaceUnaryOperator(expression);
				}
			}
		}	
		return false;
	}
	
	public boolean unknownSymbolError(String expression) {
		
		return false;
	}
	
	

}
