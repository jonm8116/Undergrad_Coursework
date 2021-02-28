package chp12;
import java.util.Scanner;

public class QuotientWithException {
	
	//This program is used to go over exceptions in Java
	//This first program is used to test an Arithmetic Exception
	public static double quotient(int number1, int number2){
		if(number2 == 0){
			throw new ArithmeticException("Divisor cannot be zero");
		}
		return (double)number1 / (double)number2;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter two integers");
		int number1 = input.nextInt();
		int number2 = input.nextInt();
		try{
			double result = quotient(number1, number2);
			System.out.println(number1 + "/" + number2 + " is " + result);
		}
		catch(ArithmeticException ex){
			System.out.println("Exception: an integer cannot be divided by zero ");
		}
		
		System.out.println("Excution continues.... ");
	}

	
}
