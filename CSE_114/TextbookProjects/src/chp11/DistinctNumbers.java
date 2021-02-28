package chp11;
import java.util.Scanner;
import java.util.ArrayList;

public class DistinctNumbers {
	
	//This program is used to prompt the user to enter a sequence of numbers
	//It displays the sequence
	//ends with input of 0
	public static void main(String[] args) {
		System.out.println("Welcome to distinct numbers program");
		
		ArrayList<Integer> list = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		int value;
		do{
			value = input.nextInt();
			if(!list.contains(value) && value != 0){
				list.add(value);
			}
		} while(value != 0);
		
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	

}
