package chp12;
import java.util.Scanner;

public class ReadData {

	public static void main(String[] args) throws Exception {
		//Create file object
		java.io.File file = new java.io.File("scores.txt");
		
		//Create a scanner for file
		Scanner input = new Scanner(file);
		while(input.hasNext()){
			String firstName = input.next();
			String mi = input.next();
			String lastName = input.next();
			int score = input.nextInt();
			System.out.println(firstName + " " + mi + " " + lastName + " " + score);
		}
		//close file
		input.close();
	}

}
