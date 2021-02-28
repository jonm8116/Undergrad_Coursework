package chp12;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.URL;

public class WebCrawler {
	
	/* The algorithm described in the textbook is:
	 * Add the startingURLs to a list: listOfPendingURLs
	 * while listOfPendingURLs is not empty and size of listOfTraversedURLs <=100 {
	 * Remove a URL from listOfPendingURLs;
	 * 		if(this URL is not in listOfTraversedURLs){
	 * 			Add it to listOfTraveresedURLs;
	 * 			Display this URL (or separate method to print)
	 * 			Read the page from this URL, for each URL contained{
	 * 				Add to listOfPendingURLs
	 * 			}
	 * 		}
	 * }
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a URL");
		String url = input.nextLine();
	}
	//This method below I want it to take in a URL
	//Check if the url is contained in traversedURLs
	//IF not add it otherwise discard it
	//It will return 
	public static String readWebsite(String URLString){
		String websiteString;
		try{
			java.net.URL url = new java.net.URL(URLString);
			Scanner input = new Scanner(url.openStream());
			while(input.hasNext()){
				String line = input.nextLine();
				if(input.nextLine()=="http: "){
					
				}
			}
		}
		catch(java.net.MalformedURLException ex){
			System.out.println(ex.getStackTrace());
		}
		catch(java.io.IOException ex){
			System.out.println(ex.getStackTrace());
		}
		return "Hello";
	}

}
