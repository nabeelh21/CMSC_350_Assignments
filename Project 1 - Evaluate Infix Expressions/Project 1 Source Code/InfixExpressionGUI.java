/**
 * This is the main class that will implement a GUI application showing the result of the infix expression that was evaluated.
 * 
 * Name: Nabeel Hussain
 * Class: CMSC 350
 * Professor: Didier Vergamini
 * Project 1
 * Date: 11/4/2016
 * 
 * @author Nabeel Hussain
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class InfixExpressionGUI extends JFrame implements ActionListener
{
	// Create an instance of the InfixExpressionEvaluation class, in order to perform the evaluations of the expressions. 
	private InfixExpressionEvaluation mgr = new InfixExpressionEvaluation() ;
	
	
	/***
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		new InfixExpressionGUI();
	}

	public InfixExpressionGUI() throws IOException
	{
	    // Creates the label which will be used to show where the user can enter the expression they want evaluated in the GUI  
	    JLabel enterInfixExpressionLabel = new JLabel("Enter Infix Expression");
		
        // Create a top panel, which will be used to contain the TextField where the user will enter the expression they want evaluated.  
	    JPanel topPanel = new JPanel();

	    // Creates the textfield which will hold the expression the user wants evaluated. 
		JTextField infixExpressionTextField = new JTextField();
		infixExpressionTextField.setPreferredSize(new Dimension(250, 25));	    
	    
	    //Add the JLabel and JTextField for entering an expression into the top panel
	    topPanel.add(enterInfixExpressionLabel, BorderLayout.EAST);
	    topPanel.add(infixExpressionTextField, BorderLayout.EAST);

	    
	    // Will show the text used for displaying where the answer to the expression will be shown. 
	    JLabel resultLabel = new JLabel("Result");
		
		// Create a bottom panel, which will be used to contain the TextField containing the evaluated result of the users expression.   
	    JPanel bottomPanel = new JPanel();

	    // Creates the textfiled which will hold the answer to the expression the user wants evaluated. 
	    JTextField expressionResultTextField = new JTextField();
		expressionResultTextField.setPreferredSize(new Dimension(250, 25));
		expressionResultTextField.setEditable ( false ); // set textArea non-editable

	    //Add the JLabel and JTextField for displaying the result into the bottom panel
	    bottomPanel.add(resultLabel, BorderLayout.EAST);
	    bottomPanel.add(expressionResultTextField, BorderLayout.EAST);
	    
	    

        // Create a button panel, which will be used to contain the "Evaluate" button.  
	    JPanel buttonPane = new JPanel();

		// Button that will be used to evaluate the expression, when the user clicks on it.
		JButton evaluateButton = new JButton("Evaluate");
		
		// Event handler for when the user clicks on the "Evaluate" button, which will evaluate the expression the user inputs.  
		evaluateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				//Clear the previous result in the result textfield, if there is any.
				expressionResultTextField.setText("");
				
				// Extract the expression from the textfield that the user inputs, and place it into a String.  
				String expression = infixExpressionTextField.getText();
				
				// Will hold the answer to the expression being evaluated. 
				int result;

				try {
					result = mgr.evaluateExpression(expression);
					
					expressionResultTextField.setText(String.valueOf(result));
					
				} catch (DivideByZeroException e)
				{
					//Should the expression contain division by zero, a checked exception DivideByZero should be thrown by the method that performs the evaluation
					//and caught in the main class, where a JOptionPane window should be displayed containing an error message.
					JOptionPane.showMessageDialog(null, "Error. You cannot divide by 0.");

					e.printStackTrace();
				}
			}
		});
		
		buttonPane.add(evaluateButton, BorderLayout.EAST);
		
		
		// Create a frame that will be used to properly display all the components in the GUI 
	    JFrame displayFrame = new JFrame ();
	    displayFrame.setTitle("Infix Expression Evaluator");
	    
	    //display the topPanel("Enter Infix Expression" section) on the top, followed by the evaluate button in the center,
	    //and the bottomPanel ("Result" section) on the bottom.
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
