import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The EventDB class constructs an event database, through the use of the 
 * Event class, which is full of events and a rosters with the respective event. 
 * There are accessor and mutator methods to assist in retrieving the values in 
 * the database, as well as manipulate those values.
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class EventDB {
	
	private List<Event> database;

	/**
	 * Constructs an empty database.
	 */
	public EventDB()
	{
		database = new ArrayList<Event>();
	}
	
	/**
	 * Adds an event with the given type t to the end of the database. If an 
	 * event with the type t is already in the database, the method just 
	 * returns.
	 *
	 * @param t The given event type.
	 */
	public void addEvent(String t)
	{
		if(t.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		Event event = new Event(t); // The given event t constructed
		Iterator<Event> itrAdd = database.listIterator(); // Iterator over events
		Event temp; // Temporary event to see if this equals another
		
		for(int i = 0; i < database.size(); i++)
		{
			temp = itrAdd.next();
			
			if(event.equals(temp))
			{
				return;
			}
		}
		database.add(event);	
	}
	
	/**
	 * Adds the athlete with given name n to the event with the given type t in 
	 * the database. If an event with the type t is not in the database, the 
	 * method throws a java.lang.IllegalArgumentException. If n is already in 
	 * the list of athletes roster in the event with type t, the method 
	 * just returns.
	 *
	 * @param n The given name of an athlete.
	 * @param t The given event type.
	 */
	public void addAthlete(String n, String t)
	{
		if(n.equals(null) || t.equals(null) || !this.containsEvent(t))
		{
			throw new IllegalArgumentException();
		}
		
		if(this.isRegistered(n, t))
		{
			return;
		}
		
		for(int i = 0; i < database.size(); i++)
		{
			if(database.get(i).getType().equalsIgnoreCase(t))
			{
				database.get(i).getRoster().add(n);
			}
		}		
	}

	/**
	 * Method removes the event with the type t from the database. 
	 * 
	 * @param t The given event type.
	 * @return Returns true if removal  of the event is successful; false 
	 * otherwise.
	 */
	public boolean removeEvent(String t)
	{
		if(t.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		Iterator<Event> itrRemove = database.listIterator(); // Iterator over events
		Event removeEvent; // Temp event to compare one event to another to remove
		
		for(int i = 0; i < database.size(); i++)
		{
			removeEvent = itrRemove.next();
			
			if(removeEvent.getType().equalsIgnoreCase(t))
			{
				database.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method returns true iff (i.e., if and only if) an event with the 
	 * type t is in the database.
	 *
	 * @param t The given event type
	 * @return Returns true if an event is in the database; false otherwise.
	 */
	public boolean containsEvent(String t)
	{
		if(t.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		Iterator<Event> itrEvent = database.listIterator(); // Iterator over events
		Event containsEvent; // Temporary event to comapare to another
		
		for(int i = 0; i < database.size(); i++)
		{
			containsEvent = itrEvent.next();
			
			if(containsEvent.getType().equalsIgnoreCase(t))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * The method returns true iff (i.e., if and only if) a athlete with the 
	 * name n appears in the roster for at least one event in the database.
	 *
	 * @param n The given name of the athlete
	 * @return Returns true if an athlete with name n is in the roster; 
	 * false otherwise.
	 */
	public boolean containsAthlete(String n)
	{
		if(n.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		String roster;
		for(int i = 0; i < database.size(); i++)
		{
			Iterator<String> itrRoster = 
				database.get(i).getRoster().listIterator();
			
			for(int j = 0; j < database.get(i).getRoster().size(); j++)
			{	
				roster = itrRoster.next();
				if(roster.equalsIgnoreCase(n))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method returns true iff (i.e., if and only if) the given athlete n is 
	 * registered in the event with the given type t. If an event with the type 
	 * t is not in the database, return false.
	 *
	 * @param n The given name of the athlete
	 * @param t The given event type
	 * @return Returns true if an athlete with name n is registered in the given
	 * event t; false otherwise.
	 */
	public boolean isRegistered(String n, String t)
	{
		if(n.equals(null) || t.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < database.size(); i++)
		{
			if(database.get(i).getType().equalsIgnoreCase(t))
			{
				for(int j = 0; j < database.get(i).getRoster().size(); j++)
				{
					if(database.get(i).getRoster().get(j).equalsIgnoreCase(n))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Method returns the roster (list of athletes) for the event with the 
	 * given type t. If an event with the type t is not in the database, 
	 * the method returns null.
	 *
	 * @param t The given event type t
	 * @return Returns the roster (list of athletes) for an event with given 
	 * type t; null otherwise.
	 */
	public List<String> getRoster(String t)
	{
		if(t.equals(null))
		{
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < database.size(); i++)
		{
			if(database.get(i).getType().equals(t))
			{
				return database.get(i).getRoster();
			}
		}
		return null;
	}
	
	/**
	 * Method returns the list of events in which the athlete with the given 
	 * name n is registered. If a athlete with the name n is not in the 
	 * database, return null.
	 *
	 * @param n The given name of the athlete
	 * @return A list of strings that contains all the events that the
	 * respective athelete (n) is in. If the athelete can't be found, null is
	 * returned
	 */
	public List<String> getEvents(String n)
	{ 
		if(n.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		String athlete; // Used to point at an athlete later
		
		List<String> athleteInEvents = new ArrayList<String>(); // The events an
																// athlete is in
		
		for(int i = 0; i < database.size(); i++)
		{
			Iterator<String> itrGetRoster = 
				database.get(i).getRoster().listIterator(); // Iterator over roster
			
			for(int j = 0; j < database.get(i).getRoster().size(); j++)
			{
				athlete = itrGetRoster.next();
				
				if(athlete.equalsIgnoreCase(n))
				{
					athleteInEvents.add(database.get(i).getType());
				}
			}
		}
		
		// If there actually was an event that the athlete was registered in, 
		// then return the list
		if(!athleteInEvents.get(0).equalsIgnoreCase(null))
		{
			return athleteInEvents;
		}
		return null;
	}
	
	/**
	 * Method returns an Iterator over the Event objects in the database. 
	 * The events should be returned in the order they were added to the 
	 * database (resulting from the order in which they are in the text file).
	 *
	 * @return The event iterator over the list of events in order
	 */
	public Iterator<Event> iterator()
	{
		Iterator<Event> itrEvent = database.listIterator(); // Iterator over events
		
		return itrEvent;
		
	}
	
	/**
	 * Method returns the number of events in this database.
	 *
	 * @return The number of events in the database
	 */
	public int size()
	{
		int eventCount = 0; // Number of events
		
		for(int i = 0; i < database.size(); i++)
		{
			eventCount++;
		}
		
		return eventCount;
		
	}
	
	/**
	 * Method removes the athlete with the given name n from the database (i.e., 
	 * remove the athlete from every event in which they are roster). If a 
	 * athlete with the name n is not in the database, return false; otherwise 
	 * (i.e., the removal is successful) return true.
	 *
	 * @param n The given name of the athlete
	 * @return True if the athlete with given name n is removed from every event
	 * , false otherwise
	 */
	public boolean removeAthlete(String n)
	{
		if(n.equals(null))
		{
			throw new IllegalArgumentException();
		}
		
		String athletes; // Athlete in multiple events to be removed
		boolean removal = false; // Whether or not the athlete was removed
		
		for(int i = 0; i < database.size(); i++)
		{		
			for(int j = 0; j < database.get(i).getRoster().size(); j++)
			{
				if(database.get(i).getRoster().get(j).equalsIgnoreCase(n))
				{
					database.get(i).getRoster().remove(j);
					removal = true;
				}
			}
		}
		
		// If at least one removal of the athlete was done, then return true
		if(removal)
		{
			return true;
		}
		return false;
	}
}
