package chp7;

public class OccurrencesOfLetters {
	
	//This program is used to count the occurrences of each letter in an array of char
	//The program will generate 100 lowercase random letters
	//It will then assign these letters to an array of char
	public static void main(String[] args) {
		System.out.println("Welcome to occerunces of lowercase leters");
		//System.out.println((int)'a');
		displayCounts(countLetters(arrOfChar()));
		System.out.println("\n");
		printArr(arrOfChar());
		
	}
	//This method is used to help generate random characters
	//Remember to study this method because you did not make this yourself 
	public static char getRandomCharacter(char ch1, char ch2){
		return (char)(ch1 + Math.random() * (ch2 - ch1 + 1));
	}
	//This method is also from the random character class in chp6
	public static char getRandomLowerCaseLetter(){
		return getRandomCharacter('a', 'z');
	}
	//This method creates the array of 100 random lowercase letters
	public static char[] arrOfChar(){
		char[] arr = new char[100];
		for(int i=0; i<arr.length; i++){
			arr[i] = getRandomLowerCaseLetter();
		}
		return arr;
	}
	//This method counts the occurrence of each letter 
	//It does this by creating an array called count 
//	public static void countArr(char[] arr){
//		int[] count = new int[26];
//		int[] checkCount = new int[26];
//		for(int i=0; i<count.length; i++){
//			count[i] = i;
//			checkCount[i] = 0;
//		}
//		//Make a nested for loop to check 
//		//If each element in arr is equivalent to an element in count
//		//Use a int cast to char to do the arithmetic
//		for(int i=0; i<arr.length; i++){
//			//This for loop is suppossed to traverse through letters in alphabet
//			for(int j=0; j<count.length; j++){
//				if((arr[j] - 97) == ((int)(count[j]))){
//					checkCount[j]++;
//					break;
//				}
//			}
//		}
//		for(int i=0; i<checkCount.length; i++){
//			System.out.println("The number of " + (char)(count[i] + 97) + " is " + checkCount[i]);
//		}
//	}
	public static int[] countLetters(char[] arr){
		int[] counts = new int[26];
		//for each lowercase letter in the array, count it
		for(int i=0; i<arr.length; i++){
			counts[arr[i] - 'a']++;
		}
		return counts;
	}
	
	public static void displayCounts(int[] counts){
		for(int i=0; i<counts.length; i++){
			if((i+1) % 10 == 0){
				System.out.println(counts[i] + " " + (char)(i + 'a'));
			}
			else{
				System.out.print(counts[i] + " " + (char)(i + 'a') + " ");
			}
		}
	}
	
	public static void printArr(char[] arr){
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
}
