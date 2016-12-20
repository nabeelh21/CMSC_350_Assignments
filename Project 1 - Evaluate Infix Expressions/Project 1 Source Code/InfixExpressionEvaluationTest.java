import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * This class will test if the methods in the InfixExpressionEvaluation properly work. 
 * 
 * @author Nabeel Hussain
 */
public class InfixExpressionEvaluationTest {
	
	
	InfixExpressionEvaluation expression;
	
	@Before
	public void setUp() throws Exception {
		
		expression = new InfixExpressionEvaluation();

	}

	@After
	public void tearDown() throws Exception {
		
		expression = null;
	}
	
	// Test whether the evaluateExpression method accurately solves the expression and returns the correct answer. 
	@Test
	public void testEvaluateExpression() throws DivideByZeroException
	{
		String expression1 = "(1+2+3+4)";  										// Test 1
		assertEquals(10, expression.evaluateExpression(expression1), .001);
			
		
		String expression2 = " (2 + 3*5) - 8/5 * (5 - 2)";  					// Test 2
		assertEquals(14, expression.evaluateExpression(expression2), .001);

				
		
		String expression3 = "10-5*10/5 ";  								    // Test 3
		assertEquals(0, expression.evaluateExpression(expression3), .001);
		
			
		
		String expression4 = "( ((100/2)/5 +2) - (5* 2 ) )";  					// Test 4
		assertEquals(2, expression.evaluateExpression(expression4), .001);
		
			
		
		String expression5 = " (2 + 3    *5) - 8/0 * (5     - 2)      ";  		// Test 5
		try
		{
			expression.evaluateExpression(expression5);
		}
		catch(DivideByZeroException e)
		{
			assertTrue("Successfully threw a DivideByZeroException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides DivideByZeroException",false);
		}

	}
	
	
	// Test whether the Calculate() method performs the actual calculations of + - * or / correctly. 
	@Test
	public void testCalculate() throws DivideByZeroException
	{
		// test whether the addition "+" calculation works. 
		int calculation1 = expression.calculate(2, 2, "+");		   // 2 + 2 = 4
		assertEquals(4, calculation1, .001);
		
		// test whether the subtraction "-" calculation works. 
		int calculation2 = expression.calculate(50, 40, "-");	   // 50 - 40 = 10
		assertEquals(10, calculation2, .001);
		
		// test whether the multiplication "*" calculation works. 
		int calculation3 = expression.calculate(9, 5, "*");		   // 9 * 5 = 45
		assertEquals(45, calculation3, .001);
		
		// test whether the division "/" calculation works. 
		int calculation4 = expression.calculate(100, 4, "/");	   // 100 / 4 = 25
		assertEquals(25, calculation4, .001);
		
		// test whether the divide by 0 exception is catched. 
		try
		{
			int calculation5 = expression.calculate(5, 0, "/");	   // 5/0 = throw exception
		}
		catch(DivideByZeroException e)
		{
			assertTrue("Successfully threw a DivideByZeroException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides DivideByZeroException",false);
		}	
	}
	
	
	
	// Test whether the first operator in the parameter of the HasHigherPrecedence() method, has higher or the same precedence, compared to the second
	// operator in its parameter.
	@Test
	public void testHasHigherPrecedence() throws DivideByZeroException
	{
		// test that "*" has higher or equal precedence compared to "/" 
		boolean test1 = expression.hasHigherPrecedence("*", "/");		  
		assertEquals(test1, true);
		
		// test that "*" has higher precedence compared to "+" 
		boolean test2 = expression.hasHigherPrecedence("*", "+");		  
		assertEquals(test2, true);
		
		// test that "/" has higher precedence compared to "-" 
		boolean test3 = expression.hasHigherPrecedence("/", "-");		  
		assertEquals(test3, true);
		
		// test that "+" has lower precedence compared to "*" 
		boolean test4 = expression.hasHigherPrecedence("+", "*");		  
		assertEquals(test4, false);
		
		// test that "-" has higher or equal precedence compared to "/" 
		boolean test5 = expression.hasHigherPrecedence("-", "/");		  
		assertEquals(test5, false);	
	}
}
