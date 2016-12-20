/**
* A subclass of the Exception class, which will be used if there is an DivideByZeroException(when a calculation contains a division by zero).    
* @author Nabeel Hussain 
*/
@SuppressWarnings("serial")
public class DivideByZeroException extends Exception {
	
	/**
	 * no-arg Constructor
	 */
	public DivideByZeroException()
	{	
	}
	
	/**
	 * Constructor that will take in a message, which will be displayed if DivideByZeroException is thrown.
	 * 
	 *  @param message the error message that will be shown if the exception is thrown
	 */
	public DivideByZeroException(String message)
	{	
		super(message);	
	}

}
