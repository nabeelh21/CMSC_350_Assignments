import static org.junit.Assert.*;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeManagerTest
{
	private EmployeeManager empMgr;
	private Employee [] employee;
	private File inputFile, outputFile;
	
	@Before
	public void setUp() throws Exception {
		
		 empMgr = new EmployeeManager();
		 
		 // Create an initial list of 5 employees to test on, and then using an instance of the EmployeeManager() class,
		 // add these employees to the company employee list. 
		 employee = new Employee[5];
		  
		 employee[0] = new Employee("Nabeel", "Hussain", "Manager",30.50, 10, 26, "Male");
		 employee[1] = new Employee("John", "Wall", "Engineer",30.50, 10, 26, "Male");
		 employee[2] = new Employee("Brad", "Beal", "Sales",30.50, 10, 26, "Male");
		 employee[3] = new Employee("Matt", "Barker", "Secretary",30.50, 10, 26, "Male");
		 employee[4] = new Employee("Alice", "Eve", "Engineer",30.50, 10, 26, "Female");	
		 
		 
		 
	 	empMgr.addEmployee(employee[0]);
		empMgr.addEmployee(employee[1]);
		empMgr.addEmployee(employee[2]);
		empMgr.addEmployee(employee[3]);
		empMgr.addEmployee(employee[4]);
	}

	@After
	public void tearDown() throws Exception {
		empMgr = null;
		employee = null;
	}
	
	// test the addEmployee method to make sure that the employees are correctly being added into the list.  
	@Test
	public void testAddEmployee()
	{
		assertEquals("Nabeel", empMgr.employees().get(0).first);
		assertEquals("John", empMgr.employees().get(1).first);
		assertEquals("Brad", empMgr.employees().get(2).first);
		assertEquals("Matt", empMgr.employees().get(3).first);
		assertEquals("Alice", empMgr.employees().get(4).first);	
		
		assertEquals(5, empMgr.companySize(),.001);	
		
		empMgr.addEmployee(new Employee("Kim", "Lee", "Manager",40.00, 3, 35, "Female"));
		assertEquals("Kim", empMgr.employees().get(5).first);	
	}
	
	
	// test the readFile method to make sure that its correctly reading the contents of an employee info txt file correctly,
	// and extracting the info of each employee within each line.  
	@Test
	public void testReadFile() {
		
		 EmployeeManager mgr = new EmployeeManager();
		 
			inputFile = new File("TestReadFile.txt");			
			
			mgr.readFile(inputFile);

			
			assertEquals("Marc", mgr.employees().get(0).first); // The first employee in the TestInput.txt file should be "Marc"
			assertEquals("Sean", mgr.employees().get(1).first); // The second employee in the TestInput.txt file should be "Sean"

	}

	// Test if the writeFile method correctly writes the employee information into a txt file. 
	@Test
	public void testWriteFile() {
		
		 EmployeeManager mgr = new EmployeeManager();
		 
		 Employee emp1 = new Employee("Bill", "Roberts", "Manager",30.50, 10, 26, "Male"); 
		 Employee emp2 = new Employee("Austin", "Smith", "Engineer",8.25, 5, 50, "Male"); 
		  
		 mgr.addEmployee(emp1);
		 mgr.addEmployee(emp2);

	
			outputFile = new File("TestWriteFile.txt");
			
			mgr.writeFile(outputFile);
			
			
			mgr.readFile(outputFile);

			assertEquals("Bill", mgr.employees().get(0).first);   // The first employee written into the Testoutput.txt file should be "Bill"
			assertEquals("Austin", mgr.employees().get(1).first); // The first employee written into the Testoutput.txt file should be "Austin"

	}
	
	// Test if the MakeRandom method correctly is adding the correct number of employees into the list 
	@Test
	public void testMakeRandom()
	{
		 EmployeeManager mgr = new EmployeeManager();
		 
		 Employee emp1 = new Employee("Marc", "Jacos", "Manager",30.50, 10, 26, "Male");
		 Employee emp2 = new Employee("Sean", "Williams", "Engineer",8.25, 5, 50, "Male");
		 
		 mgr.addEmployee(emp1);
		 mgr.addEmployee(emp2);
		 
		 // create 50 new random employees
		 mgr.makeRandom(50);
		
		// After we have added 50 random employees, the size of the list should be 52, which includes the two employees that were initially added. 		 
		 assertEquals(52, mgr.companySize(),.001); 
	}

}
