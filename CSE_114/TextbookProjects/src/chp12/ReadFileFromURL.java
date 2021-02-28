package chp12;
import java.util.Scanner;

public class ReadFileFromURL {
	
	//Time to explain how this program works
	
	//So first need to create a String which will be read in from the keyboard
	//This string will become the url string for the website
	//Then we must set up a try/catch block for the exceptions
	/* - MalformedURLException
	 * - IOException
	 * In the try block:
	 * First we create a URL object (the constructor is passed the url string)
	 * Initialize count variable and Scanner object 
	 * The constructor for the scanner object is passed url.openStream()
	 * This method returns an input stream and allows a way to access the web
	 * 	
	 * Then a while loop is created to read from the web file while(input.hasNext())
	 * A string variable is initialized in the while loop and is used to read each line
	 * The length of the characters of each line is then added to count
	 * After this loop is completed
	 * Eventually the program will print a statement which shows how many
	 * characters are in the file
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Enter URL: ");
		String URLString = new Scanner(System.in).next();
		
		try{
			//Creates a URL object 
			java.net.URL url = new java.net.URL(URLString);
			int count = 0;
			//Scanner object
			Scanner input = new Scanner(url.openStream());
			while(input.hasNext()){
				String line = input.nextLine();
				count += line.length();
			}
			System.out.println("The file size is " + count + " characters");
		}
		catch(java.net.MalformedURLException ex){
			System.out.println("Invalid URL");
		}
		catch(java.io.IOException ex){
			System.out.println("I/O Errors: no such file");
		}
	}

}
