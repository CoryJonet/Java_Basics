import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * MovieQueueMain is the main class in the Movie Queue project. It takes in
 * commands in the form of "Enter option - a, c, l, m, p, r, s, w or x: ". Where
 * a adds a movie title to the end the end of the movie queue, c copies a movie
 * title to the end of the movie queue, l loads a movie genre text file into the
 * movie queue and prints the movies, m moves a movie title to the front of the
 * movie queue, p prints the movie queue in a specified format, r removes a movie
 * title from the movie queue, s saves the movie queue to a text file, w marks
 * the first amount of movies as watched and removes them, and lastly, x simplfy
 * exits the program.
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class MovieQueueMain {
	
	/* You may create additional private static 
	   variables or methods as needed */

	/**
	 * The main class of movieQueue controls the program by repeatedly asking for
	 * input: "Enter option - a, c, l, m, p, r, s, w or x: " unless x (exit) is
	 * chosen. 
	 *
	 * @param String args[] Commaned line arguments aren't used in this program
	 */
	public static void main(String args[]) throws InvalidListPositionException 
	{
		
		LinkedList<String> movieQueue = new LinkedList<String>();
		//** You may also add additional variables as needed **//
		
		Scanner stdin = new Scanner(System.in);  // for reading console input
        boolean done = false;
        while (!done) {
            System.out.print("Enter option - a, c, l, m, p, r, s, w or x: ");
            String input = stdin.nextLine();
            
            if (input.length() > 0) 
            {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                // trim off any leading or trailing spaces
                remainder = input.substring(1).trim();
                
                switch (choice) 
                {
                
                case 'a' :
                	
                	//** Add your code here for option a **//
                	add(movieQueue, remainder);
                	break;
                	
                case 'c' :
                	
                	//** Add your code here for option c **//
                	copy(movieQueue, remainder);
                	break;
                
                case 'l' :
                	
                	//** Add your code here for option l **//
                	movieQueue = load(movieQueue, remainder);
                	break;
                	
                case 'm' :
                	
                	//** Add your code here for option m **//
                	move(movieQueue, remainder);
                	break;
                	
                case 'p' :
                	
                	//** Add your code here for option p *//
                	print(movieQueue);
                	break;
                	
                case 'r' :
                	
                	//** Add your code here for option r **//
                	remove(movieQueue, remainder);
                	break;
                	
                case 's' :
                	
                	//** Add your code here for option s **//
                	save(movieQueue, remainder);
                	break;
                	
                case 'w' :
                	
                	//** Add your code here for option w **//
                	movieQueue = watched(movieQueue, remainder);
                	break;
                	
                case 'x' :
                	//Exit the program. This command is already implemented.
                	done = true;
                    System.out.println("exit");
                    break;
                	
                default :
                	System.out.println("Unknown Command");
                	break;
                }
            }
        }
	}
	
	/**
	 * This method adds a movie to the movie queue from a database of movies of 
	 * the given genre. The genre will be one of the following: comedy, drama, 
	 * action, horror, family. Each line of the text file is read and printed.
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 */
	public static void add(LinkedList<String> movieQueue, String remainder) 
		throws InvalidListPositionException
	{
		boolean goodFile = false;
		while(!goodFile)
		{
			File toAdd = new File(remainder); // Create a new file to read
			Scanner inputFile = null; // Reads the file the first time through
			Scanner inputFile2 = null; // Reads the file the second time through
			Scanner inputMovieNum = null; // Reads the given movie line number to 
										  // add to the end of the movie queue
			int inputMovieNumb = 0; // The movie line number to add to the end of
								    // the movie queue
			int movieLineNum = 1; // Numbered movie lines
			boolean inputLoop = true; // To break out of the inputMovieNumb loop

			// Try to read the specified genre file
			try 
			{
				inputFile = new Scanner(toAdd);

				// Print out numbered line with movie titles
				while(inputFile.hasNextLine())
				{
					System.out.println(movieLineNum + " " 
							+ inputFile.nextLine().trim());
					movieLineNum++;
					inputMovieNumb++;
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("Cannot find the specified file.");
				break;
			}

			// Obtain correct input
			do
			{
				System.out.println("Please enter a number between 1 and " 
						+ (movieLineNum - 1));
				inputMovieNum = new Scanner(System.in);

				while(!inputMovieNum.hasNextInt())
				{
					inputMovieNum.nextLine();
					System.out.println("Please enter a valid input!");
					System.out.println("Please enter a number" +
							" between 1 and " + (movieLineNum - 1));

				}

				inputMovieNumb = inputMovieNum.nextInt();

				if(inputMovieNumb > 0)
				{
					inputLoop = false;
				}
				else
				{
					System.out.println("Please enter a valid input!");
				}

			}while(inputLoop);

			// Second time through the file to obtain the correct movie title
			try 
			{
				inputFile2 = new Scanner(toAdd);
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("Cannot find the specified file.");
			}

			String chosen = null; // The movie to add to the end of the movie queue
			boolean haveMovie = true; // To break out of a loop once the movie is 
			// obtained

			while(inputFile2.hasNextLine() && haveMovie)
			{
				for(int i = 1; i <= inputMovieNumb; i++)
				{
					chosen = inputFile2.nextLine().trim();
				}
				haveMovie = false;
			}
			movieQueue.add(chosen);
			System.out.println("Added " + chosen + " to queue.");
			goodFile = true;
		}
	}
	
	/**
	 * This method copies the line at line# to the end of the movie queue. 
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 */
	public static void copy(LinkedList<String> movieQueue, String remainder) 
	{
		// Try to copy the data at the given list node location
		try
		{
			int toCopy = Integer.parseInt(remainder);
			String movie = movieQueue.get(toCopy - 1);
			movieQueue.add(movie);
			System.out.println("Copied " + movie + " to end of queue.");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid line number.");
		}
		catch(InvalidListPositionException e)
		{
			System.out.println("Invalid line number.");
		}
		
	}
	
	/**
	 * This method loads the movie queue from the given file. 
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 * @return The movie queue that was manipulated and needs to be saved
	 */
	public static LinkedList<String> load(LinkedList<String> movieQueue, 
			String remainder) throws InvalidListPositionException
	{
		movieQueue = new LinkedList<String>(); // Create a new list to "empty"
		
		File toAdd = new File(remainder); // Text file to load
		Scanner inputFile = null; // Reads the text file to load
		
		// Try loading the text file
		try 
		{
			inputFile = new Scanner(toAdd);
			while(inputFile.hasNextLine())
			{
				movieQueue.add(inputFile.nextLine());
			}
			System.out.print(movieQueue.print(true));
			
			inputFile.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Cannot find the specified file.");
		}
		return movieQueue;
	}
	
	/**
	 * This method moves the movie title at position line# to the front of the 
	 * movie queue.
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 */
	public static void move(LinkedList<String> movieQueue, String remainder)
	{
		// Try moving a movie title at the given position to the front
		try
		{	
			// The movie title to be moved
			String move = movieQueue.get(Integer.parseInt(remainder) - 1);
			movieQueue.remove(Integer.parseInt(remainder) - 1);
			movieQueue.add(0, move);
			System.out.println("Moved " + move + " to front of queue.");
		
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid line number");
		}
		catch(InvalidListPositionException e)
		{
			System.out.println("Invalid line number");
		}
	}
	
	/**
	 * This method prints the movie queue in the format specified by the 
	 * LinkedList's print() method.
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 */
	public static void print(LinkedList<String> movieQueue)
	{
		if(movieQueue.isEmpty())
		{
			System.out.println("Empty.");
		}
		else
		{
			boolean lineNumbers = true;
			System.out.print(movieQueue.print(lineNumbers));
		}
	}
	
	/**
	 * This method removes the specified movie from the list.
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 */
	public static void remove(LinkedList<String> movieQueue, String remainder)
	{
		
		try
		{
			System.out.println("Removed " 
					+ movieQueue.remove(Integer.parseInt(remainder) - 1)
					+ " from queue.");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid line number");
		}
		catch(InvalidListPositionException e)
		{
			System.out.println("Invalid line number");
		}
	}
	
	/**
	 * This method saves the movie queue to the specified fileName. 
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 */
	public static void save(LinkedList<String> movieQueue, String remainder)
	{
		
		if(movieQueue.isEmpty())
		{
			System.out.println("Cannot write to file, movie queue is empty.");
		}
		
		PrintWriter output; // Writes to a text file
		try 
		{
			output = new PrintWriter(remainder);
			for(int i = 1; i <= movieQueue.size(); i++)
			{
				// Formatting for text file
				if(i == movieQueue.size())
				{
					output.print(movieQueue.get(i - 1));
					break;
				}
				output.println(movieQueue.get(i - 1));
			}
			System.out.println("Saved.");
			output.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Cannot write to the specified file.");
		}
		catch (InvalidListPositionException e)
		{
			
		}
	}
	
	/**
	 * This method marks the first amount movies in the list as watched 
	 * (i.e., remove them from the queue).
	 *
	 * @param LinkedList<String> movieQueue The movieQueue LinkedList 
	 * @param String remainder The string that contains the rest of the command
	 * @return The movie queue that was manipulated
	 */
	public static LinkedList<String> watched(LinkedList<String> movieQueue, 
			String remainder)
	{
		try  // If the integer for watched is not an int
		{
			// If the amount asked to be watched is less than or equal to 0
			if(Integer.parseInt(remainder) <= 0)
			{
				System.out.println("Invalid number of movies.");
				return movieQueue;
			}

			// If the amount to mark as watched is greater than movieQueue.size()
			if(Integer.parseInt(remainder) > movieQueue.size())
			{
				System.out.println("Removed first " + movieQueue.size() 
						+ " movies " + "from queue.");
				return new LinkedList<String>();
			}

			// Number of movies to mark as watched
			int loop = Integer.parseInt(remainder); 
			int counter = 0; // Counts for when one item has been withdrawn or more
			for(int i = 0; i < loop; i++)
			{
				try 
				{
					movieQueue.remove(i - counter);	
					counter++;
				} 
				catch (InvalidListPositionException e) 
				{
					System.out.println("Invalid number of movies.");
					break;
				}
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid number of movies.");
			return movieQueue;
		}
		System.out.println("Removed first " + remainder + " movies " +
		"from queue.");
		return movieQueue;
	}
} 