package pkg1;
import java.util.ArrayList;
/**This class constructs the queue for the elevator requests
 * The class extends ArrayList and implements the standard methods
 * of a queue 
 *@version 1, July 29, 2016
 */
public class RequestQueue extends ArrayList {
	/**This adds a request to the queue
	 * @param element
	 */
	public void enqueue(Request element){
		//think about using the add method with the index
		super.add(element);
	}
	/**This removes the element at the front of the queue
	 * @return Request
	 */
	public Request dequeue(){
		//double check to make sure this is the first position
		Request item = (Request)super.get(0);
		super.remove(0);
		return item;
	}
	/**This returns the size of the queue
	 * @return size
	 */
	@Override
	public int size(){
		return super.size();
	}
	/**This returns whether the queue is empty
	 * @return boolean - whether the queue is empty
	 */
	public boolean isEmtpy(){
		return super.isEmpty();
	}
	
}
