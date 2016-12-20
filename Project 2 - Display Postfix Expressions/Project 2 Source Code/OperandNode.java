
/**
 *  This node will be used by the binary expression tree to hold the operands.
 *  An operand node will always be a leaf node, and therefore its left and right child should be null.
 *  
 *  @author Nabeel Hussain
 */

public class OperandNode extends Node
{
	
	/**
	 * Constructor,which initializes the node to the operand passed in, and sets the left and right child to null. 
	 * 
	 *@parm operand
	 */ 
    public OperandNode(String operand)
    {
        this.data = operand;
        this.left = null;
        this.right = null;
    }
       
	/** 
	 * 
	 * @return a string of how the operand node should be displayed in infix form. 
	 */ 
    @Override
    public String toString()
    {
        return data + "";
    }
}
