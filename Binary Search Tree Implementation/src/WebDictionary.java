import java.util.*;
import java.io.*;

/**
 * The WebDictionary creates and manipulates a sorted list of internet acronyms.  
 *
 * <p>Bugs: None Known
 *
 * @author Cory Jonet
 */
public class WebDictionary {
    /**
     * The main method provides the user interface as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine how input and 
     * output is done in the game:
     * <ul>
     *   <li>if there are no command-line arguments, then console input and 
     *   console output are used</li>
     *   <li>if there is one command-line argument, then it is treated as the
     *   name of the file from which to get input and output is sent to the
     *   console</li>
     *   <li>if there are two command-line arguments, then the first is the name
     *   of the file from which to get the input and the second is the name of 
     *   the file to which to sent the output</li>
     * </ul>
     * 
     * You may add additional static variables or methods as needed.
     */
    public static void main(String[] args) throws IOException
    {
        Scanner in = null;         // for input
        PrintStream out = null;    // for output
        SortedListADT<Acronym> dictionary = new BSTSortedList<Acronym>();  
                                   // the acronym dictionary
        boolean echo = false;	   // whether or not to echo the user input
        
        // Set up where to send input and output
        switch (args.length) {
        case 0: 
        	
        	//Add code here
        	
        	in = new Scanner(System.in);
        	out = new PrintStream(System.out);
        	
            break;

        case 1:
        	
        	//Add code here
        	
        	File file = new File(args[0].toString()); // The input file
        	
        	try
        	{
        		in = new Scanner(file);
        	}
        	catch(FileNotFoundException e)
        	{
        		System.out.println("Cannot find the specified file " 
        			+ args[0].toString());
        	}
        	
        	out = new PrintStream(System.out);
        	echo = true;
        	
            break;

        case 2: 
        	
        	//Add code here
        	
        	File fileInput = new File(args[0].toString()); // The input file
        	
        	try
        	{
        		in = new Scanner(fileInput);
        	}
        	catch(FileNotFoundException e)
        	{
        		System.out.println("Cannot find the specified file " 
        				+ args[0].toString());
        	}
        	
        	File fileOutput = new File(args[1].toString()); // The output file
        	
        	try
        	{
        		out = new PrintStream(fileOutput);
        	}
        	catch(FileNotFoundException e)
        	{
        		System.out.println("Error: Unable to save to " 
        			+ args[1].toString());
        	}
        	
        	echo = true;
        	
            break;

        default:
            System.err.println("Invalid command-line arguments");
            System.exit(0);
        }

        boolean again = true;
        while (again) 
        {
            out.print("enter choice (a, d, f, p, q): ");
            out.flush();
            String input = in.nextLine();
            if (echo) out.println(input);
            if (input.length() == 0) 
            {
                out.println("invalid input");
            }

            else 
            {
                // We will have our program be case-insensitive, so we'll 
                // convert the first character to lower-case.
                char choice = input.substring(0, 1).toLowerCase().charAt(0);
                String remainder = "";  // used to hold the remainder of input
                // trim off any leading or trailing spaces
                remainder = input.substring(1).trim();

                switch (choice) 
                {
                
                // add an acronym 
                // format: a acronym:meaning
                case 'a':  
                	
                	//Add code here
                	
                	int colonIndex; // Index of the colon
                	String acronym = remainder; // The acronym to add
                	colonIndex = acronym.indexOf(':'); // Index of colon
                	
                	// Strip out meaning
                	String meaning = 
                			acronym.substring(colonIndex + 1, acronym.length());
                	
                	acronym = acronym.substring(0, colonIndex); // Strip out acronym
                	
                	// The new acronym to add
                	Acronym acronymToAdd = new Acronym(acronym, meaning); 
                	
                	dictionary.insert(acronymToAdd);
                	
                    break;

                // delete an acronym
                // format: d acronym
                case 'd':  
                	
                	//Add code here
                	
                	// A dummy acronym to delete with same acronym to delete as
                	// specified
                	Acronym acronymToDelete = new Acronym(remainder, "aa");
                	
                	if(dictionary.lookup(acronymToDelete) == null)
                	{
                		out.println("Not Present");
                		break;
                	}
                	
                	// Whether the acronym to be deleted actually was deleted
                	boolean deleted = dictionary.delete(acronymToDelete);
                	
                	if(deleted)
                	{
                		
                	}
                	else
                	{
                		out.println("Not Present");
                	}
                	
                	break;
                    
                // find an acronym
                // format: f acronym
                case 'f':   
                	
                    //Add code here
                	
                	// Dummy acronym with the actual acronym to be looked up
                	Acronym acronymToLookUp = new Acronym(remainder, "aa");
                	
                	// The returned acronym that was looked up
                	Acronym acronymLookedUp = dictionary.lookup(acronymToLookUp);
                	
                	if(acronymLookedUp == null)
                	{
                		out.println("Not Present");
                	}
                	else
                	{
                		out.print(acronymLookedUp.toString());
                	}
                	
                    break;
                    
                // print the contents of the dictionary in sorted order
                // format: p
                case 'p':  
                	
                    //Add code here 
                	
                	// Iterator over the dicionary
                	Iterator<Acronym> itr = dictionary.iterator();
                	
                	while(itr.hasNext())
                	{
                		out.print(itr.next().toString());
                	}
                	
                    break;
                    
                // quit - this does not need to be changed
                // format: q
                case 'q':  
                    again = false;
                    out.println("done");
                    break;

                default:   // anything else is invalid
                    out.println("invalid choice");
                } // end switch
            } // end else
        } // end while
        in.close();
        out.close();
    } // end main
}
