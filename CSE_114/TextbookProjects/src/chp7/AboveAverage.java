package chp7;
import java.util.Scanner;

public class AboveAverage {
	
	//The purpose of this program is for a user to enter a certain number of values
	//The user determines the number of values
	//Then the user enters the value into the console
	//After all the inputs are entered into the console
	//The program will output the average and the items that are above the average
	public static void main(String[] args) {
		System.out.println("Welcome to the Above Average Calculator");
		System.out.println("Please enter the amount of items you would like to enter");
		Scanner input = new Scanner(System.in);
		int amountOfItems = input.nextInt();
		System.out.println("Please enter your numberical items");
		int[] arrElements = new int[amountOfItems];
		for(int i=0; i<amountOfItems; i++){
			arrElements[i] = input.nextInt();
		}
		findAverage(arrElements);
	}
	//This method is used to find the average of all the elements
	//It will then find which elements are above the average
	public static void findAverage(int[] arrElements){
		double sum = 0;
		//This for loop gets the sum
		for(int i=0; i<arrElements.length; i++){
			sum+= arrElements[i];
		}
		double average = sum / (arrElements.length);
		int amountAboveAvg = 0;
		//This for loop is to check how many elements are above the average
		//This will be the length of the next array
		for(int i=0; i<arrElements.length; i++){
			if(arrElements[i] > average){
				amountAboveAvg++;
			}
		}
		int[] aboveAvg = new int[amountAboveAvg];
		//This loop will add the elements above the avg to the new array
		//Make this a nested for loop
		for(int i=0; i<aboveAvg.length; i++){
			for(int j=0; j<arrElements.length; j++){
				if(arrElements[j] > average){ 
					if(aboveAvg[i] != arrElements[j]){
						aboveAvg[i] = arrElements[j];
					}
				}
			}
		}
		System.out.println("The average is " + average);
		System.out.print("The elements above the average are ");
		for(int i=0; i<aboveAvg.length; i++){
			System.out.print(aboveAvg[i] + " ");
		}
	}
	
	

}
