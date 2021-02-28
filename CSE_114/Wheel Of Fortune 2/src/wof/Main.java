package wof;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Main extends Board {

	public Main() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException {
		boolean isComplete=false;
		boolean userTurn = true;
		int playerScore=0;
		int cpuScore = 0;
		char [] cpuArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
							'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		String [] cpuArrayString = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
						"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		
		//Objects created
		Wheel wheel = new Wheel();
		Scanner input = new Scanner(System.in);
		Player player = new Player();
		Player cpu = new Player("McDonnell-5000", 0);
		Board phrase = new Board();
		Random random = new Random();
		
		//Prompt the user for name
		System.out.println("What is your name?");
		player.setName(input.nextLine());
		
		
		/* This loop will control most of the game
		 * This loop will only be for the player's turn 
		 * Maybe use another loop for the computer with similar code
		 */
		//!isComplete (is the same as) isComplete == false
		int spinValue=0;
		while(!isComplete){
		//Add the if statement
		//if(userTurn==true)
			spinValue = wheel.spin();
			if(spinValue>0){
				if(userTurn == true){
					System.out.println(player.getName() + " you spun a " + spinValue);
					//Break this up into multiple print statements 
					//The saying is too long
					System.out.println("Guess a letter or a phrase");
					//This print statement is printed out first to show the blanks
					//To the individual
					System.out.println(phrase.getPhrase());
					String guess = input.nextLine();
					
					//There will be two if statements here
					//One statement will be if the user only inputs one letter
					//The other will be for a longer length phrase
					if(guess.length()==1){
						phrase.addToGuess(guess);
						phrase.getPhrase();
						//This if statement checks if the guess is in the phrase
						//That chain of methods will return a value greater than 0
						//if the letter is in the phrase
						if(phrase.getPhrase().indexOf(guess)>0){
							playerScore += spinValue;
							
						}
						else{
							//Let this switch to player's turn
							userTurn = false;
							System.out.println("Now " + cpu.getName() + " will go");
						}
					}
					if(guess.length()>1){
						if(guess.equals( phrase.phrases.get(randomValue))){
							System.out.println(phrase.phrases.get(randomValue));
							System.out.print("you won!");
							playerScore += spinValue;
							isComplete=true;
							System.out.println("Would you like to play again??? (Y/N)");
							String choice = input.nextLine();
							if(choice=="Y"){
								isComplete = false;
							}
							else{
								isComplete = true;
								System.out.println("Sorry one time only");
							}
						}
					}
					System.out.println("This is your score " + playerScore);
				}
				if(userTurn==false){
					//This is where the AI will play
					System.out.println(cpu.getName() + " you spun a " + spinValue);
					System.out.println(phrase.getPhrase());
					//char cpuGuess = cpuArray[random.nextInt(cpuArray.length)];
					phrase.addToGuess(cpuArrayString[random.nextInt(cpuArray.length)]);
					String cpuGuess = phrase.getPhrase();
					System.out.println(cpu.getName() + " guessed " + "\n"+ phrase.getPhrase());
					
					if(phrase.getPhrase().indexOf(cpuGuess)>0){
						
						cpuScore += spinValue;
						System.out.println(cpu.getName() + " your score " +cpuScore);
						
					}
					else{
					
						userTurn = true;
					}
				}
			}
			
		}
	}

}
