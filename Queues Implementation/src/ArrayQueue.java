/**
 * The ArrayQueue class implements the QueueADT and all its methods. Its main 
 * objective is to create a circular ArrayQueue to store and manipulate MazeCell
 * objects to either store, solve or print.
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class ArrayQueue<E> implements QueueADT<E>
{

	private static final int INITSIZE = 10;  // initial array size
	private E[] items = (E[]) new Object[10]; // the items in the queue
	private int numItems;   // the number of items in the queue
	private int frontIndex = 0; // Front of the circular queue
	private int rearIndex = -1; // Rear of the circular queue

	/**
	 * The enqueue method enqueues a MazeCell object into the ArrayQueue. If the
	 * ArrayQueue is too full, the enqueue method will expand the ArrayQueue and
	 * appropriately fix the frontIndex and rearIndex values.
	 *
	 * @param item The MazeCell object to enqueue
	 */
	public void enqueue(E item) 
	{
		// check for full array and expand if necessary
		if (items.length == numItems) 
		{
			E[] tmp = (E[])(new Object[items.length * 2]);
			System.arraycopy(items, frontIndex, tmp, frontIndex,
					items.length-frontIndex);
			if (frontIndex != 0) 
			{
				System.arraycopy(items, 0, tmp, items.length, frontIndex);
			}
			items = tmp;
			rearIndex = frontIndex + numItems - 1;
		}

		// use auxiliary method to increment rear index with wraparound
		rearIndex = incrementIndex(rearIndex);

		// insert new item at rear of queue
		items[rearIndex] = item;
		numItems++;
	}

	/**
	 * The dequeue method dequeues the MazeCell object from the first index 
	 * (FIFO) and returns the MazeCell object.
	 *
	 * @return The dequeued MazeCell object
	 */
	public E dequeue()
	{
		E temp = items[frontIndex];
		frontIndex = incrementIndex(frontIndex);
		numItems--;
		return temp;
	}

	/**
	 * The isEmpty method checks if the ArrayQueue is empty or not.
	 *
	 * @return True iff numItems is 0, false otherwise
	 */
	public boolean isEmpty() 
	{
		return numItems == 0;
	}

	/**
	 * The size method returns the size of the ArrayQueue.
	 *
	 * @return The size of the ArrayQueue
	 */
	public int size() 
	{
		return numItems;
	}
	
	/**
	 * The incrementIndex method is a helper method for to increment the 
	 * indices of the ArrayQueue with wrap around.
	 *
	 * @param index The rearIndex that needs to be manipulated.
	 * @return The new rearIndex
	 */
	private int incrementIndex(int index) 
	{
		if (index == items.length - 1) 
			return 0;
		else 
		{
			return index + 1;
		}
	}


}
