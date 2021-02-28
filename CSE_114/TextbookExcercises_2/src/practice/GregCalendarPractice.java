package practice;
import java.util.GregorianCalendar;
public class GregCalendarPractice {

	public static void main(String[] args) {
		GregorianCalendar date = new GregorianCalendar();
		int hour = date.get(date.HOUR);
		int minute = date.get(date.MINUTE);
		String time = hour+":"+minute;
		int day = date.get(date.DATE);
		int month = date.get(date.MONTH)+1;
		int year = date.get(date.YEAR);
		int timeOfDay = date.get(date.AM_PM);
		String amPm="";
		if(timeOfDay==0){amPm = "AM";}
		else if(timeOfDay==1){amPm = "PM";}
		String dateValue = month+"/"+day+"/"+year;
		System.out.println(time+" " + amPm+ " "+ dateValue);
	
	}

}
