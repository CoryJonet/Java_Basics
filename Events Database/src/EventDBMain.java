/**
 * The EventDBMain class is the main class in this program that utilizes the 
 * EventDB class to keep track of the athletes and the events they are in, as 
 * well as other specific information.
 *
 * <p>Bugs: 
 *
 * @author Cory Jonet
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EventDBMain {
    public static void main(String[] args) {

        // *** Add code for steps 1 - 3 of the main method ***
    	
    	// Error checking for no arguments in args
    	if(args.length != 1)
    	{
    		System.out.println("Usage: java EventDBMain FileName");
    		System.exit(0);
    	}
  
    	File file; // The file to be imported
    	Scanner in = null; // The scanner to read the file
    	try
    	{
    		file = new File(args[0]);
    		in = new Scanner(file);
    		
    	}
    	catch(FileNotFoundException e)
    	{
    		System.out.println("Error: Cannot access input file");
    		System.exit(0);
    	}
    	
    	EventDB eventDB = new EventDB(); // Create the new event database
    	
    	while(in.hasNextLine())
    	{
    		String line = in.nextLine();
    		String[] temp = line.split(","); // Split between every comma
    		String name = temp[0]; // Name gets the first argument
    		
    		for(int i = 1; i < temp.length; i++)
    		{
    			// If an event with this name is already in the database, just
    			// add the athlete
    			if(eventDB.containsEvent(temp[i]))
    			{
    				eventDB.addAthlete(name, temp[i]);
    			}
    			// Otherwise add the event/athlete to the database
    			else
    			{
    				eventDB.addEvent(temp[i]);
    				eventDB.addAthlete(name, temp[i]);
    			}
    		}
    	}	

        Scanner stdin = new Scanner(System.in);  // for reading console input
        boolean done = false;
        while (!done) {
            System.out.print("Enter option ( cdprswx ): ");
            String input = stdin.nextLine();

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) { // if there is an argument
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 

	                switch (choice) { // the commands that have arguments
	
	                case 'c':
	                	
	                    // *** Add code to implement this option ***
	                	if(!eventDB.containsEvent(remainder))
	                	{
	                		System.out.println("Event not found");
	                	}
	                	else
	                	{
	                		eventDB.removeEvent(remainder);
	                		System.out.println("Event removed");
	                	}
	                	
	                    break;
	
	                case 'p':
	                	
	                    // *** Add code to implement this option ***
	                	
	                	// If eventDB contains the athlete name then print the 
	                	// events they are in
	                	if(eventDB.containsAthlete(remainder))
	                	{
	                		System.out.println(eventDB.
	                			getEvents(remainder).toString().
	                				substring(1, eventDB.getEvents(remainder)
	                						.toString().indexOf(']')));
	                	}
	                	
	                	// Otherwise print the error message
	                	else
	                	{
	                		System.out.println("Athlete not found");
	                	}
	                	
	                    break;
	
	                case 'r':
	                	
	                    // *** Add code to implement this option ***
	                	
	                	// If the event can't be found in eventDB
	                	if(!eventDB.containsEvent(remainder))
	                	{
	                		System.out.println("Event not found");
	                	}
	                	
	                	//If no athletes are registered in the event
	                	else if(eventDB.getRoster(remainder).equals(null))
	                	{
	                		System.out.println("None");
	                	}
	                	
	                	// Otherwise print the athletes in order
	                	else
	                	{
	                		System.out.println(eventDB.
	                			getRoster(remainder).toString()
	                			.substring(1, eventDB.getRoster(remainder)
                						.toString().indexOf(']')));
	                	}
	                	
	                    break;
	
	                case 's':
	                    // The following code reads in a comma-separated sequence 
	                    // of strings.  If there are exactly two strings in the 
	                    // sequence, the strings are assigned to name1 and name2.
	                    // Otherwise, an error message is printed.
	                    String[] tokens = remainder.split("[,]+");
	                    if (tokens.length != 2) {
	                        System.out.println("need to provide exactly two " +
	                        		"names");
	                    }
	                    else {
	                        String name1 = tokens[0].trim();
	                        String name2 = tokens[1].trim();
	                        
	                        // *** Add more code to implement this option ***
	                        
	                        if(!eventDB.containsAthlete(name1) || 
	                        		!eventDB.containsAthlete(name2))
	                        {
	                        	System.out.println("None");
	                        }
	                        else
	                        {	
	                        	String common = ""; // Stores common events
	                        	
	                        	// Go through the name1 athlete's events
	                        	for(int i = 0; i < eventDB.getEvents(name1)
	                        		.size(); i++)
	                        	{
	                        		
	                        		// Go through name2 athlete's events
	                        		for(int j = 0; j < eventDB
	                        			.getEvents(name2).size(); j++)
	                        		{
	                        			
	                        			// Compare both athletes' events
	                        			if(eventDB.getEvents(name1)
	                        				.get(i).equalsIgnoreCase(eventDB
	                        						.getEvents(name2).get(j)))
	                        			{
	                        				
	                        				// If there is a match, add it to 
	                        				// the String temp variable
	                        				common += eventDB.getEvents(name2)
	                        						.get(j) + ", ";
	                        			}	
	                        		}
	                        	}
	                        	
	                        	// To get rid of the extra comma at the end
	                        	common = common.substring(0, 
	                        		common.length() - 2);
	                        	System.out.println(common);
	                        }
	                    }
	                    
	                    break;
	
	                case 'w':
	                	
	                    // *** Add code to implement this option ***
	                	
	                	if(!eventDB.containsAthlete(remainder))
	                	{
	                		System.out.println("Athlete not found");
	                	}
	                	else
	                	{
	                		eventDB.removeAthlete(remainder);
	                		System.out.println(remainder + " " 
	                			+ "withdrawn from all events");
	                	}
	                		
	                    break;
	
	                default: // ignore any unknown commands
                    	System.out.println("Incorrect command.");
	                	break;
	                
	                } // end switch
                } // end if
                else { //if there is no argument
                	switch (choice) { // the commands without arguments
                	
                	case 'd': 
                		
	                    // *** Add code to implement this option ***
                		
                		statistics(eventDB); // Wrote a method for this
	                    break;
	                    
                	case 'x':
	                    done = true;
	                    System.out.println("exit");
	                    break;
	                    
                	default:  // a command with no argument
                		System.out.println("Incorrect command.");
	                    break;
                	} // end switch
                } // end else  
           } // end if
        } // end while
    } // end main
    
    /**
     * This method computes all the statistic from the event database. These 
     * include unique events/athletes, # of athletes/event and # of events/
     * athlete, most popular event, and lastly, least popular event.
     *
     * @param EventDB The event database that is used to compute statistics
     */
    public static void statistics(EventDB eventDB)
    {
    	int most = 0; // Most athletes/event
    	int least = 0; // Least athletes/event
    	int sum = 0;  // Sum of athletes/event
    	
    	List<String> uniqAthletes = new ArrayList<String>(); // Unique athletes list
    	List<Event> popular = new ArrayList<Event>(); // Popular events list
    	List<Event> unPopular = new ArrayList<Event>(); // Unpopular events list
    	
    	Iterator<Event> itrEvent = eventDB.iterator(); // Iterator over events
    	
    	// Loop through the eventDB and compute the stats 
    	for(int i = 0; i < eventDB.size(); i++)
    	{
    		Event currEvent = itrEvent.next(); // Iterator for events
    		sum += currEvent.getRoster().size();
    		int rosterSize = currEvent.getRoster().size(); // The roster size
    		
    		if(rosterSize >= most)
    		{
    			most = rosterSize;
    			for(int k = 0; k < popular.size(); k++)
    			{
    				if(popular.get(k).getRoster().size() < rosterSize)
    				{
    					popular.remove(k);
    				}
    			}
    			popular.add(currEvent);
    		}
    		if(rosterSize <= least || least == 0)
    		{
    			least = rosterSize;
    			unPopular.add(currEvent);
    		}
    		for(int j = 0; j < currEvent.getRoster().size(); j++)
    		{
    			if(!uniqAthletes.contains(currEvent.getRoster().get(j)))
    			{
    				uniqAthletes.add(currEvent.getRoster().get(j));
    			}
    		}
    	}
    	
    	int athleteMax = 0; // Maximum events/athlete
		int athleteMin = 0; // Minimum events/athlete
		int athleteSum = 0; // The sum of events/athlete
		
		// Retrieve the stats for athleteMax, athleteMin, athleteSum to compute
		// the other stats for events/athlete
    	for(int i = 0; i < uniqAthletes.size(); i++)
    	{
    		// If athleteMax is less than the value in uniqAthletes, then 
    		// athleteMax gets that value
    		if(athleteMax <= eventDB.getEvents(uniqAthletes.get(i)).size())
    		{
    			athleteMax = eventDB.getEvents(uniqAthletes.get(i)).size();
    		}
    		
    		// If athleteMin is greater than the value in uniqAthletes, then
    		// athleteMin gets that value
    		if(athleteMin >= eventDB.getEvents(uniqAthletes.get(i)).size() 
    			|| athleteMin == 0)
    		{
    			athleteMin = eventDB.getEvents(uniqAthletes.get(i)).size();
    		}
    		athleteSum += eventDB.getEvents(uniqAthletes.get(i)).size();
    	}
    	
    	// Average athletes/event
    	double average = Math.round(sum / eventDB.size());
    	
    	// Average events/athlete
    	double athleteAverage = Math.round(athleteSum / uniqAthletes.size());

    	
    	
    	// Print the number of events and athletes
    	System.out.println("Events: " + eventDB.size() + ", Athletes : " 
    	+ uniqAthletes.size());
    	
    	// Print the number of athletes/event and statistics
    	System.out.println("# of athletes/event: most " + most + 
    			", least " +  least + ", average " + average);
    	
    	// Print the number of events/athlete and statistics
    	System.out.println("# of events/athlete: most " + athleteMax + 
    			", least " +  athleteMin + ", average " + athleteAverage);
    	
    	// Print the most popular event(s)
    	System.out.print("Most popular: ");
    	
    	// Loop through the popular list and print them
    	for(int i = 0; i < popular.size(); i++)
    	{
    		// Basically to get rid of the extra comma after the last event
    		if(i == popular.size() - 1)
    		{
    			System.out.print(popular.get(i).getType());
    		}
    		else
    		{
    			System.out.print(popular.get(i).getType() + ", ");
    		}
    	}
    	System.out.println(" [" + popular.get(0).getRoster().size() + "]");
    	
    	
    	// Print the least popular event(s)
    	System.out.print("Least popular: ");
    	
    	// Loop through the unPopular list and print them
    	for(int i = 0; i < unPopular.size(); i++)
    	{
    		// Basically to get rid of the extra comma after the last event
    		if(i == unPopular.size() - 1)
    		{
    			System.out.print(unPopular.get(i).getType());
    		}
    		else
    		{
    			System.out.print(unPopular.get(i).getType() + ", ");
    		}
    	}
    	System.out.println(" [" + unPopular.get(0).getRoster().size() + "]");
    }
}
