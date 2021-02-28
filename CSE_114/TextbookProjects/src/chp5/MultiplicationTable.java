package chp5;

public class MultiplicationTable {

	public static void main(String[] args) {
		//This program is used to display a multiplication table up to 9x9 
		//It is displayed through the console
		
		System.out.println("      Multiplication Table \n");
		//The extra spacing helps to provide some tabbing in the table
		System.out.print("      ");
		//This prints the top row to indicate the columns
		for(int i=1; i<=11; i++){
			System.out.print(i + "   ");
		}
		//This prints out the line beneath the numbers
		System.out.println();
		for(int i=1; i<=50; i++){
			System.out.print("-");
		}
		System.out.println();
		//Prints the column of numbers on the left side 
		//Used to determine the rows
		for(int i=1; i<=11; i++){
			System.out.print(i + " ");
			System.out.print("|");
			//This inside loop creates the times table
			for(int j=1; j<=11; j++){
				System.out.printf("%4d", i*j);
			}
			System.out.println();
		}
		
	}

}
