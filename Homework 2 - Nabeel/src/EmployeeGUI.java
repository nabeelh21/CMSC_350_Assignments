/**
 * This program will implement a GUI application for reading in a file containing employee information, and sorting them based on name, age, pay, etc... 
 * 
 * Name: Nabeel Hussain
 * Class: CMSC350
 * Professor: Didier Vergamini
 * Homework 1
 * Date: 10/25/2016
 * 
 * @author Nabeel Hussain
 */


import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class EmployeeGUI extends JFrame implements ActionListener
{
	File selectedFile = null;
	EmployeeManager mgr = new EmployeeManager() ;
	

	/***
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		new EmployeeGUI();
	}

	public EmployeeGUI() throws IOException
	{
        // Create a main panel, which will be used to contain the Text area for displaying the output. 
	    JPanel mainPanel = new JPanel();
	    mainPanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Employee Information" ) );

	    // create the main panel components, which include the TextArea with a ScrollPane
	    JTextArea display = new JTextArea (40, 80);
	    JScrollPane scroll = new JScrollPane (display);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    
	    // Make sure the scroll bar is always at the top, when new instances of employees are added. 
	    DefaultCaret caret = (DefaultCaret)display.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	    
	    display.setEditable ( false ); // set textArea non-editable
	    
	    //Add Text area  and its scroll pane into the main panel
	    mainPanel.add(scroll);

	    
        // Create a panel, which will be used to contain the "Create Random Employees" button and its corresponding input field.  
	    JPanel buttonRow1 = new JPanel();
		JTextField nRandomTextField = new JTextField();
		nRandomTextField.setPreferredSize(new Dimension(35, 25));
		
		// Button that will be used to create N random instances of the employee class.
		JButton randomInstancesButton = new JButton("Create Random Employees");
		
		// Event handler for when the user clicks on the "Create Random Employees" button, which will create n random instances of employees. 
		randomInstancesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				// If there has been no file read in, then there is no pool of names and job titles that can be used to randomly create new employees. 
				if(selectedFile == null)
				{
					JOptionPane.showMessageDialog(null, "You must read in a file of employees first, in order to create new random ones.");
				}
				else if(nRandomTextField .getText().equals(" "))
				{
					
					JOptionPane.showMessageDialog(null, "Please enter the number of random employees you would like to create.");

				}
				else
				{
					//Use the number in the textfield that the user inputs as the parameter for the makeRandom method. 
					String num = nRandomTextField .getText();
					
					int number = Integer.parseInt(num);
							
									
					mgr.makeRandom(number);
					display.setText(mgr.printEmployees());
				}
			}
		});

		buttonRow1.add(nRandomTextField , BorderLayout.EAST);
		buttonRow1.add(randomInstancesButton, BorderLayout.WEST);
	    
	    
		
	    final JFileChooser fileChooser = new JFileChooser();
		final JFrame f = new JFrame("Document Viewer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
        // Create a panel, which will be used to contain the "Read File", "Save to File", "Display Employees", and "Exit" buttons
		JPanel buttonRow2 = new JPanel();
		
		JButton inputFileButton = new JButton("Read File");
		// Event handler for when the user clicks on the "Read File" button, which will read in a file containing employee information 
		inputFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int result = fileChooser.showOpenDialog(f);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					selectedFile = fileChooser.getSelectedFile();
					try {

						mgr.readFile(selectedFile);
						
						display.setText(mgr.printEmployees());	

					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		
		JButton outputFileButton = new JButton("Save to File");
		// Event handler for when the user clicks on the "Save to File" button, which will write the employee information of a company into a chosen file.
		outputFileButton .addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int result = fileChooser.showSaveDialog(f);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileChooser.getSelectedFile();
					try {

						mgr.writeFile(selectedFile);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		
		// Will exit the program, if user clicks the "Exit" button. 
		JButton exitButton = new JButton("Exit");
		exitButton .addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
			}
		});
		
		// Align all the buttons that will be used in the GUI side by side next to each other. 
		buttonRow2.add(inputFileButton, BorderLayout.EAST);
		buttonRow2.add(outputFileButton, BorderLayout.EAST);
		buttonRow2.add(exitButton, BorderLayout.EAST);
		
		
		
		// Create a frame that will be used to properly display all the components in the GUI 
	    JFrame displayFrame = new JFrame ();
	    displayFrame.setTitle("Company XYZ");
	    
	    //display the buttnRow1 panel("Create random Employees" button) on the top, followed by the text area panel in the center, and the file input/output buttons at the bottom.
	    displayFrame.add(buttonRow1, BorderLayout.NORTH);
	    displayFrame.add(mainPanel, BorderLayout.CENTER);
	    displayFrame.add(buttonRow2, BorderLayout.SOUTH );

	    displayFrame.pack ();
	    displayFrame.setLocationRelativeTo(null);
	    displayFrame.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
}
