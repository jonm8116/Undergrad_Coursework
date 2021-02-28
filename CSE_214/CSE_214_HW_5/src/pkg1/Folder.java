package pkg1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**This is the folder class for the mailbox program
 * It defines a folder that can hold a list of emails
 * @version 1, August 14, 2016
 */
public class Folder implements Serializable{
	/**An arraylist is used to hold all the emails for 
	 * the folder
	 */
	private ArrayList<Email> emails;
	/**Defines the name of the folder
	 */
	private String name;
	/**Defines the currentSorting method
	 */
	private String currentSortingMethod;
	/**This is the constructor for the folder object
	 * @param name
	 */
	public Folder(String name){
		this.name = name;
		this.emails = new ArrayList<>();
	}
	/**This adds an email to the given folder
	 * @param email
	 */
	public void addEmail(Email email){
		emails.add(email);
	}
	/**This removes an email from the given folder
	 * @param index
	 * @return email
	 */
	public Email removeEmail(int index){
		return emails.remove(index);
	}
	/**Sorts the emails in the folder by
	 * its subject ascending 
	 */
	public void sortBySubjectAscending(){
		Collections.sort((List)emails);
		this.setCurrentSortingMethod("sort by subject ascending");
	}
	/**Sorts the emails in the folder by
	 * its subject descending
	 */
	public void sortBySubjectDescending(){
		Collections.sort((List)emails, new SubjectDescendComparator());
		this.setCurrentSortingMethod("current Sorting Method");
	}
	/**Sorts the emails in the folder by
	 * date ascending
	 */
	public void sortByDateAscending(){
		Collections.sort((List)emails, new DateComparator());
		this.setCurrentSortingMethod("sort by date ascending");
	}
	/**Sorts the emails in the folder by date descending
	 */
	public void sortByDateDescending(){
		Collections.sort((List)emails, new ReverseDateComparator());
		this.setCurrentSortingMethod("sort by date descending");
	}
	/**Accessor method for folder name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**Mutator method for folder name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**Accessor method for currentSortingMethod
	 * @return currentSortingMethod
	 */
	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}
	/**Mutator method for currentSortingMehtod
	 * @param currentSortingMethod
	 */
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}
	/**Accessor method for arraylist of emails
	 * @return emails
	 */
	public ArrayList<Email> getEmails(){
		return emails;
	}
	/**Mutator method for arraylist of emails
	 * @param emails
	 */
	public void setEmails(ArrayList<Email> emails){
		this.emails = emails;
	}
}
