import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * This class will test whether my LinkedListUMUC.java class correctly creates a doubly linked list and properly locates and removes different elements. 
 * 
 * @author Nabeel Hussain
 */

public class LinkedListUMUCTest {
	
    LinkedListUMUC<Integer> mgr; // Create a double linked list of type Integer. 

	@Before
	public void setUp() throws Exception
	{
		
		mgr = new LinkedListUMUC<>();	
	}

	@After
	public void tearDown() throws Exception
	{
		mgr = null;
	}

	@Test
	public void testLinkedList()
	{
		// Insert 8 numbers into the linked list, with each new number being added to the front of the list. 
		mgr.insertHead(10);
		mgr.insertHead(12);
		mgr.insertHead(16);
		mgr.insertTail(15);
		mgr.insertTail(53);
		mgr.insertHead(30);
		mgr.insertHead(56);
		mgr.insertHead(8);
		assertEquals("8  56  30  16  12  10  15  53", mgr.toString()); //the list should be printed in a string with space delimiters. 

		// Insert 2 new number, with each new number being added to the end of the list. 
		mgr.insertTail(17);
		mgr.insertTail(21);
		assertEquals("8  56  30  16  12  10  15  53  17  21", mgr.toString()); //the list should be printed with the two new numbers added. 

		mgr.removeTail(); // remove the last number in the list
		assertEquals("8  56  30  16  12  10  15  53  17", mgr.toString()); //the list should be printed again with the last number removed. 

		
        mgr.removeHead(); // remove the first number in the list.
		assertEquals("56  30  16  12  10  15  53  17", mgr.toString()); //the list should be printed again with the last number removed. 

		assertEquals(null,  mgr.removeElementAt(9));  //there is currently only 8 elements in the list, so removing an element at position 9 will not be valid. 
		 
		assertEquals(null,  mgr.peekElementAt(0)); //element 0 is the dummy node, so there will not be any elements in that position.  
	}

}
