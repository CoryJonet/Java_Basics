import java.util.Iterator;
import java.util.List;

/**
 * The BSTSortedList class constructs a binary search tree as the sorted list
 * to store the internet Acronyms. It implements the SortedListADT, which thus
 * uses the methods insert, delete, lookup, size, isEmpty, and iterator to 
 * manipulate the sorted list of Acronyms.
 *
 * <p>Bugs: None Known
 *
 * @author Cory Jonet
 */
public class BSTSortedList<K extends Comparable<K>> implements SortedListADT<K> 
{
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the sorted list
    private BSTSortedListIterator<K> iterator; // The iterator for the sorted list
    
    /**
     * The BSTSortedList constructor simply constructs a BST with root null
     * and numItems as 0.
     *
     */
    public BSTSortedList()
    {
    	root = null;
    	numItems = 0;
    }
    
    /**
     * The public insert method inserts a key into the BSTSortedList, but uses
     * a recursive, overloaded method, seen below, to perform its work. This is 
	 * code that was provided by Professor Skrentny and modified slightly.
     *
     * @param key The key value to insert into the Sorted List
     */
	public void insert(K key) 
	{
		root = insert(root, key);	
		numItems++;
	}

	/**
	 * The public delete method deletes a key from the BSTSortedList, but uses
	 * a recursive, overloaded method, seen below, to perform its work. This is 
	 * code that was provided by Professor Skrentny and modified slightly.
	 *
	 * @param key The key value to delete from the Sorted List
	 * @return true if the deletion is successful (i.e., the key was in the 
	 * Sorted List and has been removed), false otherwise (i.e., the key was 
	 * not in the Sorted List)
	 */
	public boolean delete(K key) 
	{
		root = delete(root, key);
		if(lookup(key) == null)
		{
			return true;
		}
		return false;
	}

	/**
	 * The public lookup methods looks up a key from the BSTSortedList, but uses
	 * a recursive, overloaded method, seen below, to perform its work. This is 
	 * code that was provided by Professor Skrentny and modified slightly.
	 *
	 * @param key The key to search for
	 * @return The key from the Sorted List, if it is there; null if the key 
	 * is not in the Sorted List
	 */
	public K lookup(K key) 
	{
		return lookup(root, key);
	}

	/**
	 * The size method simply returns the size of the BSTSortedList.
	 *
	 * @return The size of the BSTSortedList
	 */
	public int size() 
	{
		return numItems;
	}

	/**
	 * The isEmpty method simply returns whether the BSTSortedList is empty or
	 * not.
	 *
	 * @return true iff numItems equals 0, false otherwise
	 */
	public boolean isEmpty() 
	{	
		return numItems == 0;
	}

	/**
	 * The iterator method constructs a BSTSortedListIterator to be used to 
	 * point at the key values in the BSTSortedList. It then returns this
	 * iterator.
	 *
	 * @return An iterator over the Sorted List
	 */
	public Iterator<K> iterator() 
	{
		iterator = new BSTSortedListIterator<K>(root);
		return iterator;
	}
	
	/**
	 * The private insert method does the work in inserting a key value into
	 * the BSTSortedList. It compares the other key values to this key value
	 * and determines where to place the key based on alphabetical ordering. 
	 * This is code that was provided by Professor Skrentny and modified 
	 * slightly.
	 *
	 * @param n The new BSTnode<K> that is currently being looked at
	 * @param key The key to be added into the BSTSortedList
	 * @return The new BSTnode<K> that was created and place in the BSTSortedList
	 */
	private BSTnode<K> insert(BSTnode<K> n, K key) 
	{
	    if (n == null) 
	    {
	        return new BSTnode<K>(key, null, null);
	    }
	    
	    if (key.compareTo(n.getKey()) < 0) {
	        // add key to the left subtree
	        n.setLeft( insert(n.getLeft(), key) );
	        return n;
	    }
	    
	    else 
	    {
	        // add key to the right subtree
	        n.setRight( insert(n.getRight(), key) );
	        return n;
	    }
	}
	
	/**
	 * The private delete method does the work in deleting a node from the 
	 * BSTSortedList. It will also find a suitable replacement for the node 
	 * that was deleted in the cases of no children, one child, and two
	 * children. This is code that was provided by Professor Skrentny and modified 
	 * slightly.
	 *
	 * @param n The BSTnode<K> that is currently being looked at
	 * @param key The key that is to be deleted
	 * @return The BSTnode<K> that was currently being looked up
	 */
	private BSTnode<K> delete(BSTnode<K> n, K key) 
	{
	    if (n == null) 
	    {
	        return null;
	    }
	    
	    if (key.equals(n.getKey())) 
	    {
	        // n is the node to be removed
	        if (n.getLeft() == null && n.getRight() == null) 
	        {
	            return null;
	        }
	        if (n.getLeft() == null) 
	        {
	            return n.getRight();
	        }
	        if (n.getRight() == null) 
	        {
	            return n.getLeft();
	        }
	       
	        // if we get here, then n has 2 children
	        K smallVal = smallest(n.getRight());
	        n.setKey(smallVal);
	        n.setRight( delete(n.getRight(), smallVal) );
	        return n; 
	    }
	    
	    else if (key.compareTo(n.getKey()) < 0) 
	    {
	        n.setLeft( delete(n.getLeft(), key) );
	        return n;
	    }
	    
	    else 
	    {
	        n.setRight( delete(n.getRight(), key) );
	        return n;
	    }
	}
	
	/**
	 * The smallest method is a recursive helper method for the private delete
	 * method in the event that a BSTnode has two children and a replacement is
	 * needed for the BSTnode that was deleted. This is code that was provided
	 * by Professor Skrentny and modified slightly.
	 *
	 * @param n The BSTnode<K> that is currently being looked at
	 * @return The key value that is the replacement 
	 */
	private K smallest(BSTnode<K> n)
	// precondition: n is not null
	// postcondition: return the smallest value in the subtree rooted at n

	{
	    if (n.getLeft() == null) 
	    {
	        return n.getKey();
	    } 
	    else 
	    {
	        return smallest(n.getLeft());
	    }
	}

	/**
	 * The private lookup method does the work in looking up a value in the 
	 * BSTSortedList. This is code that was provided by Professor Skrentny 
	 * and modified slightly.
	 *
	 * @param n The BSTnode<K> that we're currently looking at
	 * @param K The key value that we're looking for
	 * @return The key value that was found, null if not found
	 */
	private K lookup(BSTnode<K> n, K key) 
	{
	    if (n == null) 
	    {
	        return null;
	    }
	    
	    if (n.getKey().equals(key)) 
	    {
	        return n.getKey();
	    }
	    
	    if (key.compareTo(n.getKey()) < 0) 
	    {
	        // key < this node's key; look in left subtree
	        return lookup(n.getLeft(), key);
	    }
	    
	    else 
	    {
	        // key > this node's key; look in right subtree
	        return lookup(n.getRight(), key);
	    }
	}
}
