package chp12;
import java.io.IOException;

public class WriteData {
	
	//The purpose of this program is to write data to file
	//It does this using the printwriter class
	public static void main(String[] args) throws IOException {
		java.io.File file = new java.io.File("scores.txt");
		if(file.exists()){
			System.out.println("File already exists");
			System.exit(1);
		}
		//Creates a file and printwriter object
		java.io.PrintWriter output = new java.io.PrintWriter(file);
		//Write formatted output to the file
		output.print("John T Smith");
		output.println(90);
		output.print("Eric K Jones");
		output.println(85);
		//close file
		output.close();
		System.out.println("Absolute Path is " + file.getAbsolutePath());
	}

}
