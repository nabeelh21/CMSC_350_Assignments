import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Employee Manager class, which will be used to perform the functions needed by the GUI to read in files, write to files,
 * and display the proper employee info in sorted order. 
 *
 * @author Nabeel Hussain
 */
public class EmployeeManager
{
	private static java.util.Random rn = new java.util.Random ();               		// Will be used to create random employees 
	private static LinkedListUMUC <String> firstNames = new LinkedListUMUC <>(); 		// Declares a Double LinkedList to hold all the employees first names 
	private static LinkedListUMUC <String> lastNames = new LinkedListUMUC <>(); 		// Declares a Double LinkedList to hold all the employees last names
	private static LinkedListUMUC <String> employeePosition = new LinkedListUMUC <>();	// Declares a Double LinkedList to hold all the employees position titles
	private static LinkedListUMUC <String> employeeGender = new LinkedListUMUC <>();	// Declares a Double LinkedList to hold all the employees gender
    // These a Double LinkedList above will be used to generate random employee names and information when creating random instances of the Employee class. 
	
	
	// Declares a Double LinkedList to hold all the employees  
	private LinkedListUMUC<Employee> employees = new LinkedListUMUC<>(); 

	//Declares and array that will hold the employees that are randomly created in the makeRandom(int m) method
	private Employee [] emp;
	
	/**
	* Will read in a .txt file that contains the information of employees for company xyz
	* 
	* @param inFile the txt file that contains the information of employees for company xyz
	*/
	public void readFile(File inFile)
	{
		Scanner inputFile;
		
		try {
			inputFile = new Scanner(inFile);
			
			// The first line of the file is the number of elements in the file
			int numOfEmployees = Integer.parseInt(inputFile.nextLine());
			
			String first, last, position, gender;
			double wage;
			int yearsExperience, age;
			
			while (inputFile.hasNext())
			{	
				//Separate the contents of each line containing an employees info, by using the space dilimeter, and put the contents into an array
				String[] employeeData = inputFile.nextLine().split(" ");
				
				// The first content will always be the first name of an employee
				first = employeeData[0];
				firstNames.insertTail(first);
				
				// The second content will always be the last name
				last = employeeData[1];
				lastNames.insertTail(last);
				
				// The third content will always be the employees position title
				position = employeeData[2];
				employeePosition.insertTail(position);
				
				// The fourth content will always be the hourly wage
				wage = Double.parseDouble(employeeData[3]);
				
				// The fifth content will always be number of years worked at company
				yearsExperience = Integer.parseInt(employeeData[4]);
				
				// The sixth content will always be employees age
				age = Integer.parseInt(employeeData[5]);
				
				// The seventh content will always be employees gender
				gender = employeeData[6];
				employeeGender.insertTail(gender);
				
				
				// Create a new employee using the information gathered from spliting the current line of the txt file being read.
				// add it to the employees ArrayList
				addEmployee(new Employee(first, last, position, wage, yearsExperience, age, gender));
			} 
			inputFile.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Will write the employee records of a company onto a specified txt file that the user chooses.
	* @param outFile the txt file that will be written onto
	*/
	public void writeFile(File outFile)
	{
		
		// Will use the output file that is passed into this method to write the e
		PrintWriter outPut;
		
		try {
			
			outPut = new PrintWriter(outFile);
			
			String out = "";
			
			outPut.println(employees.getSize());
			//System.out.println(employees.size());
			
			for (int i = 0; i <employees.getSize(); i++)
			{
								
				outPut.println(employees.peekElementAt(i + 1).toString());
			}
			
			outPut.print(out);		
			outPut.close();

		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Will add an employee to the existing company employee list.
	 * @param x info of an employee that is being added to the company
	 */
	public void addEmployee(Employee x)
	{
		employees.insertTail(x);
	}
	
	
	/**
	 * Will return an ArrayList containing a list of all the employees .
	 * @return the ArrayList of employees
	 */
	public LinkedListUMUC<Employee> employees()
	{
		return this.employees;
	}
	
	
	
	/**
	 * Will display a running count of the number of employees in the company. 
	 * @return the number of employees currently in the company
	 */
	public int companySize()
	{
		int numEmployees = employees.getSize();
		
		return numEmployees;
	}

	
	/**
	 * Will print all the employees and their information into a String.
	 * It will then proceed to display these employees in sorted order by first name, last name, job position, pay, years worked, age, etc..
	 * This will be output into the text area box of the GUI.
	 * 
	 * @return employeeList a string containing all the employee information of company XYZ organized by different filters. 
	 */
	public String printEmployees()
	{
		String employeeList = "";
		
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";

		Employee [] x = new Employee[employees.getSize()];
		
		for(int i = 0; i < employees.getSize(); i ++)
		{
			x[i] = employees.peekElementAt(i+1);
		}
		
		for(int i = 0; i < employees.getSize(); i ++)
		{
			employeeList += employees.peekElementAt(i+1).formattedString() + "\n";
		}
		
		employeeList += "\n";
		employeeList += "\n";
		
		employeeList += "---- SORTED by Last Name -----" + "\n";
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";
		Employee.sortBy = Employee.SORTBY.LAST;
		java.util.Arrays.sort (x);
		for (Employee m: x)
		{
			employeeList += m.formattedString() + "\n";
		}
		
		employeeList += "\n";
		employeeList += "\n";
		
		employeeList += "---- SORTED by First Name -----" + "\n";
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";
		Employee.sortBy = Employee.SORTBY.FIRST;
		java.util.Arrays.sort (x);
		for (Employee m: x)
		{
			employeeList += m.formattedString() + "\n";
		}
		
		employeeList += "\n";
		employeeList += "\n";
		
		employeeList += "---- SORTED by Position -----" + "\n";
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";
		Employee.sortBy = Employee.SORTBY.POSITION;
		java.util.Arrays.sort (x);
		
		for (Employee m: x)
		{
			employeeList += m.formattedString() + "\n";
		}
		
		employeeList += "\n";
		employeeList += "\n";
		
		employeeList += "---- SORTED by Pay -----" + "\n";
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";
		Employee.sortBy = Employee.SORTBY.PAY;
		java.util.Arrays.sort (x);
		
		for (Employee m: x)
		{
			employeeList += m.formattedString() + "\n";
		}
		
		employeeList += "\n";
		employeeList += "\n";
		
		employeeList += "---- SORTED by Years Worked -----" + "\n";
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";
		Employee.sortBy = Employee.SORTBY.YEARS;
		java.util.Arrays.sort (x);
		for (Employee m: x)
		{
			employeeList += m.formattedString() + "\n";
		}
		
		employeeList += "\n";
		employeeList += "\n";
		
		employeeList += "---- SORTED by Age -----" + "\n";
		employeeList += "Employee ID" + "           " + "Last Name" + "                " + "First Name" + "                   " + "Position" + "                  " + "Hourly Pay" + "               " + "Num Years Worked" + "              " +"Age" + "                 " + "Gender" + "\n";
		employeeList += "\n";
		Employee.sortBy = Employee.SORTBY.AGE;
		java.util.Arrays.sort (x);
		
		for (Employee m: x)
		{
			employeeList += m.formattedString() + "\n";
		}
		
		return employeeList;	
	}
	
	
	/**
	 * Will create an array of N random instances of this class, where N is an integer parameter to this method
	 * 
	 * @return emp an array containing m number of randomly generated employees. 
	 */
	public Employee[] makeRandom (int m)
	{
		emp = new Employee [m];
		
		for (int i = 0; i < emp.length; i++)
		{
			emp[i] = new Employee();
			
			emp[i].first = firstNames.peekElementAt(rn.nextInt(firstNames.getSize()) +1); // Picks a random first name from the list of existing employee first names. Cannot select 0, because the linked list at position 0 is a dummy node. 
			emp[i].last = lastNames.peekElementAt(rn.nextInt(lastNames.getSize()) + 1); // Picks a random last name from the list of existing employee last names. Cannot select 0, because the linked list at position 0 is a dummy node. 
			emp[i].position = employeePosition.peekElementAt(rn.nextInt (employeePosition.getSize()) + 1); // Picks a random employee position title from the list of existing employee job titles. Cannot select 0, because the linked list at position 0 is a dummy node. 
			emp[i].pay = rn.nextFloat()* 91.50 + 8.50; //Random number between $8.50-$100 dollars an hour
			emp[i].years = rn.nextInt(51); // Random number of years between 0-50 for years worked. 
			emp[i].age = rn.nextInt(50) + 16;  // Random number between 16-65 years of age.
			emp[i].gender = employeeGender.peekElementAt(rn.nextInt(employeeGender.getSize()) + 1); // Picks a random employee gender 
			
			employees.insertTail(emp[i]);
		} // end for each employee to instantiate
		return emp;
	} // end method makeRandom

}
