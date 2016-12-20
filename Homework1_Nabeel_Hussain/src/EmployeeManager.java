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
	private static java.util.Random rn = new java.util.Random ();               // Will be used to create random employees 
	private static ArrayList <String> firstNames = new ArrayList <>(); 			// Declares ArrayList to hold all the employees first names 
	private static ArrayList <String> lastNames = new ArrayList <>(); 			// Declares ArrayList to hold all the employees last names
	private static ArrayList <String> employeePosition = new ArrayList <>();	// Declares ArrayList to hold all the employees position titles
	private static ArrayList <String> employeeGender = new ArrayList <>();		// Declares ArrayList to hold all the employees gender
    // These ArrayLists above will be used to generate random employee names and information when creating random instances of the Employee class. 
	
	
	// Declares ArrayList to hold all the employees  
	private ArrayList<Employee> employees = new ArrayList<>(); 

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
				firstNames.add(first);
				
				// The second content will always be the last name
				last = employeeData[1];
				lastNames.add(last);
				
				// The third content will always be the employees position title
				position = employeeData[2];
				employeePosition.add(position);
				
				// The fourth content will always be the hourly wage
				wage = Double.parseDouble(employeeData[3]);
				
				// The fifth content will always be number of years worked at company
				yearsExperience = Integer.parseInt(employeeData[4]);
				
				// The sixth content will always be employees age
				age = Integer.parseInt(employeeData[5]);
				
				// The seventh content will always be employees gender
				gender = employeeData[6];
				employeeGender.add(gender);
				
				
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
			
			outPut.println(employees.size());
			//System.out.println(employees.size());
			
			for (int i = 0; i <employees.size(); i++)
			{
								
				outPut.println(employees.get(i).toString());
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
		employees.add(x);
	}
	
	
	/**
	 * Will return an ArrayList containing a list of all the employees .
	 * @return the ArrayList of employees
	 */
	public ArrayList<Employee> employees()
	{
		return this.employees;
	}
	
	
	
	/**
	 * Will display a running count of the number of employees in the company. 
	 * @return the number of employees currently in the company
	 */
	public int companySize()
	{
		int numEmployees = employees.size();
		
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

		Employee [] x = new Employee[employees.size()];
		
		for(int i = 0; i < employees.size(); i ++)
		{
			x[i] = employees.get(i);
		}
		
		for(int i = 0; i < employees.size(); i ++)
		{
			employeeList += employees.get(i).formattedString() + "\n";
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
			
			emp[i].first = firstNames.get(rn.nextInt(firstNames.size()));
			emp[i].last = lastNames.get(rn.nextInt(lastNames.size()));
			emp[i].position = employeePosition.get(rn.nextInt (employeePosition.size()));
			emp[i].pay = rn.nextFloat()* 91.50 + 8.50; //Random number between $8.50-$100 dollars an hour
			emp[i].years = rn.nextInt(51) + 16; // Random number of years between 0-50
			emp[i].age = rn.nextInt(50) + 16;  // Random number between 16-65 years of age.
			emp[i].gender = employeeGender.get(rn.nextInt(employeeGender.size()));
			
			employees.add(emp[i]);
		} // end for each employee to instantiate
		return emp;
	} // end method makeRandom

}
