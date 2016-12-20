import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * This class will be used to test if the ExpressionTree class and methods work correctly and produce the right output.    
 * 
 * @author Nabeel Hussain
 */
public class ExpressionTreeTest {

	ExpressionTree tree;
	
	@Before
	public void setUp() throws Exception {
		
		tree = new ExpressionTree();
	}

	@After
	public void tearDown() throws Exception {
		
		tree = null;
	}
	
	// Tests a normal postfix expression. Base case. 
	@Test
	public void postfixExpressionConverstionTest1() {
		
		String expression = "2 3 + 1 -";
		
		String result = tree.constructTree(expression);
				
		assertEquals("( ( 2 + 3 ) - 1 )" ,result);
	}
	
	
	//Tests expression with only one operator and two leaf nodes. 
	@Test
	public void postfixExpressionConverstionTest2() {
		
		String expression = "2 3+";
		
		String result = tree.constructTree(expression);
		
		assertEquals("( 2 + 3 )" ,result);
		
		File file = new File("ThreeAddressCode.txt");
		Scanner threeAddresCodeFile;
		
		try {
			threeAddresCodeFile = new Scanner(file);
			
			ArrayList<String> threeaddresscode = new ArrayList<String>();
			
			while (threeAddresCodeFile.hasNext())
			{			
				threeaddresscode.add(threeAddresCodeFile.nextLine());
			}
			
			assertEquals("Add R0 2 3", threeaddresscode.get(0));   // The first line of the Three Address code of the above expression"
			
			threeAddresCodeFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Tests expression with a blank space before the expression, and random spacing in between operators and integers.
	// The expression also contains all of the 4 operators +-*/
	@Test
	public void postfixExpressionConverstionTest3() {
		
		String expression = "7 2 5 +3 2*+ 1- /";
		
		String result = tree.constructTree(expression);
		
		assertEquals("( 7 / ( ( ( 2 + 5 ) + ( 3 * 2 ) ) - 1 ) )" ,result);
	}
	
	
	
	//Tests expression with a blank space after the end of the expression
	@Test
	public void postfixExpressionConverstionTest4() {
		
		String expression = "72 5 +32*1- ";
		
		String result = tree.constructTree(expression);
		
		assertEquals("( ( ( 72 + 5 ) * 32 ) - 1 )" ,result);
	}
	
	
	
	//Tests whether the three address generator properly outputs the correct code in the specified output file 
	@Test
	public void postfixExpressionConverstionTest5() {
		
		String expression = "7 2 5 +3 2*+ 1- *";
		
		String result = tree.constructTree(expression);
		
		assertEquals("( 7 * ( ( ( 2 + 5 ) + ( 3 * 2 ) ) - 1 ) )" ,result);
		
		File file = new File("ThreeAddressCode.txt");
		Scanner threeAddresCodeFile;
		
		try {
			threeAddresCodeFile = new Scanner(file);
			
			ArrayList<String> threeaddresscode = new ArrayList<String>();
			
			while (threeAddresCodeFile.hasNext())
			{			
				threeaddresscode.add(threeAddresCodeFile.nextLine());
			}
			
			assertEquals("Add R0 2 5", threeaddresscode.get(0));   // The first line of the Three Address code of the above expression"
			assertEquals("Mul R1 3 2", threeaddresscode.get(1));   // The first line of the Three Address code of the above expression"
			assertEquals("Add R2 R0 R1", threeaddresscode.get(2));   // The first line of the Three Address code of the above expression"
			assertEquals("Sub R3 R2 1", threeaddresscode.get(3));   // The first line of the Three Address code of the above expression"
			assertEquals("Mul R4 7 R3", threeaddresscode.get(4));   // The first line of the Three Address code of the above expression"

			threeAddresCodeFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Tests whether a RuntimeException is thrown if there is an invalid token in the expression. 
	@Test
	public void postfixExpressionConverstionTest6() {
		
		String expression = "2 3 &";
		try
		{			
			String result = tree.constructTree(expression);
		}
		catch(RuntimeException e)
		{
			assertTrue("Successfully threw a RuntimeException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides DivideByZeroException",false);
		}
	}

}
