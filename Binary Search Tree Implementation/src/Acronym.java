import java.util.*;

/**
 * The Acronym Class constructs an internet acronym in the form of acronym(
 * meaning). To be a valid acronym, the acronym & meaning strings must be at 
 * least two characters, NOT start with whitespace, and NOT have a colon. There
 * are various methods (compareTo, equals, setMeaning, getMeaning, getAcronym,
 * setAcronym) to aid in the manipulation of acronyms.
 *
 * <p>Bugs: None Known
 *
 * @author Cory Jonet
 */
public class Acronym implements Comparable<Acronym>
{

	public String acronym; // This internet acronym
	public String meaning; // This internet acronym's meaning
	
	/**
	 * The Acronym method is a constructor that contructs an acronym with 
	 * an acronym & meaning. However, it first checks if acronym are valid
	 * strings based on the specifications listed in the class header.
	 *
	 * @param acronym The acronym part of the acronym to be constructed
	 * @param meaning The meaning part of the acronym to be constructed
	 */
	public Acronym(java.lang.String acronym, java.lang.String meaning)
	{
		checkAcronym(acronym);
		checkMeaning(meaning);
		
		this.acronym = acronym;
		this.meaning = meaning;
	}
	
	/**
	 * The compareTo method overrides Java's built in compareTo method to 
	 * compare acronyms based on the acronym string.
	 *
	 * @param addr The acronym to be compared to
	 * @return The comparison number. Positive if acronym is greater than addr,
	 * Zero if acronym and addr are equals, and Negative if acronym is less than
	 * addr
	 */
	public int compareTo(Acronym addr) 
	{
		return acronym.compareTo(addr.getAcronym());
	}
	
	/**
	 * The equals method compares an object to an Acronym by first casting other
	 * to an Acronym if other is an instanceof Acronym. Then equals compare 
	 * this Acronym to other using .equalsIgnoreCase.
	 *
	 * @param other The object to be compared (most likely an Acronym)
	 * @return true iff other is an Acronym object and equals this acronym
	 */
	public boolean equals(java.lang.Object other) 
	{
		if(other == null)
		{
			return false;
		}
		
		// Check if the object is an Acronym object
		if(other instanceof Acronym)
		{
			int parenthesesIndex = other.toString().indexOf('(');
			String acronymToCompare = 
					other.toString().substring(0, parenthesesIndex);
			if(acronym.equalsIgnoreCase(acronymToCompare))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * The getAcronym method simply returns the String representation of the
	 * acronym.
	 *
	 * @return The acronym
	 */
	public java.lang.String getAcronym() 
	{
		return acronym;
	}
	
	/**
	 * The getMeaning method simply returns the meaning of this acronym in String
	 * form.
	 *
	 * @return The meaning of the acronym
	 */
	public java.lang.String getMeaning() 
	{
		return meaning;
	}
	
	/**
	 * The setAcronym method replaces this acronym with a new acronym if it is 
	 * valid.
	 *
	 * @param newAcronym The new acronym that will replace this acronym if it 
	 * is valid
	 */
	public void setAcronym(java.lang.String newAcronym) 
	{
		checkAcronym(newAcronym);
		acronym = newAcronym;
	}
	
	/**
	 * The setMeaning method replaces this meaning with a new meaning if it is 
	 * valid.
	 *
	 * @param newMeaning The new meaning that will replace this meaning if it 
	 * is valid
	 */
	public void setMeaning(java.lang.String newMeaning) 
	{
		checkMeaning(newMeaning);
		meaning = newMeaning;
	}
	
	/**
	 * The toString method overrides Java's toString and manipulates Acronym
	 * objects to be printed in the form acronym(meaning)
	 *
	 * @return The toString representation of the acronym in the form of 
	 * acronym(meaning) <newLine>
	 */
	public java.lang.String toString() 
	{
		String printAcronym = acronym + "(" + meaning + ")" + "\n";
		return printAcronym;
	}
	
	/**
	 * The checkAcronym method checks to see if the acronym to be added is valid
	 * based on specifications in the class header. It returns iff it is valid
	 * and an IllegalArgumentException isn't thrown.
	 *
	 * @param acronym The acronym to be checked for validation
	 */
	public void checkAcronym(java.lang.String acronym)
	{
		if(acronym.length() < 2 || acronym.substring(0).equals(" "))
		{
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < acronym.length(); i++)
		{
			if(acronym.charAt(i) == ':')
			{
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**
	 * The checkMeaning method checks to see if the meaning to be added is valid
	 * based on the specifications in the class header. It returns true iff the
	 * meaning is valid and an IllegalArgumentException isn't thrown.
	 *
	 * @param meaning The meaning to be checked for validation
	 */
	public void checkMeaning(java.lang.String meaning)
	{
		if(meaning.length() < 2 || meaning.substring(0).equals(" "))
		{
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < meaning.length(); i++)
		{
			if(meaning.charAt(i) == ':')
			{
				throw new IllegalArgumentException();
			}
		}
	}

}