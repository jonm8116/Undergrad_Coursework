package chp6;
import java.util.Scanner;

public class Calendar {
	//This program is used to print out the calendar for a specified month and year
	//This is done by taking in a user's input
	//The problem decomposition for this problem starts with two large problems
	//printCalendar and printMonth

	
	//AMOUNT OF TIME PROGRAMMING = X HOURS
	private static int daysBeforeMonth = 0;
	private static int year;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Print Calendar Program");
		System.out.println("Please enter the year");
		/*int*/year = input.nextInt();
		System.out.println("Please enter the numerical month");
		int month = input.nextInt();
		printMonthTitle(month, year);
		//System.out.println(startDate(month, year));
		printMonthBody(startDate(month, year), month);
//		if(isLeapYear(year)){
//			System.out.println(year + " This is a leap year");
//		}
//		else{
//			System.out.println(year + " This is NOT a leap year");
//		}
	}
	
	//This is one of the two methods that problem is divided into 
	//This will print out the month title or the month header
	public static void printMonthTitle(int month, int year){
		String[] monthArr = {"January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November", "December"};
		String monthDisplay = monthArr[month - 1];
		System.out.println("         " + monthDisplay + " " + year);
		//Prints line under month and year
		for(int i=0; i<= 40; i++){
			System.out.print("-");
		}
		System.out.println();
		String week = "Sun " + "Mon " + "Tue " + "Wed " + "Thu " + "Fri " + "Sat";
		System.out.println(week);
	}
	//This method is used to find the first day in a month
	//In order to calculate day of week you use the following formula:
	//dayOfWeek = (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7 
	//The value that this formula reproduces rotates around a cycle from 0 to 6
	//So just set the days according to the day in the week
	public static int startDate(int month, int year){
		final int START_DAY_FOR_JAN_1_1800 = 3; //Day of the week back then was Wednesday
		int yearDifference = Math.abs(year - 1800);
		int NumberOfDaysYears = yearDifference * 365; //Number of days in year
		int totalNumberOfDays = NumberOfDaysYears + daysBeforeMonth(month);
		int startDate = (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7;
		return startDate;
	}
	//This method is used to print out the month body 
	//It prints out the month body underneath the month title 
	public static void printMonthBody(int startDate, int month){
		int daysInMonth = daysBeforeMonth(month + 1) - daysBeforeMonth(month);
		//Use nested loops for printing out the month body??
		int count = startDate;
		//This first for loop will be used to set a new line when value%6== 0
		for(int i =0; i<startDate; i++){
			System.out.print("   ");
		}
			for(int j=1; j<=daysInMonth; j++){
				System.out.printf("%4d", j);
				count++;
				if(count % 7 == 0){
					System.out.println();
				}
			}

	}
	//This method is used to calculate whether the year is a leap year
	public static boolean isLeapYear(int year){
		//A leap year is divisible by 4 but not by 100 OR divisible by 400
		boolean isLeapYear = false;
		if(year % 4==0 && year % 100 != 0){
			isLeapYear = true;
		}
		else if(year % 400 == 0){
			isLeapYear = true;
		}
		else{
			isLeapYear = false;
		}
		return isLeapYear;
	}
	//This method is used to determine how many days between months
	public static int daysBeforeMonth(int month){ //Change to int returning method??
		//January 
		if(isLeapYear(year)){
		switch(month){
		case 1: daysBeforeMonth = 0; //In January 
				break;
		case 2: daysBeforeMonth = 31; //In February 
				break;
		case 3: daysBeforeMonth = 31 + 29; //In March
				break;
		//With february and leap year this is gonna be annoying 
		case 4: daysBeforeMonth = 31 + 29 + 31; //April
				break;
		case 5: daysBeforeMonth = 31 + 29 + 31 + 30; //May 
				break;
		case 6: daysBeforeMonth = 31 + 29 + 31 + 30 + 31; //June
				break;
		case 7: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30; //July
				break;
		case 8: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30 + 31; //August
				break;
		case 9: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31; //September
				break;
		case 10: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30; //October
				break;
		case 11: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31; //November
				break;
		case 12: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30; //December 
				break;
		//This 13th case is used for calculating the days in the month for the printMonthBody Method
		case 13: daysBeforeMonth = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31;
				break;
		}
		}
		else {
			switch(month){
			case 1: daysBeforeMonth = 0; //In January 
					break;
			case 2: daysBeforeMonth = 31; //In February 
					break;
			case 3: daysBeforeMonth = 31 + 28; //In March
					break;
			//With february and leap year this is gonna be annoying 
			case 4: daysBeforeMonth = 31 + 28 + 31; //April
					break;
			case 5: daysBeforeMonth = 31 + 28 + 31 + 30; //May 
					break;
			case 6: daysBeforeMonth = 31 + 28 + 31 + 30 + 31; //June
					break;
			case 7: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30; //July
					break;
			case 8: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31; //August
					break;
			case 9: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31; //September
					break;
			case 10: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30; //October
					break;
			case 11: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31; //November
					break;
			case 12: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30; //December 
					break;
			case 13: daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31;
					break;
		}
		}
		return daysBeforeMonth;
	}
	
}
