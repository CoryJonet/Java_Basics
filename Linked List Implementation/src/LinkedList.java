/**
 * This class creates a movie queue represented by a LinkedList of ListNodes.
 * It includes methods to add a movie title at the end of the queue, add at a 
 * position, remove at a position, get at a position, the size of the movie
 * queue, and whether or not the movie queue is empty.
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class LinkedList<String> implements ListADT<String> 
{

	private ListNode<String> head;
	private ListNode<String> tail;
	private int numItems;
	
	/**
	 * The constructor for the LinkedList class. It initializes the header node
	 * to a dummy node, tail to the dummy node, and numItems to 0.
	 */
	public LinkedList()
	{
		head = new ListNode<String>(null);
		tail = head;
		numItems = 0;
	}
	
	/**
	 * This add method adds a movie title to the end of the movie queue 
	 * (LinkedList). The method throws an IllegalArgumentException if the 
	 * param (item) is null. The method also checks for special cases (i.e. 
	 * adding to a list with zero items, one item).
	 *
	 * @param String item The movie title to be added to the movie queue 
	 * (LinkedList)
	 */
	public void add(String item) 
	{
		stringCheck(item);
		
		// Empty list
		if(tail == head)
		{
			ListNode<String> newNode = new ListNode<String>(item);
			newNode.setNext(null);
			head.setNext(newNode);
			tail = newNode;
			numItems++;
			return;
		}
		
		// One or more items in the list
		ListNode<String> curr = head;
		for(int i = 0; i < numItems; i++)
		{
			curr = curr.getNext();
		}
		curr.setNext(new ListNode<String> (item, null));
		numItems++;
	}
	
	/**
	 * This add method adds a movie title to any valid, zero-based position in
	 * the movie queue (LinkedList). It checks for a null movie title and throws
	 * an InvalidArgumentException when needed. It also checks for an invalid
	 * position to add to and throws an InvalidListPositionException when needed.
	 * Lastly, the method checks for special cases (i.e. adding to a zero item
	 * list and a one item list).
	 *
	 * @param int pos The zero-based index to add to
	 * @param String item The movie to add to the movie queue (LinkedList)
	 */
	public void add(int pos, String item) throws InvalidListPositionException 
	{
		stringCheck(item);
		
		if(pos < 0 || (pos >= numItems && pos != 0))
		{
			throw new InvalidListPositionException();
		}
		
		// If there's a 0 item linked list
		if(tail == head)
		{
			ListNode<String> newNode = new ListNode<String>(item);
			tail.setNext(newNode);
			head.setNext(newNode);
			tail = newNode;
			numItems++;
			return;
		}

		// If we're adding to the front of the movie queue
		if(pos == 0)
		{
			ListNode<String> newNode = new ListNode<String>(item);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			numItems++;
			return;
		}
		
		// Else we're adding not to the front of the list
		ListNode<String> curr = traverse(pos, "add");
		curr.setNext(new ListNode<String>(item, curr.getNext()));
		if(pos == (numItems - 1))
		{
			tail = curr.getNext();
		}
		
		numItems++;
	}

	/**
	 * The remove method removes a movie title from anywhere in the movie queue
	 * (LinkedList) and returns that movie title. The method checks for an 
	 * invalid position and throws an InvalidListPositionException where 
	 * appropriate. The method also checks for special cases (i.e. removing 
	 * from a one item list and removing from the last item in the list).
	 *
	 * @param int pos The zero-based position where the movie title will be 
	 * removed
	 * @return The removed move title from the movie queue (LinkedList)
	 */
	public String remove(int pos) throws InvalidListPositionException 
	{
		
		if(pos < 0 || pos >= numItems)
		{
			throw new InvalidListPositionException();
		}
		
		// If there's only one item in the list
		if(pos == 0 && numItems == 1)
		{
			String temp = head.getNext().getData();
			head.setNext(null);
			tail.setNext(head);
			numItems--;
			return temp;
		}
		
		// Else there more than one item in the list
		ListNode<String> curr = traverse(pos, "remove");
		String temp = curr.getNext().getData();
		
		//If we're removing the last item in the list
		if((numItems - pos) == 1)
		{
			curr.setNext(null);
			tail = curr;
			numItems--;
			return temp;
		}
		
		// If we're NOT removing the last item
		curr.setNext(curr.getNext().getNext());
		numItems--;
		return temp;
	}

	/**
	 * The get method returns the movie title of the given zero-based 
	 * position in the movie queue (LinkedList). It also checks for invalid
	 * positions and throws an InvalidListPositionException when needed.
	 *
	 * @param int pos The zero-based position to retrieve the movie title from
	 * in the movie queue (LinkedList)
	 * @return The movie title to be returned
	 */
	public String get(int pos) throws InvalidListPositionException 
	{
		
		if(pos < 0 || pos >= numItems)
		{
			throw new InvalidListPositionException();
		}
		ListNode<String> curr = traverse(pos, "get");
		return curr.getData();
	}

	/**
	 * The isEmpty method returns true if the movie queue (LinkedList) is empty,
	 * false otherwise.
	 *
	 * @return true if the movie queue (LinkedList) is empty, false otherwise
	 */
	public boolean isEmpty() 
	{
		return numItems == 0;
	}

	/**
	 * The size method returns the size of the movie queue (LinkedList).
	 * 
	 * @return The size of the movie queue (LinkedList)
	 */
	public int size() 
	{
		return numItems;
	}
	
	/**
	 * The print method returns a String containing the movie titles of the movie
	 * queue (LinkedList) formatted as follows: If lineNumbers is false, each 
	 * movie title should be on its own line; else each movie title should be on 
	 * its own line, preceded by its line number.
	 *
	 * @param boolean lineNumbers Whether or not to add line numbers before the 
	 * movie title on each row
	 * @return The string with the movie titles each on a new line with their
	 * respective number
	 */
	public String print(boolean lineNumbers)
	{
		String movies = (String) ""; // The string that will contain all movies
		ListNode<String> curr = head.getNext(); // Pointer
		// The numerical placement of an individual movie 
		int movieCounter = 1; // Movie line number
		
		if(lineNumbers)
		{
			while(curr != null)
			{
				movies = (String) (movies + "" + movieCounter + " " 
						+ curr.getData() + "\n");
				curr = curr.getNext();
				movieCounter++;
			}
			return movies;
		}
		else
		{
			while(curr != null)
			{

				movies = (String) (movies + "" + curr.getData() + "\n");
				curr = curr.getNext();
				movieCounter++;
			}
		}
		return movies;
	}
	
	/**
	 * The stringCheck method checks to see if the parameter Strings in previous
	 * methods are valid and throws an IllegalArgumentException when 
	 * appropriate.
	 *
	 * @param String item The string to be checked 
	 */
	public void stringCheck(String item) throws IllegalArgumentException
	{
		if(item.equals(null))
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * The traverse method traverses the movie queue (LinkedList). There are two
	 * cases: adding to a position/remove at a position and getting at a 
	 * position.
	 *
	 * @param int pos The position to add/remove/get at
	 * @param java.lang.String method The method that is calling this method
	 * @return The ListNode pointer named curr
	 */
	public ListNode<String> traverse(int pos, java.lang.String method)
	{
		if(method.equals("add") || method.equals("remove"))
		{
			ListNode<String> curr = head;
			for(int i = 0; i < pos; i++)
			{
				curr = curr.getNext();
			}
			return curr;
		}

		ListNode<String> curr = head;
		for(int i = 0; i < pos + 1; i++)
		{
			curr = curr.getNext();
		}
		return curr;
	}

}
