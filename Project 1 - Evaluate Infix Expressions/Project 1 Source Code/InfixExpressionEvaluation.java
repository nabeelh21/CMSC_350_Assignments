import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
/**
 * This class will perform the actual evaluation of the expression that is entered by the user.
 * 
 * @author Nabeel Hussain
 */

public class InfixExpressionEvaluation
{
	//Operator stack, which will contain the +, –, *, and / operators, as well as left and right parentheses
	private Stack<String> operatorStack = new Stack<String>();
	
	//Operand stack, which will contain any numbers that are input to the program, as well as the results of calculations.
	private Stack<String> operandStack = new Stack<String>(); 
	
	
	/**
	 * Evaluates the expression and returns the result
	 * 
	 * @param expression the string containing the expression to be evaluated
	 * @return the result of the expression
	 * @throws DivideByZeroException 
	 */
	public int evaluateExpression(String expression) throws DivideByZeroException
	{
		
		// Each time this method is called, make sure we start off with a new operator and operand stack to store the tokens. 
		operatorStack = new Stack<String>();
		operandStack = new Stack<String>();
	
		int answer = 0;  // Will hold the final result of the expression that is evaluated. 

		
		String expressionReadIn = expression;
		
		expressionReadIn = expressionReadIn.replaceAll(" ", ""); // Will remove all the spaces in the expression. 
		
		//tokenize the string containing the expression, using the operators "(,)" and "+, -, *, /" as the delimiters. 
		StringTokenizer tokenizedExpression = new StringTokenizer(expressionReadIn, "+-*/)(", true);
		
		//while there are more tokens in the expression being read in
	     while (tokenizedExpression.hasMoreTokens())
	     {
	    	 //get the next token
	    	 String nxtToken = tokenizedExpression.nextToken();	 

	    	 
	    	 //If it is an operand, then push it onto the operand stack.
	    	 //Since our program is only expected to perform correctly on syntactically correct infix expressions, then we can assume that
	    	 //if the token is not "+", "-", "*", "/", "(", or ")",then anything else will be assumed to be an integer/operand
	    	 if( !nxtToken.equals("+") && !nxtToken.equals("-") && !nxtToken.equals("*") &&!nxtToken.equals("/")
	    			  &&!nxtToken.equals("(") &&!nxtToken.equals(")"))
	    	 {
	    		 operandStack.push(nxtToken); 

	    	 } 	 
	     
		     //else if it is a left parenthesis
		     else if(nxtToken.equals("("))
		     {
		    	//push it on to the operator stack 
		    	 operatorStack.push(nxtToken);
		     }
	    	 
		     //else if it is a right parenthesis
		     else if(nxtToken.equals(")"))
		     {
		    	 //while top of the operator stack is not a left parenthesis
		    	 while(!operatorStack.peek().equals("("))
		    	 {
		    		 
		    		//pop two operands and an operator
		    		 
		    		 int y = 0; // Will hold the first operand that is popped from the operandStack
		    		 int x = 0; // Will hold the second operand that is popped from the operandStack
		    		 String operator = ""; // Will hold the operator that is popped from the operatorStack
		    		 
		    		 y = Integer.parseInt(operandStack.pop());
		    		 x = Integer.parseInt(operandStack.pop());
	    			 
		    		 operator = operatorStack.pop();

	        		 //calculate and push the result onto the operand stack, by using the calculate method to perform the calculation.
	        		 int calculation = calculate( x,  y, operator);
	    			
	        		 //push the calculated result back onto the operand stack
	        		 operandStack.push(Integer.toString(calculation));	 
		    	 }
		    	 // pop the left parenthesis off the top of the stack
		    	 operatorStack.pop();
		     }
	    	 
		     //else if it is an operator
		     else if( nxtToken.equals("+") || nxtToken.equals("-") || nxtToken.equals("*") || nxtToken.equals("/") )
		     {
		    	 //while the operator stack is not empty and
		    	 //the operator at the top of the stack has higher or the same precedence than the current operator
		    	 while( operatorStack.isEmpty() == false && hasHigherPrecedence(operatorStack.peek(), nxtToken) == true )
		    	 {
		    		 //pop two operands and an operator
		    		 
		    		 int y = 0; // Will hold the first operand that is popped from the operandStack
		    		 int x = 0; // Will hold the second operand that is popped from the operandStack
		    		 String operator = ""; // Will hold the operator that is popped from the operatorStack
		    		 
		    		 y = Integer.parseInt(operandStack.pop());
		    		 x = Integer.parseInt(operandStack.pop());
	    			 
		    		 operator = operatorStack.pop();

	        		 //calculate and push the result onto the operand stack, by using the calculate method to perform the calculation.
	        		 int calculation = calculate( x,  y, operator);
	    			
	        		 //push the calculated result back onto the operand stack
	        		 operandStack.push(Integer.toString(calculation));	 

		    	 }
		    	 //push the current operator on the operators stack, if it has a lower precedence compared to the operator on the top of the stack
		    	 operatorStack.push(nxtToken);
		     }	 
	     }
	     
    	//while the operator stack is not empty 
    	 while( operatorStack.isEmpty() == false)
    	 {
    		 //pop two operands and an operator
    		 
    		 int y = 0; // Will hold the first operand that is popped from the operandStack
    		 int x = 0; // Will hold the second operand that is popped from the operandStack
    		 String operator = ""; // Will hold the operator that is popped from the operatorStack
    		 
    		 y = Integer.parseInt(operandStack.pop());
    		 x = Integer.parseInt(operandStack.pop());
			 
    		 operator = operatorStack.pop();

    		 //calculate and push the result onto the operand stack, by using the calculate method to perform the calculation.
    		 int calculation = calculate( x,  y, operator);
			
    		 //push the calculated result back onto the operand stack
    		 operandStack.push(Integer.toString(calculation));	  
   		 
    	 }
			answer = Integer.parseInt(operandStack.pop());
			return answer;
	}
	
	
	/**
	 * Performs the next calculation in the expression being evaluated. It will be one of either 4 operations +, -, *, /
	 * 
	 * @param x The first variable of the calculation  
	 * @param y The second variable of the calculation
	 * 
	 * @return the answer to the calculation that is performed. 
	 */
    public int calculate(int x, int y, String operator) throws DivideByZeroException
    {
    	int calculationResult = 0;

    	// performs the addition of two numbers if "+"
    	if( operator.equals("+") )
		 {
    		calculationResult = x + y;
		 }
    	
    	// performs the subtraction of two numbers if "-"
    	else if( operator.equals("-") )
		 {
    		calculationResult = x - y;
		 }
    	
    	// performs the multiplication of two numbers if "*"
    	else if( operator.equals("*") )
		 {
    		calculationResult = x * y;
		 }
    	
    	// performs the division of two numbers if "/" and throws a division by zero if the second variable is a 0.
    	else if( operator.equals("/") )
		 {
    		if(y == 0)
    		{
   			 throw new DivideByZeroException ("Error. Cannot divide an expression by zero.");

    		}
    		else
    		{
    			calculationResult = x / y;
    		}
		 }
    	else
    	{
			JOptionPane.showMessageDialog(null, "Error. Not a valid operator.");
    	}
		return calculationResult;
    }   
    
    
    
	
	/**
	 * Returns true if the first string read in(which is the operator at the top of the stack)
	 * has higher precedence or the same precedence, compared to the second string read in (current operator).
	 * 
	 * @param topOperator the operator at the top of the stack
	 * @param currentOperator the current operator
	 * 
	 * @return true if the operator at the top of the stack has higher or same precedence than the current operator of the stack,then return true. Otherwise, false.
	 */
    public boolean hasHigherPrecedence(String topOperator, String currentOperator)
    {
    	// if the operator on the top of the stack is either "*" or "/", then it will always have either a higher or equal precedence, so return true.
    	if ( (topOperator.equals("*") || topOperator.equals("/")))
    	{	
    		return true;		
    	}
    	// if the current operator is either "+" or "-", then the top operator will always have either a higher or equal precedence, so return false.
    	else if((currentOperator.equals("+") || currentOperator.equals("-")))
    	{
    		return false;
    	}
    	// if the top operator is either "+" or "-", then it will return false if the current is "*" or "/"
    	else if( (topOperator.equals("+") || topOperator.equals("-")) && (currentOperator.equals("*") || currentOperator.equals("/")) )
    	{
    		return false;
    	}
		return false;
    }   
}
