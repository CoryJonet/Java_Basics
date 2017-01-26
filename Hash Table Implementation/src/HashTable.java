import java.io.*;
import java.util.LinkedList;

/**
 * This class implements a hashtable that using chaining for collision handling.
 * The chains are implemented using LinkedLists.  When a hashtable is created, 
 * its initial size and maximum load factor are specified. The hashtable can 
 * hold arbitrarily many items and resizes itself whenever it reaches 
 * its maximum load factor. Note that this hashtable allows duplicate entries.
 */
public class HashTable<T> 
{
	
	/* Add private variables here as needed */
	
	private int size; // Size of the array of LinkedLists
	private double loadFactor; // The maximum load factor allowed
	private LinkedList<T>[] hashTable; // The hashTable array with buckets
	private int numItems; // The number of items added to the hashTable
	
    /**
     * Constructs an empty hashtable with the given initial size and maximum '
     * load factor. The load factor should be a real 
     * number greater than 0.0 (not a percentage).  For example, to create a 
     * hash table with an initial size of 10 and a load factor of 0.85, one 
     * would use:
     * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
     *
     * @param initSize The initial size of the hashtable.  If the size is less
     * than or equal to 0, an IllegalArgumentException is thrown.
     * @param loadFactor The load factor expressed as a real number.  If the
     * load factor is less than or equal to 0.0, an IllegalArgumentException is
     * thrown.
     **/
    public HashTable(int initSize, double loadFactor) 
    {
    	if(initSize <= 0)
    	{
    		throw new IllegalArgumentException();
    	}
    	if(loadFactor <= 0.0)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	size = initSize;
    	this.loadFactor = loadFactor;
    	hashTable = (LinkedList<T>[])(new LinkedList[size]);
    	numItems = 0;
    	
    	// Instantiate the LinkedList buckets
    	for(int i = 0; i < hashTable.length; i++)
    	{
    		hashTable[i] = new LinkedList<T>();
    	}
    }
    
    
    /**
     * Determines if the given item is in the hashtable and returns it if 
     * present.  If more than one copy of the item is in the hashtable, the 
     * first copy encountered is returned.
     *
     * @param item the item to search for in the hashtable
     * @return the item if it is found and null if not found
     **/
    public T lookup(T item) 
    {
    	
    	int code = item.hashCode(); // The hashcode for the item
    	
    	// If the hashcode is negative
    	if(code < 0)
    	{
    		code = -code;
    	}
    	
    	code = code % hashTable.length;
    	
    	if(hashTable[code].contains(item))
    	{
    		return hashTable[code].get(hashTable[code].indexOf(item));
    	}
    	else
    	{
    		return null;
    	}
    }
    
    
    /**
     * Inserts the given item into the hash table.  
     * 
     * If the load factor of the hashtable after the insert would exceed 
     * (not equal) the maximum load factor (given in the constructor), then the 
     * hashtable is resized.
     * 
     * When resizing, to make sure the size of the table is good, the new size 
     * is always 2 x <i>old size</i> + 1.  For example, size 101 would become 
     * 203.  (This  guarantees that it will be an odd size.)
     * 
     * <p>Note that duplicates <b>are</b> allowed.</p>
     *
     * @param item the item to add to the hashtable
     **/
    public void insert(T item) 
    {
    	// This begins the process of resizing the hashTable
    	if(((double)numItems) /((double) size) >= loadFactor)
    	{
    		
    		int newSize = (size * 2) + 1; // The new size for the hashTable
    		
    		LinkedList<T>[] hashTable2 = (LinkedList<T>[])(new LinkedList[newSize]);;
    		
    		// Instantiate the LinkedList "buckets" in the hashTable
    		for(int i = 0; i < hashTable2.length; i++)
    		{
    			hashTable2[i] = new LinkedList<T>();
    		}
    		
    		// Begin the process of reentering the items with new hashcodes
    		for(int i = 0; i < size; i++)
    		{
    			if(!hashTable[i].isEmpty())
    			{
    				for(int j = 0; j < hashTable[i].size(); j++)
    				{
    					
    					int code2 = hashTable[i].get(j).hashCode(); // The new hashCode
    					
    					// If the new hashcode is negative
    					if(code2 < 0)
    					{
    						code2 = -code2;
    					}
    					
    					code2 = code2 % newSize;
    					hashTable2[code2].add(hashTable[i].get(j));
    				}
    			}
    		}
    		hashTable = hashTable2;
    		size = newSize;
    	}
    	
    	// The hashcode for the new item to be added
    	int code = item.hashCode();
    	
    	// If the hashcode is negative
    	if(code < 0)
    	{
    		code = -code;
    	}
    	
		code = code % size;
		hashTable[code].add(item);
		numItems++;
    }
    
    
    /**
     * Removes and returns the given item from the hashtable.  If the item is 
     * not in the hashtable, <tt>null</tt> is returned.  If more than one copy 
     * of the item is in the hashtable, only the first copy encountered is 
     * removed and returned.
     *
     * @param item the item to delete in the hashtable
     * @return the removed item if it was found and null if not found
     **/
    public T delete(T item) 
    {
    	int code = item.hashCode(); // The hash code for the item
    	
    	// If the hashcode is negative
    	if(code < 0)
    	{
    		code = -code;
    	}
    	
    	code = code % size;
    	
    	if(hashTable[code].contains(item))
    	{
    		T temp = hashTable[code].get(hashTable[code].indexOf(item));
    		hashTable[code].remove(hashTable[code].indexOf(item));
    		numItems--;
    		return temp;
    	}
    	else
    	{
    		return null;
    	}
    }
    
  
    /**
     * Prints statistics about the hashtable to the PrintStream supplied.
     * The statistics displayed are: 
     * <ul>
     * <li>the current table size
     * <li>the number of items currently in the table 
     * <li>the current load factor
     * <li>the length of the largest chain
     * <li>the number of chains of length 0
     * <li>the average length of the chains of length > 0
     * </ul>
     *
     * @param out the place to print all the output
     **/
    public void displayStats(PrintStream out) 
    {
    	// The current load factor
    	double currLoadFactor = ((double)numItems)/((double)size);
    	
        out.println("Current table size: " + size);
        out.println("The number of items currently in the table: " + numItems);
        out.println("The current load factor: " + currLoadFactor);
        
        int largestChain = 0; // The largest chain
        int numZeroes = 0; // Number of LinkedList chains or "buckets" with size 0
        double averageLength = 0; // The average length of the buckets
        double average = 0; // The number of buckets
        
        for(int i = 0; i < hashTable.length; i++)
        {
        	if(hashTable[i].size() > largestChain)
        	{
        		largestChain = hashTable[i].size();
        	}
        	if(hashTable[i].isEmpty())
        	{
        		numZeroes++;
        	}
        	if(hashTable[i].size() != 0)
        	{
        		averageLength += hashTable[i].size();
        		average++;
        	}
        }
        
        // Retrieve the average length of a bucket
        averageLength = averageLength / average;
        
        out.println("The length of the largest chain: " + largestChain);
        out.println("The number of chains of length 0: " + numZeroes);
        out.println("The average of the chains of length > 0: " + averageLength);
    }
}
