package pkg1;
/**This class is meant to define the custom exception FullPlayListException
 * This exception is thrown whenever the playlist has reached its full size
 * and another element is going to be added to it.
 * It extends the Exception class
 * @version July 12, 2016
 */
public class FullPlayListException extends Exception {
	/**The only data field in this class is the name
	 * of the playlist.
	 * This is just so that it is clear which playlist is 
	 * throwing the exception
	 */
	private String playlistName;
	/**This is the constructor of the FullPlayListException
	 * It calls the super constructor and outputs
	 * the name of the playlist that is full
	 * @param name
	 */
	FullPlayListException(String name){
		super("The " + name + " playlist is full");
	}
}
