package chp5;
import java.util.Scanner;

public class StringPalindrome {

	public static void main(String[] args) {
		//This program is used to check a string palindrome of any length
		System.out.println("Welcome to the String Palindrome Checker");
		System.out.println("Please enter a string to check if its a palindrome");
		
		Scanner input = new Scanner(System.in);
		String guess = input.nextLine();
		//Index of char in first position of string
		int low = 0;
		//Index of last char in string
		int high = guess.length()-1;
		
		boolean isPalindrome = true;
		while(low<high){
			if(guess.charAt(low)!=guess.charAt(high)){
				isPalindrome = false;
				break;
			}
			low++;
			high--;
		}
		if(isPalindrome){
			System.out.println(guess + " is a palindrome");
		}
		else{
			System.out.println(guess + " is NOT a palindrome");
		}
	}

}
