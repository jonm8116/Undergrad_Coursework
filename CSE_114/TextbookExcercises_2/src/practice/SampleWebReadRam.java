package practice;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import java.io.IOException;
public class SampleWebReadRam {

public static void main(String[] args) throws Exception {
		
		URL apple= new URL("http://www.tabletpccomparison.net/");

		URLConnection ipod = apple.openConnection();
		Scanner s = new Scanner (apple.openStream());
		
		System.out.println("Please enter an ipod to search");
		Scanner input = new Scanner(System.in); 
		String user = input.nextLine(); 
		
		
		
		while(s.hasNext()){
			String line = s.nextLine(); 
			if(line.contains(user)){
				System.out.print(line);
				break;
			}
		}
		
	}
	
}