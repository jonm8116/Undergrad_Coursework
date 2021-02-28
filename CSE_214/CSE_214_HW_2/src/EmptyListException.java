/**The empty list exception is thrown when ever an operation is done 
 * on the list but the list does not contain any nodes
 * @version 1, July 20, 2016
 */
public class EmptyListException extends Exception{
	
	public EmptyListException(){
		super("The list is empty");
	}
}
