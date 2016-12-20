import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
/**
 * This class is the data manager class, which builds the expression tree, and uses it to generate the expressions infix form and the three address code onto a file.   
 * 
 * @author Nabeel Hussain
 */
public class ExpressionTree
{
		//Operator stack, which will contain the Nodes of the expression tree. 
		private Stack<Node> operandStack = new Stack<Node>();
		private Node added;

		/**
		 * A boolean method that will determine if a sting is an operator or not. 
		 * 
		 * @param token the string being evaluated to see if it's an operator
		 * @return true if the string is either + - * or /, and false otherwise. 
		 */
	    public static boolean isOperator(String token)
	    {
	    	// determines if it is the addition operator "+"
	    	if( token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))
			 {
	    		return true;
			 }
	    	
	    	return false;
	    } 
	    
		/**
		 * A boolean method that will determine if a sting is an integer or not. 
		 * 
		 * @param token the string being evaluated to see if it's an integer
		 * @return true if the string is an integer, and false otherwise. 
		 */
	    public static boolean isInteger(String token)
	    {
	        try
	        {
	            Integer.parseInt(token);
	            
	            return true;
	        } catch (NumberFormatException nfe)
	        {
	            return false;
	        }    
	    }
	    
	    
		/**
		 * Since the postfix expression being read in does not require for there to be spaces between every operator, then this method will properly
		 * separate the elements using the spaces and operators as delimiters, and store them in an String[] Array in the proper order from left to right. 
		 * 
		 * @param postfixExpression the string that will be broken down into individual tokens. 
		 * @return a String[] array containing the tokens from the delimited expression. 
		 */
		public String[] postfixExpressionDilimeter(String postfixExpression)
		{
			//Separate the postfix expression into individual tokens, and store them in order from left to right in an ArrayList.
			//The tokens may or may not be separated by a space in the string that is read in. 
			
			String expression; // will hold the postfix expression being read in
							
			expression = postfixExpression;
			
			// split the string expression using the spaces as the dilimeters. 
			String[] splitExpressionArray = expression.split("\\s+"); 
			
			// Put each section of the splitExpressionArray back into a string, so it can then be tokenized using the operators ["+-*/"] to split the expression even further. 
			String splitExpression = "";
			
			for(int i = 0; i< splitExpressionArray.length ; i ++)
			{
				splitExpression += splitExpressionArray[i] + " ";
			}
			
			//tokenize the string splitExpression, using the operators "+, -, *, /" as the delimiters. 
			StringTokenizer tokenizedExpression = new StringTokenizer(splitExpression, "+-*/)(", true);
			
			//Add each tokenized element from the tokenizedExpression, back into a string
			
			splitExpression = "";
			
		     while (tokenizedExpression.hasMoreTokens())
		     {
		    	 String nxtToken = tokenizedExpression.nextToken();
		    	 
		    	 splitExpression += nxtToken + " ";	 
		     }
		     
		     //store each individual token from the formatted string into an array
		     String[] tokens = splitExpression.split("\\s+");

			return tokens;
		}
		
		
		
		/**
		 * Reads in a postfix expression, and constructs the binary expression tree using a Stack.
		 * Once the tree is constructed, it translates it into infix form, and also outputs its three address code into a file.  
		 * 
		 * @param expression the string containing the postfix expression to be translated
		 * @return the infix expression that has been translated from postfix form. 
		 * @throws RuntimeException 
		 */
		public String constructTree(String expression) throws RuntimeException
		{			
			// Each time this method is called, make sure we start off with a new operand stack 
			operandStack = new Stack<Node>();

			// Will hold the final result of the expression that is evaluated. 
			String result;   

			// Separate the contents of the postfix expression being read in, and store each element into an String[] array. 
			String[] expressionReadIn = postfixExpressionDilimeter(expression);

			
				//iterate through the tokens in the expression to format it into infix form
		       for(int i = 0 ; i < expressionReadIn.length; i++)
		       {
		    	 //get the next token
		    	 String nxtToken = expressionReadIn[i];	 

		    	 
		    	 //If it is an operand, then push it onto the operand stack.
		    	 if(isInteger(nxtToken))
		    	 {
		    		 added = new OperandNode(nxtToken);
		    		 operandStack.push(added);
		    	 } 	
			     //else if it is an operator
			     else if(isOperator(nxtToken))
			     {
			    	 added = new OperatorNode(nxtToken);
			    	 
		    		 //pop two operands
		    		 
		    		 Node y; // Will hold the first operand that is popped from the operandStack
		    		 Node x; // Will hold the second operand that is popped from the operandStack
		    		 
		    		 y = operandStack.pop();
		    		 x = operandStack.pop();
					 
		    		 added.right = y;
		    		 added.left = x;		    		
		 
		    		 //push the sub expression back onto the operand stack
		    		 operandStack.push(added);	 

			     }
		    	 // otherwise, if it's not an operator or operand, then it is an invalid token
			     else
			     {
					JOptionPane.showMessageDialog(null, "Invalid token " + nxtToken);

			    	 throw new RuntimeException();
			     }
		     }

			 result = operandStack.peek().toString();
			  
			 //Generate the Three Address Code, using the constructed binary expression tree  
			 generateThreeAddressCode(operandStack.peek());
			 
			 return result;
		}
		

		
		/**
		 * Generates the Three Address code of a binary expression tree using a post order traversal and stack, and outputs the code into a file.  
		 * 
		 * @param node the node holding the binary expression tree
		 */
		public void generateThreeAddressCode(Node node) 
	    {
	        Stack<Node> S = new Stack<Node>();
	      
			PrintWriter outPut;
			
			int registerCounter = 0; // Will keep track of what register the current calculation of the expression will be held in, during the traversal.  
			String threeAddress = ""; // will hold the correctly formatted three address code of the current calculation that is being performed in the traversal. 

			try {
				// It will print out the three address code of the expression into a file named ThreeAddressCode.txt
				outPut = new PrintWriter("ThreeAddressCode.txt", "UTF-8");

		        // Check for empty tree, and return nothing if so. 
		        if (node == null)
		        {
		        }
		        
		        // Otherwise, push the tree node that will be traversed into the stack
		        S.push(node);
		        
		        Node prev = null; // It will keep track of the previous node in the expression tree, during the traversal. 

		        
		        // While the stack is not empty, do a post order traversal of the tree to generate the three address code 
		        while (!S.isEmpty()) 
		        {
		            Node current = S.peek(); // it will keep track of the current node that the post order traversal is on 
		  
		            //Go down the tree until a leaf node is reached, and if so process it and then pop the top of the stack stack. Otherwise keep moving down 
		            if (prev == null || prev.left == current || prev.right == current) 
		            {
		                if (current.left != null)
		                {
		                    S.push(current.left);   
		                }
		                else if (current.right != null)
		                {
		                    S.push(current.right);
		                }
		                // if both the left and right child of the current node is null, then you have reached a leaf node. 
		                else
		                {
		                	// If the current node is a left child, then store its data into its parent nodes .left section. 
		                	if(prev.left == current )
		                	{
		                		prev.left = S.pop();	                		
		                	}
		                	// If the current node is a right child, then store its data into its parent nodes .right section. 
		                	else if(prev.right == current )
		                	{
		                		prev.right = S.pop();                		
		                	}		         
		                }
		            } 
	                //Else, if you cannot go down left any further, then go back up the tree from the left node
		            else if (current.left == prev) 
		            {
		            	//if the child is right, then push it onto the stack
		                if (current.right != null)
		                {
		                    S.push(current.right);
		                }
		                //otherwise process parent and pop stack 
		                else
		                {
		                    S.pop();                    
		                }
		            } 
		            
	                // Else, if you cannot go down right any further, then go back up the tree from the right node
		            else if (current.right == prev) 
		            {
		            	
		            	//after coming back from right node process parent and pop stack
		                S.pop();

		                // If the node is a "+", then format the correct three address code for addition and output the line of code onto the file.
		                // Also, store the calculation in a register, and store that register into the operator nodes parent node. 
		                if(current.data.equals("+"))
		        		{
		                	// if its the first calculation that needs to be performed in the expression
		        			if(registerCounter ==0)
		        			{
		        				//store the calculations three address code
		            			threeAddress = "Add " + "R" + registerCounter + " " + current.left.data + " " + current.right.data;
		            			
		            			// If the current node is not the root node, then the stack is still not empty  
		            			if(!S.isEmpty())
		            			{
				                    if(S.peek().left == current && S.peek().left != null)
				                    {
				                    	S.peek().left.data = "R" + registerCounter;
				                    }
				                    else if(S.peek().right == current && S.peek().right != null)
				                    {
				                    	S.peek().right.data = "R" + registerCounter;
				                    }
			                    }	            			
		            			registerCounter++;		            					        				
		        			}
		        			// else is the stack is not empty
		        			else if(!S.isEmpty())
		        			{
		        			
		        			//store the calculations three address code
		        			threeAddress = "Add " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			
		        			// If the left child of the current nodes parent equals the current node, then put its calculation register in the parent node.
		                    if(S.peek().left == current )
		                    {
		                    	S.peek().left.data = "R" + registerCounter;
		                    }
		                    
		        			// If the right child of the current nodes parent equals the current node, then put its calculation register in the parent node.
		                    else if(S.peek().right == current)
		                    {
		                    	S.peek().right.data = "R" + registerCounter;
		                    }	
		        			registerCounter++;
		        			}
		        			
		        			else
		        			{
			        			threeAddress = "Add " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			}		        			
		        			outPut.println(threeAddress);
		        		}
		                
		                //The steps for displaying the three address code for the other three operators should follow the same process.
		                
		                
		                // If the node is a "-", then format the correct three address code for subtraction and output the line of code onto the file.
		                // Also, store the calculation in a register, and store that register into the operator nodes parent node. 
		        		else if(current.data.equals("-"))
		        		{
		        			if(registerCounter ==0)
		        			{
		            			threeAddress = "Sub " + "R" + registerCounter + " " + current.left.data + " " + current.right.data;
		            			
		            			if(!S.isEmpty())
		            			{
				                    if(S.peek().left == current && S.peek().left != null)
				                    {
				                    	S.peek().left.data = "R" + registerCounter;
				                    }
				                    else if(S.peek().right == current && S.peek().right != null)
				                    {
				                    	S.peek().right.data = "R" + registerCounter;
				                    }
		            			}
		            			registerCounter++;		            					        				
		        			}
		        			else if(!S.isEmpty())
		        			{
		        			
		        			threeAddress = "Sub " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			
		                    if(S.peek().left == current )
		                    {
		                    	S.peek().left.data = "R" + registerCounter;
		                    }
		                    else if(S.peek().right == current)
		                    {
		                    	S.peek().right.data = "R" + registerCounter;
		                    }	
		        			registerCounter++;
		        			}
		        			else
		        			{
			        			threeAddress = "Sub " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			}
		        			outPut.println(threeAddress);
		        		}
		                // If the node is a "*", then format the correct three address code for multiplication and output the line of code onto the file.
		                // Also, store the calculation in a register, and store that register into the operator nodes parent node.
		        		else if(current.data.equals("*"))
		        		{
		        			if(registerCounter ==0)
		        			{
		            			threeAddress = "Mul " + "R" + registerCounter + " " + current.left.data + " " + current.right.data;
		            			
		            			if(!S.isEmpty())
		            			{
				                    if(S.peek().left == current && S.peek().left != null)
				                    {
				                    	S.peek().left.data = "R" + registerCounter;
				                    }
				                    else if(S.peek().right == current && S.peek().right != null)
				                    {
				                    	S.peek().right.data = "R" + registerCounter;
				                    }
		            			}
		            			registerCounter++;		            					        				
		        			}
		        			else if(!S.isEmpty())
		        			{
		        			
		        			threeAddress = "Mul " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			
		                    if(S.peek().left == current )
		                    {
		                    	S.peek().left.data = "R" + registerCounter;
		                    }
		                    else if(S.peek().right == current)
		                    {
		                    	S.peek().right.data = "R" + registerCounter;
		                    }	
		        			registerCounter++;
		        			}
		        			else
		        			{
			        			threeAddress = "Mul " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			}
		        			outPut.println(threeAddress);
		        		}
		                // If the node is a "-", then format the correct three address code for division and output the line of code onto the file.
		                // Also, store the calculation in a register, and store that register into the operator nodes parent node.
		        		else if(current.data.equals("/"))
		        		{
		        			if(registerCounter ==0)
		        			{
		            			threeAddress = "Div " + "R" + registerCounter + " " + current.left.data + " " + current.right.data;
		            			
		            			if(!S.isEmpty())
		            			{
				                    if(S.peek().left == current && S.peek().left != null)
				                    {
				                    	S.peek().left.data = "R" + registerCounter;
				                    }
				                    else if(S.peek().right == current && S.peek().right != null)
				                    {
				                    	S.peek().right.data = "R" + registerCounter;
				                    }
		            			}
		            			registerCounter++;		            					        				
		        			}
		        			else if(!S.isEmpty())
		        			{
		        			
		        			threeAddress = "Div " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			
		                    if(S.peek().left == current )
		                    {
		                    	S.peek().left.data = "R" + registerCounter;
		                    }
		                    else if(S.peek().right == current)
		                    {
		                    	S.peek().right.data = "R" + registerCounter;
		                    }	
		        			registerCounter++;
		        			}
		        			else
		        			{
			        			threeAddress = "Div " + "R" + registerCounter + " " +  current.left.data + " " + current.right.data;
		        			}
		        			outPut.println(threeAddress);
		        		}
		            }
		  
		            prev = current;
		        }
		        outPut.close();  
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }      
}
