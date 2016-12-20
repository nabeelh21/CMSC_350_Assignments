
/**
 * This node will be used by the binary expression tree to hold the operators.
 * 
 * @author Nabeel Hussain
 */
public class OperatorNode extends Node
{
	/**
	 * Constructor,which initializes the node to the operator passed in, and sets the left and right child to null. 
	 * 
	 *@parm operator 
	 */ 
    public OperatorNode(String operator)
    {
        this.data = operator;
        this.left = null;
        this.right = null;
    }
    
	/**
	 * Set the left child of the node
	 * 
	 * @param left the left child of the node
	 */  
    public void setLeft(Node left)
    {
        this.left = left;
    }
    
    
	/**
	 * Set the right child of the node
	 * 
	 * @param right the right child of the node
	 */ 
    public void setRight(Node right)
    {
        this.right = right;
    }
    
    
    
	/**
	* Get the left child of the node
	* 
	* @return the left child of the node
	*/ 
    public Node getLeft()
    {
        return left;
    }

    
    
	/**
	 * Get the right child of the node
	 * 
	 * @return the right child of the node
	 */ 
    public Node getRight()
    {
        return right;
    }

    
	/**
	 * If it is an operator node, then there must always be two operands attached to it,in order for there to be a valid postfix expression.
	 * As a result, there must be a left and right child to the operator node, and the calculation should be displayed using parenthesis. 
	 * 
	 * @return a string of how the operator node should be displayed in infix form. 
	 */ 
   @Override
   public String toString()
   {
       return "( " + this.left + " " + data + " " + this.right + " )";
   }
}