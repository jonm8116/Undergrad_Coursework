package chp3;
import java.util.Scanner;

public class ChineseZodiac {

	public static void main(String[] args) {
		System.out.println("Welcome to Chinese Zodiac Calculator");
		System.out.println("Please enter a year to determine your zodiac");
		
		Scanner input = new Scanner(System.in);
		int year = input.nextInt();
		//The zodiac is based upon a 12 digit system
		
		//You add a break statement for each case in the switch statement
		//If you don't add a break then the program will continue to modularly divide the year
		//And will output more zodiacs even though it shouldn't 
		switch(year % 12){
		case 0:
			System.out.println(year + " Your zodiac is year of the monkey");
			break;
		case 1:
			System.out.println(year + " zodiac rooster");
			break;
		case 2: 
			System.out.println(year + " zodiac dog");
			break;
		case 3: 
			System.out.println(year + " zodiac pig");
			break;
		case 4: 
			System.out.println(year + " zodiac rat");
			break;
		case 5: 
			System.out.println(year + " zodiac ox");
			break;
		case 6: 
			System.out.println(year + " zodiac tiger");
			break;
		case 7: 
			System.out.println(year + " zodiac rabbit");
			break;
		case 8:
			System.out.println(year + " dragon");
			break;
		case 9:
			System.out.println(year + " snake");
			break;
		case 10: 
			System.out.println(year + " horse");
			break;
		case 11:
			System.out.println(year + " sheep");
			break;
		}
		
		System.out.println("Thank you for using the program!");
	}

}
