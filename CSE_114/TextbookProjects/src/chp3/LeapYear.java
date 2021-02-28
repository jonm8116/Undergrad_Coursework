package chp3;
import java.util.Scanner;

public class LeapYear {

	public static void main(String[] args) {
		System.out.println("Leap Year Calculator");
		System.out.println("Enter a Year to determine if its a leap year");
		//A Leap Year is divisible by 4 but not by 100 OR is divisible by 400
		Scanner input = new Scanner(System.in);
		int year = input.nextInt();
		if (year % 4 == 0){
			if (year % 100 != 0){
				System.out.println(year + " Is a leap year");
			}
			else{
				System.out.println(year + " Is NOT a leap year");
			}
		}
		else if(year % 400 == 0){
			System.out.println(year + " This is a leap year");
		}
		else{
			System.out.println(year + " Is NOT  leap year");
		}
		
	}

}
