import java.util.ArrayList;
/**
 * This class implements a Stack data structure, which will be used to store the operands and operations for evaluating the infix expression.  
 * 
 * @author Nabeel Hussain
 */
import javax.swing.JOptionPane;
/**
 * The Stack data structure that will be used to store operators and operands, in order to evaluate an infix expression.
 * 
 * @author Nabeel Hussain
 */


public class Stack<E>{
	
	private ArrayList<E> elements;  // the data of the stack, which will hold the elements in LIFO order. 
	private int numberOfElements;   // will keep track of the number of elements currently in the ArrayList Stack. 
	private E topElement;           // the most recent element pushed onto the stack 
	
	
	/**
	 * Constructor that initializes the stack
	 */	
	public Stack()
	{
		elements = new ArrayList<E>();
		numberOfElements = 0;
	}
	
	
	/**
	 * Will check to see if the stack contains any elements. 
	 * 
	 * @return true if stack is empty, and false otherwise.
	 */	
	public boolean isEmpty()
	{
		if (numberOfElements == 0)
			return true;
		
		else
			return false;
	}			
	
	/**
	 * Will add a new element onto the top of the stack.
	 * 
	 * @param item to be added into stack
	 */
	public int getSize()
	{
		return this.numberOfElements;
	}
	
	
	/**
	 * Will add a new element onto the top of the stack.
	 * 
	 * @param item to be added into stack
	 */
	public void push(E item)
	{
		elements.add(item);
		
		numberOfElements++;
	}
	
	
	/**
	 * Will remove the top element of the stack. 
	 * 
	 * @return the top element of the stack
	 */
	public E pop() 
	{
		if (numberOfElements == 0)
		{
			JOptionPane.showMessageDialog(null, "Stack is Empty");
			
			return null;
		}
		
		else
		{
			topElement = elements.get(elements.size() - 1);
			
			elements.remove(elements.size() - 1);
			
			numberOfElements--;
			
			return topElement;	
		}	
	}
	
	/**
	 * Will show the top element of the stack, but wont remove it. 
	 * 
	 * @return the top element of the stack
	 */
	public E peek()
	{
		if (numberOfElements == 0)
		{
			JOptionPane.showMessageDialog(null, "Stack is Empty");
			return null;
		}
		
		else
		{
			topElement = elements.get(elements.size() - 1);
			
			return topElement;	
		}
	}	
}