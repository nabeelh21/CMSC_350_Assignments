import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class will create a generic doubly linked list class using an internal node class:     
 * 
 * Name: Nabeel Hussain
 * Class: CMSC 350
 * Professor: Didier Vergamini
 * Homework 2
 * Date: 11/12/2016
 * 
 * @author Nabeel Hussain
 */


public class LinkedListUMUC <T>  {
	
	protected NodeUMUC<T> head; // The head node, which will be used to point to the first element in the Linked List. 
	protected NodeUMUC<T> tail; // The tail node, which will be used to point to the last element in the Linked List.
	protected int size; 	// The number of elements in the linked list.
	
	
	/**
	 * Internal node class 
	 */
	@SuppressWarnings("hiding")
	public class NodeUMUC<T>
	{
		protected T data;
		protected NodeUMUC<T> next;	
		protected NodeUMUC<T> prev;
		
		public NodeUMUC()
		{

		}
	}
	
	
	/**
	 * Constructor, which begins the list as null     
	 */
	public LinkedListUMUC()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	
	/**
	 * Gets the size of the doubly linked list. 
	 * 
	 * @return the number of elements in the linked list
	 */
	public int getSize()
	{
		return this.size;
	}
	
	
	/**
	 * Adds element to the front of the list.
	 * 
	 * @param data the info of the new node that will be passed into the linked list
	 */
	public void insertHead(T data)
	{
		NodeUMUC<T> newNode = new NodeUMUC<T>(); // the node that will be added into the list, using the data from the parameter passed in. 
		newNode.data = data;
		
		// If the list has no nodes, then make the first node that is added in to be the head
		if(head == null)
		{
            head = newNode;
            tail = head;
            head.next = tail;
            head.prev = tail;
        }
		// Otherwise, if the list is not empty, then add the new node to the front of the linked list
		else
		{	
			newNode.next = head; // the previous head is now the next node of the newly inserted head node. 
			newNode.prev = tail; 
			tail.next = newNode;
			head.prev = newNode;
			head = newNode; // the new head should now be the new node that was passed in
        }
		size++; // increment the size of the list, since a new node has been added. 
	}
	
	
	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param data the info of the new node that will passed into the linked list
	 */
	public void insertTail(T data)
	{

		NodeUMUC<T> newNode = new NodeUMUC<T>(); // the node that will be added into the list, using the data from the parameter passed in.
		newNode.data = data;
		
		// If the list is empty, then make the first node that is added in be the head
		if(tail == null)
		{
			head = newNode;
			tail = head;
			tail.next = tail;
			tail.prev = tail;
		}
		// Otherwise,if the list is not empty, then add the new node to the end of the linked list
		else
		{
			newNode.prev = tail; // the previous tail is now the previous node of the newly inserted tail node.
			newNode.next = head;
			head.prev = newNode;
			tail.next = newNode;
			tail = newNode; // the new tail should now be the new node that was passed in
		}
		size++; // increment the size of the list, since a new node has been added.
	  }

	
	/**
	 * Removes the element at the specified position of the list. 
	 * 
	 * @param position the location in the list where the element being removed is located. 
	 * 
	 * @return the node to be removed
	 */
	public T removeElementAt (int position)
	{
        NodeUMUC<T> deletedNode = head;
        
        int count = 0; // will keep track of the elements during the iteration of the list. 
        
        // If the position to remove the node is 1, then it's the head node, so remove the head. 
        if(position == 1)
        {
            return removeHead();
        }
        
        // Iterate through the list of elements, while the node to be deleted is not the head node
        while(deletedNode != head);
        {
        	//increment the counter each each time it iterates
           count++;
           
           // If the count equals the position of the element the user wants removed, and the next element of the
           // deleted node is the head, then it's the last element. As a result, remove the tail node. 
           if(count == position && deletedNode.next == head)
           {
               return removeTail();
           }
           // else if the count is equal to the position of the element, then remove the deletedNode. 
           else if(count == position)
           { 
        	   // the .next pointer for the node before the deletedNode should now be pointing to the node after the deleted node. 
        	   deletedNode.prev.next = deletedNode.next; 
        	   
        	   // the .prev pointer for the node after the deleted node should be pointing to the node before the deleted node in the list. 
        	   deletedNode.next.prev = deletedNode.prev;
        	   
               return deletedNode.data;
           }
           
           // iterate to the next node if none of the above conditions apply.
           deletedNode = deletedNode.next;
        }
        
        // if the position specified to be removed is not within the range of the current elements, then display an error message and return null. 
         System.out.println("There is no element at Position: " + position);
         return null;
	}
	
	
	/**
	 * Removes and returns the first element from the list. If there are no elements the method returns null. 
	 * 
	 * @return data element or null
	 */
	public T removeHead()
	{
		NodeUMUC<T> tempNode = new NodeUMUC<T>(); 
		NodeUMUC<T> deleted = new NodeUMUC<T>(); // Will hold the node that will be removed from the list

		// If there are no elements the method returns null. 
		if(head == null){
			
            return null;
        }
		
		// if only 1 element is in the list 
		else if(tail == head)
		{
			deleted = tail;
			
			head = null;
			tail = null;
			size--;
			return deleted.data;	
		}
		else{
	        deleted = head;	 // the node to be deleted is the head node. 	
			tempNode = head.next;
	        tail.next = tempNode;
	        tempNode.prev = tail;
	        head = tempNode; // the new head is now the next node in the list. 
	        
	        size--; // decrement the size of the list, since a node has been removed.
	        
	        return deleted.data;
		}
	}
	
	
	/**
	 * Removes and returns the last element from the list. If there are no elements the method returns null. 
	 * 
	 * @return data element or null
	 */
	public T removeTail()
	{
		NodeUMUC<T> tempNode = new NodeUMUC<T>(); // Will hold the node that will be removed from the list
		NodeUMUC<T> deleted = new NodeUMUC<T>(); // Will hold the node that will be removed from the list

		// if no elements are in the linked list, then return null
		if(head == null)
		{
            return null;
        }
		// if only 1 element is in the list 
		else if(tail == head)
		{
			deleted = tail;
			
			head = null;
			tail = null;
			size--;
			return deleted.data;	
		}
		else{
			
	        deleted = tail;  // the node to be deleted is the tail node.
			tempNode = tail.prev;
	        head.prev = tempNode;
	        tempNode.next = head;
	        tail = tempNode; // the new tail is now the second to last node in the list. 
	        
	        size--; // decrement the size of the list, since a node has been removed.
	        
	        return deleted.data;
		}	
	}
	
	
	
	/**
	 * Returns but does not remove the element at the specified position. If there are no elements then the method returns null. 
	 * @return the data element or null
	 */
	public T peekElementAt(int position)
	{
		NodeUMUC<T> peekedNode = new NodeUMUC<T>();  // Will hold the node that will be peeked from the list		
		
		peekedNode = head;
		
        int count = 0; // will keep track of the elements during the iteration of the list. 
        
        // If the position to peek the node is 1, then it's the head node, so peek the head. 
        if(position == 1)
        {
            return peekHead();
        }
        
        do
        {
           count++;
           
           // if the count is equal to the position of the element, then peek the peekedNode
           if(count == position)
           {
        	   return peekedNode.data;
           }
           
           // move to the next element in the iteration
           peekedNode = peekedNode.next;
        }
        while(peekedNode !=head); // Iterate through the list of elements, while the node to be deleted is not the head node

        
        // if the position specified to be peeked is not within the range of the current elements, then display an error message and return null. 
        System.out.println("There is no element at Position: " + position); 
        
        return null;
	}
	
	
	
	/**
	 * Returns but does not remove the first element from the list. If there are no elements the method returns null. 
	 * @return the data element or null
	 */
	public T peekHead()
	{
		// If there are no elements the method returns null
		if(head == null)
		{
            return null;
        }
		// Otherwise, returns the first element in the list
		else{
			return head.data;
		}
	}
	
	
	/**
	 * Returns but does not remove the last element from the list. If there are no elements the method returns null.
	 * 
	 * @return the data element or null
	 */
	public T peekTail()
	{
		// If there are no nodes in the list, then the method returns null
		if(head == null)
		{
            return null;
        }
		// If there is only 1 node in the list, then method returns the head node. 
		else if(size == 1)
		{
			return head.data;
		}
		// Otherwise, returns the last element in the list
		else{
			return tail.data;	
		}
	}

	
	/**
	 * Returns a string with all the elements in the linked list.  
	 * 
	 * @return an string containing all the elements in the list side by side, separated by a space delimiter
	 */
	public String toString()
	{
		String str = "";
		
	       if(head == null)
	       {
	           return "Empty List";
	       }
	       
	       NodeUMUC<T> temp = head;
	       
	       while(temp.next != head)
	       {
	           str += temp.data + "  ";
	           
	           temp = temp.next;
	       }
	       str += temp.data;
	       
	       return str.toString();
	}
}
