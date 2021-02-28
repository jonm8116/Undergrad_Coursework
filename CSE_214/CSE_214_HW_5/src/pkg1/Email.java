package pkg1;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Comparator;
/**This is the email class which is used to construct an email object
 *@version 1, August 14, 2016
 */
public class Email implements Serializable, Comparable{
	/**This is the to field in an email (the recipients)
	 */
	private String to;
	/**The cc field in an email, (carbon copy recipient)
	 */
	private String cc;
	/**The bcc field in an email, (blind carbon copy)
	 */
	private String bcc;
	/**The subject field in the email
	 */
	private String subject;
	/**The body field of the email 
	 */
	private String body;
	/**The timestamp of the email. It keeps a record of when the email
	 * was created
	 */
	private GregorianCalendar timeStamp;
	/**The constructor for the email object
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param body
	 */
	public Email(String to, String cc, String bcc, String subject, String body){
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
		timeStamp = new GregorianCalendar();
	}
	/**Prints out a string representation of the timeStamp
	 * @return String - timeStamp
	 */
	public String dateToString(){
		int hour = timeStamp.get(timeStamp.HOUR);
		int minute = timeStamp.get(timeStamp.MINUTE);
		String time = hour+":"+minute;
		int day = timeStamp.get(timeStamp.DATE);
		int month = timeStamp.get(timeStamp.MONTH)+1;
		int year = timeStamp.get(timeStamp.YEAR);
		int timeOfDay = timeStamp.get(timeStamp.AM_PM);
		String amPm="";
		if(timeOfDay==0){amPm = "AM";}
		else if(timeOfDay==1){amPm = "PM";}
		String dateValue = month+"/"+day+"/"+year;
		String value = time + amPm+ " "+ dateValue;
		return value;
	}
	/**This is the compareTo method which is used to compare the 
	 * subjects of email objects
	 * @param o
	 * @return int
	 */
	@Override
	public int compareTo(Object o){
		Email email = (Email)o;
		return(this.getSubject().compareTo(email.getSubject()));
	}
	/**toString for an email object
	 * @return String
	 */
	@Override 
	public String toString(){
		String toField = "To: " + this.to +"\n";
		String ccField = "CC: " + this.cc +"\n";
		String bccField = "BCC: " + this.bcc +"\n";
		String subject = "Subject: " + this.subject +"\n";
		String body = this.body;
		String email = toField + ccField + bccField + subject + body;
		return email;
	}
	/**This method is to check to see if two emails are equal
	 * it compares their timeStamps because two different emails
	 * cannot have been made at the same time
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj){
		Email email = (Email)obj;
		//Do you need to include all the data fields?
		//Timestamp shouldn't be the same for two emails
		if(this.timeStamp.equals(email)){
			return true;
		}
		else{
			return false;
		}
	}
	/**Accessor method for to field
	 * @return to
	 */
	public String getTo() {
		return to;
	}
	/**Mutator method for to field
	 * @param to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**Accessor method for cc field
	 * @return cc
	 */
	public String getCc() {
		return cc;
	}
	/**Mutator method for cc field
	 * @param cc
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	/**Accessor method for bcc field
	 * @return bcc
	 */
	public String getBcc() {
		return bcc;
	}
	/**Mutator method for bcc field
	 * @param bcc
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	/**Accessor method for subject
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}
	/**Mutator method for subject
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**Accessor method for body 
	 * @return body
	 */
	public String getBody() {
		return body;
	}
	/**Mutator method for body
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**Accessor method for timeStamp
	 * @return timeStamp
	 */
	public GregorianCalendar getTimeStamp() {
		return timeStamp;
	}
	/**Mutator method timeStamp
	 * @param timeStamp
	 */
	public void setTimeStamp(GregorianCalendar timeStamp) {
		this.timeStamp = timeStamp;
	}
}
