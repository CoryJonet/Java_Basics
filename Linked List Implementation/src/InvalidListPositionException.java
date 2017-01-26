/**
 * This class extends the Exception class and is a checked exception. It is 
 * thrown whenever a position is invalid in the Movie Queue program.
 *
 * <p>Bugs: None known
 *
 * @author Cory Jonet
 */
public class InvalidListPositionException extends Exception 
{
	/**
	 * The constructor for the InvalidListPositionException, which
	 * sends the message when an InvalidListPositionException is 
	 * thrown.
	 *
	 */
	public InvalidListPositionException()
	{
		super();
	}
}
