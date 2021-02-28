package chp18;
import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter to compute the factorial");
		//with for loop
		int value = input.nextInt();
		int result = 1;
		for(int i=value; i>1; i--){
			result *= i;
		}
		System.out.println("the result is " + result);
		System.out.println("and from the recursive method we have " + factorial(value));
	}
	
	public static int factorial(int n){
		if(n==1){
			return 1;
		}
		else{
			return n * factorial(n-1);
		}
	}

}
