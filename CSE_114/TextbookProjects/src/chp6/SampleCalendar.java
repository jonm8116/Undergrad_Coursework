package chp6;

public class SampleCalendar {

	public static void main(String[] args) {
		System.out.println("Sample Calendar");
		int totalDays = 433;
		int dayOfWeek = (totalDays + 3) % 7;
		System.out.println("Day of week is " + dayOfWeek);
	}

}
