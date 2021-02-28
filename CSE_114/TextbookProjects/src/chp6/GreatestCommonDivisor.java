package chp6;
import java.util.Scanner;

public class GreatestCommonDivisor {

	//This GCD program will be made using 1 additional method
	//The main method will prompt the user to enter two numbers
	//It will then call the gcd method from the main method
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Greatest Common Divisor program ");
		System.out.println("Please enter your two numbers to find their gcd");
		
		Scanner input = new Scanner(System.in);
		int num1 = input.nextInt();
		int num2 = input.nextInt();
		
		int answer = gcd(num1, num2);
		System.out.println("The greatest common divisor is " + answer);
	}
	//The gcd method will be used for the sole purpose of finding the greatest common divisor
	//So it will just take in the input from the scanner objects in the main method
	public static int gcd(int n1, int n2){
		boolean isDivisible = false;
		int gcdNum = 0;
		if(n1 < n2){
			for(int i=1; i<=n2; i++){
				if(n2 % i == 0 && n1 % i == 0){
					gcdNum = i;
				}
			}
		}
		//Divided into two cases based upon which number is larger
		else{
			for(int i=1; i<=n1; i++){
				if(n2 % i == 0 && n1 % i == 0){
					gcdNum = i;
				}
			}
		}
		return gcdNum;
	}

}
