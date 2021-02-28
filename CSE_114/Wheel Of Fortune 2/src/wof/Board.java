package wof;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Board {
	/*Use an arraylist because its much easier to add in each element
	 * just use the add method to add in each element into the list
	 */
	private static String phrase;
	private static String guesses;
	
	
	//This ArrayList will hold all the phrases
	protected static ArrayList<String> phrases;
	protected static int randomValue;
	
	
	public Board() throws FileNotFoundException{
		phrases = new ArrayList<String>();
		guesses = " ";
		ReadFile();
		randomValue = new Random().nextInt(phrases.size());
		phrase= phrases.get(randomValue);
		
	}
	public Board(String phrase){
		this.phrase = phrase;
		this.guesses = " ";
	}
	
	public void ReadFile() throws FileNotFoundException{
		
		BufferedReader fileOfPhrases = null;
		try{
			Scanner file = new Scanner(new File("phrases.txt"));
			//This will add a line read in from the file into the ArrayList
			while(file.hasNextLine()) 
				this.phrases.add(file.nextLine());
			//System.out.println(phrases.toString());
		}
		catch(FileNotFoundException e){
			System.err.print("The file was not found");
		}
	}
	
	/* Once you have read in your file, what do you want to do with it?
	 * You've added it to the ArrayList phrases 
	 */
	
	
	/* The getPhrase method will traverse through the string
	 * It will check to see if a letter exists in the string
	 * If it doesn't it will print out an underscore (blank) */
	public String getPhrase(){
		String tempstring = " ";
		
		for(char letter: phrase.toCharArray()){
			if(guesses.toLowerCase().indexOf(letter)==-1 && guesses.toUpperCase().indexOf(letter)==-1){
				tempstring += "_";
			}
			else{
				tempstring += letter; 
			}
		}
		//return tempstring.substring(1,tempstring.length())
		return tempstring;
	}
	
	/* This method is used to create a string for all the input characters
	 * These input characters are going to be checked for 
	 * in the getPhrase method */
	public void addToGuess(String input){
		guesses += input;
		//getPhrase();
		
	}
	//This method is to tell whether a letter has already been used
	public boolean isUsed(String guess){
		return guesses.contains(guess);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		Board phrase = new Board();
		phrase.addToGuess("a");
		phrase.addToGuess("n");
		phrase.addToGuess("l"); 
		phrase.addToGuess("Z");
		phrase.addToGuess("e");
		System.out.println(phrase.getPhrase());
	}

}
