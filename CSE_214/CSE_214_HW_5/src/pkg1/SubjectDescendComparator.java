package pkg1;
import java.util.Comparator;
/**This is the comparator class used to order emails
 * by subjects in descending alphabetical order
 * @version 1, August 14, 2016
 */
public class SubjectDescendComparator implements Comparator{
	/**This is the compare method used to compare subjects
	 */
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return(email2.getSubject().compareTo(email1.getSubject()));
	}
}
