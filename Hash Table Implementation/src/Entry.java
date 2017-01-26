/**
 * The Entry.java class stores a person's name and phone number. It includes
 * four different hash functions: A fixed hash function, which always returns 11;
 * a function that hashes on the String (name) only; a function that hashes on 
 * the phone number only; and a function that was created creatively
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class Entry 
{
	
	public static final int STATIC = 0;
	public static final int STRING = 1;
	public static final int LONG = 2;
	public static final int BOTH = 3;
	
	private String name; // Name entry
	private long phone; // Phone entry
	private int hashType; // The type of hashing that will be done
	
	/**
	 * Constructs an entry with name, phone, and hashType
	 *
	 * @param name The name entry
	 * @param phone The phone entry
	 * @param hashType The type of hashing that will be done
	 */
	public Entry(String name, long phone, int hashType) 
	{
		this.name = name;
		this.phone = phone;
		this.hashType = hashType;
	}
	
	/**
	 * Overrides java.lang.toString() to print the name and phone
	 *
	 * @return The newly constructed data entry to display
	 */
	@Override
	public String toString() 
	{
		return name + ":" + phone;
	}
	
	/**
	 * Overrides java.lang.equals() to see if the object is an instance of an
	 * Entry then compare this entry with that entry by name and phone.
	 *
	 * @param other The other object to compare
	 * @return True iff the object is an Entry object and the names and phones
	 * are the same
	 */
	@Override
	public boolean equals(Object other) 
	{
		if (other instanceof Entry) 
		{
			Entry that = (Entry) other; // Cast the object to an Entry object
			
			if (that.name.equals(this.name) && that.phone == this.phone)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns a hashCode for this object. You should complete the three
	 * different hash functions marked below.
	 * 
	 * Make note that when you write a hash function, it must always return
	 * the same value for the same object. In other words, you should not use
	 * any randomness to generate a hash code.
	 */
	@Override
	public int hashCode() 
	{
		if (hashType == STRING) 
		{
			/* Hash on the String name only. Java has a built-in hashing 
			 * function for Strings; see 
			 * http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/String.html#hashCode%28%29
			 * */
			
			//Replace me with your hash function
			
			return name.hashCode(); 
		}
		else if (hashType == LONG) 
		{
			/* Hash on the phone number only. You may write whatever hash 
			 * function you like, as long as it involves the phone number 
			 * in some way. Mod and/or division both work well for this. */
			
			//Replace me with your hash function
			
			long mod = phone * (phone % 11); // The hash function
			
			return (int) mod;
		}
		else if (hashType == BOTH) 
		{
			/* Come up with your own hashing function. Your hashing function
			 * should have better performance than each of the other functions 
			 * on at least one of the given input files. 
			 * You may use the name, phone number, or both. */
			
			//Replace me with your hash function
			
			//Basically did a regular hash on the string then for the phone,
			// I modded by the string length and multiplied it by the phone
			// then added the two hashcodes together
			
			int stringHash = name.hashCode(); // String part of the hashcode
			
			long mod = phone * (phone % name.length()); // Phone part of the hashcode
			
			long total = mod + stringHash; // The total hashcode
			
			return (int) total;
		}
		else 
		{
			//Fixed hash function
			return 11;
		}
	}
}
