import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * JUnit tests to check if the BST tree gets properly created, and the ascending and descending methods in the BinarySearchTree properly sorts the list.
 * 
 * @author Nabeel Hussain
 */
public class BinarySearchTreeTest
{	
	BinarySearchTree<Integer>  integerTree;
	BinarySearchTree<Fraction>  fractionTree;
	
	@Before
	public void setUp() throws Exception
	{
		integerTree = new BinarySearchTree<Integer>();

		fractionTree = new BinarySearchTree<Fraction>();
	}

	@After
	public void tearDown() throws Exception
	{
		integerTree = null;
		fractionTree = null;

	}

	@Test
	public void sortAscendingTest()
	{
		// test 1.1: Integer list to sort in ascending order
		Integer[] lisToSort1 = {1, 1, 3, 5, 9, 2, 6, 8, 3, 3};
		TreeNode<Integer> treeRoot1 = integerTree.createBST(lisToSort1);
		String integerSortedAscending = integerTree.sortTreeAscending(treeRoot1);
		assertEquals("1 1 2 3 3 3 5 6 8 9 ", integerSortedAscending);
		
		// test 2.1: Fraction List to sort in ascending order
		Fraction[] lisToSort2 = {new Fraction("1/2"), new Fraction("5/2"), new Fraction("3/2"), new Fraction("3/4"), new Fraction("1/2")};
		TreeNode<Fraction> treeRoot2 = fractionTree.createBST(lisToSort2);
		String fractionSortedAscending = fractionTree.sortTreeAscending(treeRoot2);
		assertEquals("1/2 1/2 3/4 3/2 5/2 ", fractionSortedAscending);

	}
	
	@Test
	public void sortDescendingTest()
	{
		// test 1.2: Integer list to sort in descending order
		Integer[] lisToSort1 = {1, 1, 3, 5, 9, 2, 6, 8, 3, 3};
		TreeNode<Integer>treeRoot1 = integerTree.createBST(lisToSort1);
		String integerSortedDescending = integerTree.sortTreeDescending(treeRoot1);
		assertEquals("9 8 6 5 3 3 3 2 1 1 " , integerSortedDescending);
		
		
		
		// test 2.2: Fraction List to sort in descending order
		Fraction[] lisToSort2 = {new Fraction("1/2"), new Fraction("5/2"), new Fraction("3/2"), new Fraction("3/4"), new Fraction("1/2")};
		TreeNode<Fraction>treeRoot2 = fractionTree.createBST(lisToSort2);
		String fractionSortedDescending = fractionTree.sortTreeDescending(treeRoot2);
		assertEquals("5/2 3/2 3/4 1/2 1/2 ", fractionSortedDescending);
	}
}
