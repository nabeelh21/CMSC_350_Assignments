import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This is the main class that will implement a GUI application showing the results of sorting a BST in ascending and descending order. 
 * 
 * Name: Nabeel Hussain
 * Class: CMSC 350
 * Professor: Didier Vergamini
 * Project 3
 * Date: 12/02/2016
 * 
 * @author Nabeel Hussain
 */

@SuppressWarnings("serial")
public class BinarySearchTreeSortGUI extends JFrame implements ActionListener
{
	// Create an instance of the BinarySearchTree, that will be used to create the tree from the contents of the original list.
	@SuppressWarnings("rawtypes")
	private BinarySearchTree tree;
	
	private String sortedList = "";
	
	/***
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		new BinarySearchTreeSortGUI();
	}

	public BinarySearchTreeSortGUI() throws IOException
	{
	    // Creates the label which will be used to show where the user can enter the original list that needs to be sorted 
	    JLabel originalListLabel = new JLabel("Original List");
	      
        // Create a panel, which will be used to contain the TextField where the user will enter the original list that needs to be sorted.   
	    JPanel originalListPanel = new JPanel();

	    // Creates the textfield which will hold the original list the user wants sorted. 
		JTextField originalListTextField = new JTextField();
		originalListTextField.setPreferredSize(new Dimension(280, 25));	    
	    
	    //Add the JLabel and JTextField for entering the original list that needs to be sorted 
		originalListPanel.add(originalListLabel, BorderLayout.EAST);
		originalListPanel.add(originalListTextField, BorderLayout.EAST);

	    
	    // Creates the label which will be used to show where the sorted list will be displayed.  
	    JLabel sortedListLabel = new JLabel("Sorted List");
        // Create a panel, which will be used to contain the TextField containing the sorted list.    
	    JPanel sortedListPanel = new JPanel();

	    // Creates the textfield which will hold the sorted list.  
		JTextField sortedListTextField = new JTextField();
		sortedListTextField.setPreferredSize(new Dimension(280, 25));	    
		sortedListTextField.setEditable (false ); // set textArea non-editable

	    //Add the JLabel and JTextField for displaying the sorted list. 
		sortedListPanel.add(sortedListLabel, BorderLayout.EAST);
		sortedListPanel.add(sortedListTextField, BorderLayout.EAST);
	    
	    

        // Create a button panel, which will be used to contain the "Perform Sort" button.  
	    JPanel buttonPane = new JPanel();

		// Button that will be used to perform the sort function when clicked
		JButton performSortButton = new JButton("Perform Sort");
		
		buttonPane.add(performSortButton, BorderLayout.EAST);
		

		
        // Create a radio button panel, which will be used to contain the radio buttons for selecting the Ascending and Descending options to sort the list. 
	    JPanel radioButton1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    radioButton1Panel.setBorder ( new TitledBorder ( new EtchedBorder (), "Sort Order" ) );
	    radioButton1Panel.setPreferredSize(new Dimension(220, 80));
	    
	    // Create the radio button panel components, which include the Ascending and Descending radio button options
	    JRadioButton ascendingButton = new JRadioButton("Ascending");
	    ascendingButton.setSelected(true);
	    JRadioButton descendingButton = new JRadioButton("Descending");
	    
	    // Create a container that will hold the Ascending and Descending radio button options in one column and separate rows. 
	    Container sortOrderRadioButtons = new Container();
	    sortOrderRadioButtons.setLayout(new BoxLayout(sortOrderRadioButtons, BoxLayout.Y_AXIS));
	    sortOrderRadioButtons.add(ascendingButton);
	    sortOrderRadioButtons.add(descendingButton);
	    
	    
	    //Add the sortOrderRadioButtons into the radioButton1Panel
	    radioButton1Panel.add(sortOrderRadioButtons);
	    
	    //Group the radio buttons.
	    ButtonGroup group1 = new ButtonGroup();
	    group1.add(ascendingButton);
	    group1.add(descendingButton);
		
		
        // Create a radio button panel, which will be used to contain the radio buttons for selecting the Integer and Fraction numeric type options to sort the list.  
	    JPanel radioButton2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    radioButton2Panel.setBorder ( new TitledBorder ( new EtchedBorder (), "Numeric Type" ) );
	    radioButton2Panel.setPreferredSize(new Dimension(220, 80));

	    // Create the radio button panel components, which include the Integer and Fraction radio button options
	    JRadioButton integerButton = new JRadioButton("Integer");
	    integerButton.setSelected(true);
	    JRadioButton fractionButton = new JRadioButton("Fraction");
	    
	    
	    // Create a container that will hold the Integer and Fraction radio button options in one column and separate rows. 
	    Container numericTypeRadioButtons = new Container();
	    numericTypeRadioButtons.setLayout(new BoxLayout(numericTypeRadioButtons, BoxLayout.Y_AXIS));
	    numericTypeRadioButtons.add(integerButton);
	    numericTypeRadioButtons.add(fractionButton);
	    
	    //Add the numericTypeRadioButtons into the radioButton1Panel
	    radioButton2Panel.add(numericTypeRadioButtons);

	    
	    //Group the radio buttons.
	    ButtonGroup group2 = new ButtonGroup();
	    group2.add(integerButton);
	    group2.add(fractionButton);
		
	    
		// Panel that will contain both radio button sections created for the GUI
		JPanel radioButtonSectionPanel = new JPanel();	
	    // Arrange both the radio button sections side by side in the same row. 
	    radioButtonSectionPanel.add(radioButton1Panel, BorderLayout.WEST );
	    radioButtonSectionPanel.add(radioButton2Panel, BorderLayout.EAST);

		
	    
		// Event handler for when the user clicks on the "Perform Sort" button, which will sort the original list the user inputs.  
		performSortButton.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent ae)
			{
				// extract the list entered by the user from the originalListTextField
				String originalList = originalListTextField.getText();
				
				// initialize the sortedListTextField to be blank
				sortedListTextField.setText("");
				
				// if the originalListTextField is blank, then prompt the user to enter a list to be sorted. 
				if(originalList.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter the data to sort");
				}
								
				// Separate the integers or fractions from the original list, into an array, using the spaces as delimiters. 
				String data[] = originalList.split(" ");
				
				// If the Integer button is selected
				if (integerButton.isSelected())
				{
					// Use a try catch, in order to catch any exceptions thrown for non integer inputs.
					try
					{
						//Initialize the data type of the BinarySearchTree class to be an Integer
						tree = new BinarySearchTree<Integer>();
						
						// convert each element in the original list String data[] array into Integer values, and place them into a new Integer array.
						Integer integerArray[] = new Integer[data.length];
						
						for (int i = 0; i < data.length; i++)
						{								
							// If the element cannot be converted into an Integer, then it will throw an exception, which will be caught and a "non numeric" message will display. 
							integerArray[i] = Integer.parseInt(data[i]);
						}
						
						// Create the binary tree using the integer array extracted from the the original list. 
						TreeNode<Integer> root = tree.createBST(integerArray);
						
						
						// If the ascending radio button is selected, then sort in ascending order
						if (ascendingButton.isSelected())
						{
							sortedList = tree.sortTreeAscending(root);
						}
						// else if the descending radio button is selected, then sort in ascending order
						else if (descendingButton.isSelected())
						{
							sortedList = tree.sortTreeDescending(root);
						}
						
						// set the sorted integers list into the sortedListTextField
						sortedListTextField.setText(sortedList);
					}
					catch (Exception e)
					{
						//If a non integer input is caught while the "Integer" Numeric Type is selected, then display "Non integer Input":
						JOptionPane.showMessageDialog(null, "Non integer Input");
						
						e.printStackTrace();
					}
				}
				// otherwise, the fraction button will be selected
				else
				{
					// Use a try catch, in order to catch any exceptions thrown for non numeric inputs, when the fraction type is selected. 
					try
					{
						//initalize the data type of the BinarySearchTree class to be a Fraction
						tree = new BinarySearchTree<Fraction>();

						
						// convert each element in the original list String data[] array into Fraction data types, and place them into a new Fraction array.
						Fraction fractionArray[] = new Fraction[data.length];
						
						for (int i = 0; i < data.length; i++)
						{							
							String[] fractionElements = data[i].split("/");
							
							// If there are more than 2 elements after splitting the string fraction data[i] using "/", then it's an improperly formatted fraction.
							// There should only be one number before the "/" and one number after, to make up the fraction. 
							if(fractionElements.length > 2)
							{
								throw new NumberFormatExpression("Non numeric Input");
							}
							// If the string fraction data[i] doesn't contain a "/", then check to see if it's an integer.  
							if( !data[i].contains("/"))
							{
								// convert the string in data[i] into an integer
								// This will automatically throw an exception if the string cannot be parsed into an integer, meaning its a non-numeric number. 
						        Integer.parseInt(data[i]);

						        // Add an "/1" next to the integer, to turn the integer into a fraction form. This will make it easier to compare fractions in the compareTo method. 
						        data[i] = data[i] + "/1";
							}
							
							Fraction f = new Fraction(data[i]);
		                    
		                    // put the newly converted fraction into the fraction array
		                    fractionArray[i] = f;
						}
					
						// Create the binary tree using the fraction array extracted from the the original list. 
						TreeNode<Fraction> root = tree.createBST(fractionArray);
						
						
						// If the ascending radio button is selected, then sort in ascending order
						if (ascendingButton.isSelected())
						{
							sortedList = tree.sortTreeAscending(root);
						}
						// else if the descending radio button is selected, then sort in ascending order
						else if (descendingButton.isSelected())
						{
							sortedList = tree.sortTreeDescending(root);
						}						

						// set the sorted fractions list into the sortedListTextField
						sortedListTextField.setText(sortedList);
					}
					catch (NumberFormatExpression e)
					{
						//If there is a fraction that is improperly formatted, such as "3/4/8", then, it should cause a NumberFormatExpression to be thrown,
						//where a JOptionPane window should be displayed containing the error message "Non numeric Input."
						JOptionPane.showMessageDialog(null, "Non numeric Input");
						
						e.printStackTrace();
					}
					catch (Exception e)
					{
						//Should there be an non integer in the list, then also catch that exception and display error message "Non numeric Input."
						JOptionPane.showMessageDialog(null, "Non numeric Input");
						
						e.printStackTrace();
					}
				}
			}
		});
	    		
	    // Create a container that will hold the original list textfield, sorted list textfield, "Perform Sort" button,
	    // and the radio buttons all in one column in separate rows. 
	    Container pane = new Container();
	    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	    
	    pane.add(originalListPanel);
	    pane.add(Box.createRigidArea(new Dimension(0,35)));
	    pane.add(sortedListPanel);
	    pane.add(Box.createRigidArea(new Dimension(0,35)));
	    pane.add(buttonPane);
	    pane.add(Box.createRigidArea(new Dimension(0,35)));
	    pane.add(radioButtonSectionPanel);
	     

		// Create a frame that will be used to properly display all the components in the GUI 
	    JFrame displayFrame = new JFrame ();
	    displayFrame.setTitle("Binary Search Tree Sort");
	    displayFrame.setPreferredSize(new Dimension(466, 328));
	    displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Add the Container pane holding the contents of the GUI onto the diplayFrame, which will display it. 
	    displayFrame.add(pane);

	    displayFrame.pack ();
	    displayFrame.setLocationRelativeTo(null);
	    displayFrame.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
