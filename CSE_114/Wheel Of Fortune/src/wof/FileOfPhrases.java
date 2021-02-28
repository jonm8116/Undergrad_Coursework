package wof;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOfPhrases {
	
	//This constant contains the path to the file of phrases
	private final static String TEXT_FILE_PATH = "phrases.txt"; //path to the file containing the phrases 
	
	//This arraylist will contain all the phrases
	private ArrayList<String> phrases; // list of phrases 
	
	//This constructor assigns the phrases arraylist to a new arraylist object
	public FileOfPhrases(){
		this.phrases = new ArrayList<String>();
		readAllPhrases();
	}
	
	//This method will return the next phrase from the file
	public String getNextPhrase(){
		return this.phrases.remove(0);
	}
	
	//This method will return the size of the arraylist
	public int numberOfPhrases(){
		return this.phrases.size();
	}
	
	private void readAllPhrases(){
		
		BufferedReader filesOfPhrases = null;
		 try {
			Scanner fileScanner = new Scanner(new File(FileOfPhrases.TEXT_FILE_PATH)); 
			while(fileScanner.hasNextLine()) 
				this.phrases.add(fileScanner.nextLine());
		 		//System.out.println(this.phrases);
		 } catch (FileNotFoundException e) {
			System.err.println("File Not Found: " + e.toString() );
		}
	}
	
	public static void main(String... args ){
		new FileOfPhrases();
		
	}
	
}
