/**
* A subclass of the Exception class, which will be used if there is an NumberFormatExpression.
* For example, if there is an improperly formatted fraction such as 3/4/8, then it will cause a NumberFormatExpression to be thrown
*     
* @author Nabeel Hussain 
*/
@SuppressWarnings("serial")
public class NumberFormatExpression extends Exception
{
	/**
	 * no-arg Constructor
	 */
	public NumberFormatExpression()
	{	
	}
	
	/**
	 * Constructor that will take in a message, which will be displayed if NumberFormatExpression is thrown.
	 * 
	 *  @param message the error message that will be shown if the exception is thrown
	 */
	public NumberFormatExpression(String message)
	{	
		super(message);	
	}


}
