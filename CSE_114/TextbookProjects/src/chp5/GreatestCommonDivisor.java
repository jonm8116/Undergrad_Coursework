package chp5;
import java.util.Scanner;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		System.out.println("Greatest Common Divisor Calculator");
		System.out.println("Please Enter two numbers below to find their GCD");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please input your two numbers");
		int number1 = input.nextInt();
		int number2 = input.nextInt();
		
		//Greatest common divisor initialized 
		int gcd = 1;
		for(int i=1; i<=number1 && i<=number2; i++){
			if(number2 % i  ==0 && number1 % i ==0){
				gcd = i;
			}
		}
		System.out.println("The greatest common divisor is " + gcd);
	}

}
