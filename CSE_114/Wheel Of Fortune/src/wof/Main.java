package wof;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		boolean isComplete = false;
		String playerTurn;
		int [] computerTurn = new int[26];
		
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter your name:");
		String name = input.nextLine();

		//List of objects created
		Player player = new Player(name, 0, false);
		Wheel wheel = new Wheel();
		Phrase phrase = new Phrase();
		String guesses = " ";
		
		while(isComplete==false){
			System.out.println("So " + name + " will have a go");
			System.out.println(name + " spun a " + wheel.spin());
			if(wheel.spin() != -1){
				//Remember to find a way to get rid of the large print statement
				System.out.println(phrase.blanks);
				System.out.println("Guess a letter:");
				
					if(guesses == phrase.wordFromPhrase)
						isComplete = true;
					for(char letter: phrase.wordFromPhrase.toCharArray()){
						if(guesses.indexOf(letter)==-1)
							System.out.print("_");
						
						else
							System.out.print(letter);
					}
					System.out.print("\nEnter your letter\n");
					String letter = input.nextLine();
					guesses += letter;
				
					/*for(int i=0; i<(phrase.words).size(); i++){
						int letterPlace = phrase.words.indexOf(guess);
						if(letterPlace > 0){
							System.out.print(guess);
						}
						else if(letterPlace == -1){
							System.out.print("_");
						}
					}*/
				/*while(true){
					for(String letterGuess: phrase.words){
						if(phrase.words.indexOf(guess)==-1){
							System.out.print("_");
						}
						else{
							System.out.print(guess);
						}
					}
					
				}*/
			}
			else{
				isComplete = true;
			}
				
		}
		
	}
}
