
/**
 * This is an abstract class that will define a tree node, which will be used to hold the operator sand operands being read in from the postfix expression. 
 * 
 * @Nabeel 
 */
public abstract class Node
{
    protected String data; // The information that will be stored in the node. The operators and operands being read in from the postfix expression. 
    protected Node left;   // The nodes left child
    protected Node right;  // The nodes right child


	/**
	 * Will set the data of the node. 
	 * 
	 * @param data the data to be stored in the Node
	 */
    public void setData(String data)
    {
        this.data = data;
    }
    
	/**
	 * @return the data that is stored in the Node
	 */
    public String getData()
    {
        return data;
    }
}
