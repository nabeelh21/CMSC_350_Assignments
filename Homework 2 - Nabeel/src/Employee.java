/**
* This class will hold an Employees information, including their first name, last name, job title, pay, years worked at company, age, and gender.   
* @author Nabeel Hussain 
*/ 

public class Employee implements Comparable <Employee>
{	
	static SORTBY sortBy = SORTBY.LAST;
	private static int nextEMPLOYEEID = 1;
	
	String first, last, position, gender;
	double pay = 0;
	int years = 0;
	int age = 0;
	int eid = 0;
	
	// Will be used with the compareTo method in order to sort the employees by first name, last name, pay, etc...
	enum SORTBY{LAST, FIRST, POSITION, PAY, YEARS, AGE}
	
	
	/***
	 * Constructor for the employee class. Defines the different attributes required for each employee.
	 * 
	 * @param first
	 * @param last
	 * @param position
	 * @param pay
	 * @param years
	 * @param age
	 * @param gender
	 */
	public Employee(String first, String last, String position, double pay, int years, int age, String gender)
	{
		this.eid = nextEMPLOYEEID++;
		this.first = first;
		this.last = last;
		this.position = position;
		this.pay = pay;
		this.years = years;
		this.age = age;
		this.gender = gender;
	}
	
	
	/***
	 * Constructor with no parameters
	 */
	public Employee()
	{
		eid = nextEMPLOYEEID++;
	} 
	
	
	/***
	 * Will be used to sort the company employee list into whatever order that is desired.
	 * 
	 * @param x  an Employee
	 * @return the value 0 if the argument string is equal to this string; a value less than 0 if this string is lexicographically less than the string argument; and a value greater than 0 if this string is lexicographically greater than the string argument.
	 */
	public int compareTo(Employee x)
	{
		switch (sortBy)
		{
		case LAST :
			return last.compareTo (x.last);
		case FIRST :
			return first.compareTo (x.first);
		case POSITION:
			return position.compareTo (x.position);
		case PAY :
			return (pay > x.pay)? 1 : -1;
		case YEARS:
			return (years > x.years)? 1 : -1;
		case AGE :
			return (age > x.age)? 1 : -1;
	} // end switch
	return 0;
	}
	
	
	/***
	 * Will format an instance of this class for output into a file. Each piece of info for an employee will be separated by a space delimiter. 
	 */
	public String toString ()
	{	
		return first + " " + last + " " + position + " " + String.format( "%.2f", pay)+ " " + years + " " + age + " " + gender;
		
	} 
	
	
	/***
	 * Will format an instance of this class nicely for output onto the GUI Screen text area. 
	 */
	public String formattedString ()
	{	
		return String.format("%-30d%-30s%-30s%-30s %.2f %40d%30d%30s",eid, last, first, position, pay, years, age, gender);
	} 
	
	
}
	
