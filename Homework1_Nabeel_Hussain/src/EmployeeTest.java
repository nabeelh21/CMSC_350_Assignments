import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	
	Employee employee;

	@Before
	public void setUp() throws Exception {
		employee = new Employee("Nabeel", "Hussain", "Manager",30.50, 10, 26, "Male"); 
	}

	@After
	public void tearDown() throws Exception
	{
		employee = null;
	}

	@Test
	public void testEmployee()
	{
		assertEquals("Nabeel", employee.first);
		assertEquals("Hussain", employee.last);
		assertEquals("Manager", employee.position);
		assertEquals(30.50, employee.pay, .001);
		assertEquals(10, employee.years, .001);
		assertEquals(26, employee.age, .001);
		assertEquals("Male", employee.gender);
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Nabeel Hussain Manager 30.50 10 26 Male", employee.toString());
	}
}
