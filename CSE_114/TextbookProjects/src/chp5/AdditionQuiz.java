package chp5;
import java.util.Scanner;

public class AdditionQuiz {

	public static void main(String[] args) {
		System.out.println("Welcome to Addition Quiz");
		System.out.println("You will be prompted with the addition of single digit numbers");
		System.out.println("If you get it right, you pass the quiz if you don't you keep going");
		
		Scanner input = new Scanner(System.in);
		boolean isComplete = false;
		
		while(!isComplete){
			int a = (int)(Math.random() *10);
			int b = (int)(Math.random()*10);
			System.out.println("what does " + a + "+ " + b + " =");
			int guess = input.nextInt();
			if (guess == a +b){
				System.out.println("You got it right!");
				isComplete = true;
			}
			else{
				System.out.println("Incorrect Try again");
			}
			
		}
	}

}
