package pkg1;
import java.util.Comparator;
/**This is the date comparator class, it is used to compare the timestamps 
 * of two email objects
 * This is later used to sort emails by their timestamps
 * @version 1, August 14, 2016
 */
public class DateComparator implements Comparator{
	/**This is the compare method that is used to compare the
	 * timestamps of two different email objects
	 */
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return(email1.getTimeStamp().compareTo(email2.getTimeStamp()));
	}
}
