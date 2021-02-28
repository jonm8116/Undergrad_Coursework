package chp12;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteToDesktop {
	
	//This program is designed to write a file to the desktop
	//Lets see how it goes
	public static void main(String[] args) throws Exception {
		String homeFolder = System.getProperty("user.home");
		File textFile = new File(homeFolder, "mytext.txt");
		BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
		
		try{
			
		}
		finally{
			
		}
	}

}
