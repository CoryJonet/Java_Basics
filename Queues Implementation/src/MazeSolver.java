import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The MazeSolver class is the main class of the MazeSolver program. It takes
 * input commands d, m, p, s, or x to either display, store, print, solve or
 * exit the maze. 
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class MazeSolver {

	public static MazeCell[][] maze;
	public static ArrayQueue<MazeCell> mazeQueue = new ArrayQueue<MazeCell>();

	/* You may create additional variables as needed */
	public static int row = 0; // Row of the maze
	public static int column = 0; // Column of the maze
	public static boolean noSolution = false;
	public static boolean hasSolution = false;

	/**
	 * The main method of the MazeSolver program that takes input commands
	 * d, m, p, s, or x to either display, store, print, solve or exit the maze.
	 * Once a command is chosen, the scanner will take the appropriate 
	 * parameters for each choice.
	 *
	 * @param args The arguments, which are not used
	 */
	public static void main(String[] args) {

		LinkedList<MazeCell> path = new LinkedList<MazeCell>(); // The path solution

		Scanner stdin = new Scanner(System.in);
		boolean done = false;
		while (!done) {
			System.out.print("Enter option - d, m, p, s, or x: ");
			String input = stdin.nextLine();

			if (input.length() > 0) {
				char choice = input.charAt(0);  // strip off option character
				String remainder = "";  // used to hold the remainder of input
				// trim off any leading or trailing spaces
				remainder = input.substring(1).trim();

				switch (choice) {

				case 'd':

					//Add code for choice d (display maze) here

					display();

					break;

				case 'm':

					//Add code for choice m (set maze) here
					
					// Clear the solution path and mazeQueue
					path = new LinkedList<MazeCell>();
					mazeQueue = new ArrayQueue<MazeCell>();
					
					setMaze(remainder);

					break;

				case 'p':
					//Add code for choice p (print path) here

					print(path);

					break;

				case 's':

					//Add code for choice s (solve maze) here
					
					path = solve(path);

					break;

				case 'x':
					System.out.println("Exit");
					done = true;
					break;

				default:
					System.out.println("Unknown command.");
					break;

				}
			}
		}
	}

	/**
	 * The display method displays the maze as is in the text file. If there is
	 * no maze, there is an appropriate message.
	 *
	 */
	public static void display()
	{
		// If there is no maze
		
		if(maze == null)
		{
			System.out.println("No maze specified.");
		}
		
		// Else print the maze
		
		else
		{
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < column; j++)
				{

					System.out.print(maze[i][j]);
				}
				System.out.println();
			}
		}
	}

	/**
	 * The setMaze method sets the maze into the 2D maze array. It has 
	 * appropriate messages for no file, no maze, etc.
	 *
	 * @param remainder The rest of the input (the text file name)
	 */
	public static void setMaze(String remainder)
	{
		// File to input maze from: 

		File toAdd = new File(remainder);
		Scanner inputFile = null;

		try 
		{
			inputFile = new Scanner(toAdd);

		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Invalid file.");
		}

		// Retrieve the row and column from the text file and
		// initialize the maze to that row by column size
		row = inputFile.nextInt();
		column = inputFile.nextInt();
		maze = new MazeCell[row][column];

		inputFile.nextLine(); // Retrieve on line of the maze at a time

		// Initialize then maze
		
		for(int i = 0; i < row; i++)
		{
			String line = inputFile.nextLine();

			for(int j = 0; j < column; j++)
			{
				char temp = line.charAt(j);
				if(temp == '|')
				{
					maze[i][j] = new MazeCell(MazeCell.WALL, i, j);
				}
				else if(temp == ' ')
				{
					maze[i][j] = new MazeCell(MazeCell.OPEN, 
							i, j);
				}
				else if(temp == 'S')
				{
					maze[i][j] = new MazeCell(MazeCell.START, 
							i, j);
				}
				else if(temp == 'X')
				{
					maze[i][j] = new MazeCell(MazeCell.END, 
							i, j);
				}
			}

		}
		inputFile.close();
	}

	/**
	 * The print method prints the solution to the specified maze line by line
	 * in (row, column) coordinates.
	 *
	 * @param path The solution path
	 */
	public static void print(LinkedList<MazeCell> path)
	{
		// If the maze is empty
		
		if(maze == null)
		{
			System.out.println("No maze specified.");
			return;
		}
		
		// If there is no solution to the maze
		
		if(noSolution)
		{
			System.out.println("No solution.");
			return;
		}
		
		// If the maze hasn't been solved yet
		
		if(!hasSolution)
		{
			System.out.println("Maze has not yet been solved.");
			return;
		}

		// Loop through the solution path and print the coordinates
		
		for(int i = 0; i < path.size(); i++)
		{
			System.out.println("(" + path.get(i).row() + "," 
					+ path.get(i).col() + ")");
		}

	}
	
	/**
	 * The solve method solves the specified maze through various checks, but 
	 * mainly finds the start, checks the north, east, west, and south neighbors 
	 * of the current dequeued (row, column) coordinate, dropping a crumb, 
	 * enqueueing the valid neighbors, and repeating the process until a full
	 * solution is attained.
	 *
	 * @param path The solution path
	 * @return The solution path
	 */
	public static LinkedList<MazeCell> solve(LinkedList<MazeCell> path)
	{
		boolean finished = false; // Whether the solution has been found
		if(maze == null)
		{
			System.out.println("No maze specified.");
			return path;
		}
		// Find the starting MazeCell. Enqueue this cell to mazeQueue
		outerloop:
			for(int i = 0; i < maze.length; i++)
			{
				for(int j = 0; j < maze[0].length; j++)
				{
					if(maze[i][j].type() == MazeCell.START)
					{
						mazeQueue.enqueue(maze[i][j]);
						break outerloop;
					}
				}
			}

		// Repeat until the exit is found:

		MazeCell curr = null; // Current maze cell being looked at

		while(!finished || !mazeQueue.isEmpty())
		{
			// If mazeQueue is empty, there is no path from 
			// the start to the exit - you are done.
			if(mazeQueue.size() == 0)
			{
				noSolution = true;
				System.out.println("No solution.");
				return path;
			}
			// Dequeue the first cell from mazeQueue to be the 
			// current cell.
			curr = mazeQueue.dequeue();

			// If the current cell is the exit, you are done.
			if(curr.type() == MazeCell.END)
			{
				finished = true;
				break;
			}

			// If the current cell already has a crumb, continue 
			// on to the next cell.
			if(curr.hasCrumb())
			{

			}

			// Drop a crumb in the current cell.
			else if(!curr.hasCrumb())
			{
				curr.dropCrumb();

				// Check each of the current cell's neighbors in the 
				// following order: North, east, south, west. If the 
				// neighboring cell is open and has no crumb, set its 
				// previous cell to the current cell, and enqueue 
				// it into mazeQueue.

				MazeCell north = null; // North neighbor to curr
				MazeCell east = null; // East neighbor to curr
				MazeCell south = null; // South neighbor to curr
				MazeCell west = null; // West neighbor to curr
				
				// Index bounds checking for north, east, south, west
				
				if(curr.row() - 1 >= 0)
				{
					north = maze[curr.row() - 1][curr.col()];
				}

				if(curr.row() + 1 < maze.length)
				{
					south = maze[curr.row() + 1][curr.col()];
				}

				if(curr.col() - 1 >= 0)
				{
					west = maze[curr.row()][curr.col() - 1];
				}

				if(curr.col() + 1 < maze[0].length)
				{
					east = maze[curr.row()][curr.col() + 1];
				}            			

				// Check to see if the neighbor should be enqueued
				
				if(north != null)
				{
					if(north.type() == MazeCell.OPEN 
							&& !north.hasCrumb())
					{
						north.setPrev(curr);
						mazeQueue.enqueue(north);
					}
					else if(north.type() == MazeCell.END)
					{
						mazeQueue.enqueue(north);
						north.setPrev(curr);
					}
				}

				if(east != null)
				{
					if(east.type() == MazeCell.OPEN 
							&& !east.hasCrumb())
					{
						east.setPrev(curr);
						mazeQueue.enqueue(east);
					}
					else if(east.type() == MazeCell.END)
					{
						mazeQueue.enqueue(east);
						east.setPrev(curr);
					}

				}

				if(south != null)
				{
					if(south.type() == MazeCell.OPEN 
							&& !south.hasCrumb())
					{
						south.setPrev(curr);
						mazeQueue.enqueue(south);
					}
					else if(south.type() == MazeCell.END)
					{
						mazeQueue.enqueue(south);
						south.setPrev(curr);
					}
				}

				if(west != null)
				{
					if(west.type() == MazeCell.OPEN 
							&& !west.hasCrumb())
					{
						west.setPrev(curr);
						mazeQueue.enqueue(west);
					}
					else if(west.type() == MazeCell.END)
					{
						mazeQueue.enqueue(west);
						west.setPrev(curr);
					}
				}
			}
		}
		
		// Add the solution path to the LinkedList 
		
		while(curr != null)
		{
			path.add(0, curr);
			curr = curr.getPrev();
		}

		hasSolution = true;
		System.out.println("Solution found.");
		return path;
	}
}
