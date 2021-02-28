package wof;

import java.awt.List;
import java.util.ArrayList;

public class Phrase {
	/* The purpose of this class is to use the class which reads in the file 
	 * which contains words from the list
	 * 
	 */
	private FileOfPhrases fileOfPhrases;
	protected ArrayList<String> words; 
	protected ArrayList<String> blanks;
	protected String wordFromPhrase;
	
	public Phrase(){
		words = new ArrayList<String>();
		blanks = new ArrayList<String>();
		fileOfPhrases = new FileOfPhrases();
		
		String phrase="";
		int numberOfPhrase = fileOfPhrases.numberOfPhrases();
		if(numberOfPhrase > 0){
			phrase = fileOfPhrases.getNextPhrase();
			//The split method is used to split the original string
			//into an array of smaller strings
			//using the space character as the delimiter value
			String[] list = phrase.split(" ");
			for(int i=0; i<=list.length-1; i++){
				words.add(list[i]);
				wordFromPhrase = list[i];
				String underscoreChar= "";
				for(int j = 0; j<wordFromPhrase.length();j++){
					underscoreChar += "_ ";		
				}
				blanks.add(underscoreChar);
				
			}
		}
	}	
	
	/*public boolean isCompleted(){
		
		
		
		
	}*/
	
	public static void main(String[] args){
		
		Phrase p = 	new Phrase();
		//System.out.println(p.blanks);
		System.out.println(p.words);
		
	}

}
