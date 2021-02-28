package pkg1;
import java.util.Comparator;
/**This class is used to define a date comparator in descending order
 * @version 1, August 14, 2016
 */
public class ReverseDateComparator implements Comparator{
	/**This is the compare method used to compare dates
	 */
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return(email2.getTimeStamp().compareTo(email1.getTimeStamp()));
	}
}
