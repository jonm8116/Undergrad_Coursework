package chp3;
import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		//Palindrome checker
		System.out.println("Palindrome checker");
		System.out.println("PLease enter a 3-digit number");
		
		Scanner input = new Scanner(System.in);
		int guess = input.nextInt();
		String number = String.valueOf(guess);
		char ch[] = number.toCharArray();
		if(ch[0] == ch[2]){
			System.out.println("It is a palindrome");
		}
		else{
			System.out.println("It is NOT a palindrome");
		}
	}

}
