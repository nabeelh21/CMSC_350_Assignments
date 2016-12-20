/**
 * This is the main class that will implement a GUI application showing the result of a postfix expression represented in infix form.
 * It will also output the expressions Three Address code into a file.  
 * 
 * Name: Nabeel Hussain
 * Class: CMSC 350
 * Professor: Didier Vergamini
 * Project 2
 * Date: 11/18/2016
 * 
 * @author Nabeel Hussain
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class ExpressionTreeGUI extends JFrame implements ActionListener
{
	
	ExpressionTree tree = new ExpressionTree();

	/***
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		new ExpressionTreeGUI();
	}

	public ExpressionTreeGUI() throws IOException
	{
	    // Creates the label which will be used to show where the user can enter the postfix expression they want represented 
	    JLabel enterPostfixExpressionLabel = new JLabel("Enter Postfix Expression");
		
        // Create a top panel, which will be used to contain the TextField where the user will enter the expression they want represented.  
	    JPanel topPanel = new JPanel();

	    // Creates the textfield which will hold the postfix expression the user wants represented. 
		JTextField postfixExpressionTextField = new JTextField();
		postfixExpressionTextField.setPreferredSize(new Dimension(250, 25));	    
	    
	    //Add the JLabel and JTextField for entering an expression into the top panel
	    topPanel.add(enterPostfixExpressionLabel, BorderLayout.EAST);
	    topPanel.add(postfixExpressionTextField, BorderLayout.EAST);

	    
	    // Will show the text used for displaying where the postfix expression will be represented in infix form. 
	    JLabel infixExpressionLabel = new JLabel("Infix Expression");
		
		// Create a bottom panel, which will be used to contain the TextField containing the evaluated result of the postfix expression.  
	    JPanel bottomPanel = new JPanel();

	    // Creates the textfield which will hold the result of the postfix expression the user wants converted into infix form.
	    JTextField expressionResultTextField = new JTextField();
		expressionResultTextField.setPreferredSize(new Dimension(250, 25));
		expressionResultTextField.setEditable ( false ); // set textArea non-editable

	    //Add the JLabel and JTextField for displaying the result into the bottom panel
	    bottomPanel.add(infixExpressionLabel, BorderLayout.EAST);
	    bottomPanel.add(expressionResultTextField, BorderLayout.EAST);
	    
	    

        // Create a button panel, which will be used to contain the "Construct Tree" button.  
	    JPanel buttonPane = new JPanel();

		// Button that will be used to create a tree of the expression the user enters
		JButton constructTreeButton = new JButton("Construct Tree");
		
		// Event handler for when the user clicks on the "Construct Tree" button, which will evaluate the expression the user inputs.  
		constructTreeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				//Clear the previous result in the result textfield, if there is any.
				expressionResultTextField.setText("");
				
				// Extract the postfix expression from the textfield that the user inputs, and place it into a String.  
				String postfixExpression = postfixExpressionTextField.getText();
				
				// Will hold the infix representation of the postfix expression entered. 
				String result = "";
				
				
				try
				{
					//call the constructTree button from the ExpressionTree class to build the tree, generate its infix expression, and output the three address code into a file. 
					result = tree.constructTree(postfixExpression);
					
					// Display the infix expression onto the result textfield of the GUI.
					expressionResultTextField.setText(result);
				}
				catch (RuntimeException e)
				{
					
					e.printStackTrace();
				}	
			}
		});
		
		buttonPane.add(constructTreeButton, BorderLayout.EAST);
		
		
		// Create a frame that will be used to properly display all the components in the GUI 
	    JFrame displayFrame = new JFrame ();
	    displayFrame.setTitle("Three Adddress Generator");
	    
	    //display the topPanel("Enter Postfix Expression" section) on the top, followed by the construct tree button in the center,
	    //and the bottomPanel ("Infix Expression" section) on the bottom.
	    displayFrame.add(topPanel, BorderLayout.NORTH);
	    displayFrame.add(buttonPane, BorderLayout.CENTER);
	    displayFrame.add(bottomPanel, BorderLayout.SOUTH);


	    displayFrame.pack ();
	    displayFrame.setLocationRelativeTo(null);
	    displayFrame.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
