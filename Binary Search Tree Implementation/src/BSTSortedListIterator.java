import java.util.*;

/**
 * BSTSortedListIterator implements an iterator for a binary search tree (BST)
 * implementation of a Sorted List.
 *
 * <p>Bugs: None Known
 *
 * @author Cory Jonet
 */
public class BSTSortedListIterator<K extends Comparable<K>> implements Iterator<K> 
{

	private Stack<BSTnode<K>> stack; //for keeping track of nodes

	/**
	 * The BSTSortedListIterator constructor creates a stack to keep track of 
	 * the objects in order. It uses a recursive helper method called setUp to
	 * create the stack.
	 *
	 * @param root The start of the iteration
	 */
	public BSTSortedListIterator(BSTnode<K> root) 
	{
		stack = new Stack<BSTnode<K>>();

		//Add your code here

		//Hint: Use pre-order traversal. Push the root onto the stack,
		//and then recursively push its left children (push its left child,
		//then its left child's left child, etc.).
		
		setUp(root);
	} 
	
	/**
	 * The hasNext method simply returns true iff the stack isn't empty, false
	 * otherwise.
	 *
	 * @return true iff the stack isn't empty, false otherwise
	 */
	public boolean hasNext() 
	{
		//Add your code here

		//Replace this return statement
		
		return !stack.isEmpty();
	}

	/**
	 * The next method pops off the next item in the stack and returns it, 
	 * NoSuchElementException otherwise.
	 *
	 * @return The item the iterator looked at
	 */
	public K next() 
	{
		//Add your code here

		//Replace this return statement
		if(!stack.isEmpty())
		{
			return stack.pop().getKey();
		}
		else
		{
			throw new NoSuchElementException();
		}

		//Hint: Remember you are using pre-order traversal. The next node is at 
		//the top of the stack. Pop it, then push its right child. Recursively 
		//push all its left children onto the stack as done in the constructor.
		//Finally, return the key from the popped node. Don't forget to 
		//throw a NoSuchElementException if there is no next node.
	}

	/**
	 * The remove method isn't supported in this program.
	 *
	 */
	public void remove() 
	{
		// DO NOT CHANGE: you do not need to implement this method
		throw new UnsupportedOperationException();
	}

	/**
	 * The setUp method recursively sets up the stack for the iterator to use. It
	 * does so in an alphabetical order.
	 *
	 * @param root The beginning of the BSTSortedList and current node that's 
	 * being looked at
	 */
	private void setUp(BSTnode<K> root)
	{
		if(root == null)
		{
			return;
		}

		if(root.getRight() == null && root.getLeft() == null)
		{
			stack.push(root);
			return;
		}

		if(root.getRight() != null)
		{
			setUp(root.getRight());
		}

		stack.push(root);

		if(root.getLeft() != null)
		{
			setUp(root.getLeft());
		}
	}
}
