package chp3;
import java.util.Scanner;

public class IfElseStatement {

	public static void main(String[] args) {
		//Heads and tails game
		int computerValue = (int)(Math.random()*2);
		System.out.println("Welcome to the Heads or tails game");
		System.out.println("Please select 0 for heads and 1 for tails");
		
		Scanner input = new Scanner(System.in);
		int humanGuess = input.nextInt();
		if(humanGuess == 1 || humanGuess == 0){
			if(humanGuess == computerValue){
				System.out.println("Ayy you got it right");
			}
			else{
				System.out.println("Sorry incorrect");
			}
		}
		else{
			System.out.print("Wrong input. Only 1 or 0");
		}

	}
}
