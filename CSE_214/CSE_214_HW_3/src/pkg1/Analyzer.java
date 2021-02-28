package pkg1;
import java.util.Scanner;
/**This is the driver class for the Elevator Simulator program
 * @version 1, July 29, 2016
 */
public class Analyzer {
	/**This is the main method that is used to prompt the
	 * user for the parameters of the simulate method
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("Welcome to Elevator Simulator");
		System.out.println("You must input 4 parameters before beginning the"
				+ " simulation");
		Scanner input = new Scanner(System.in);
		System.out.println("What is the probability that a person will request"
				+ " an elevator?\nPlease enter a decimal value");
		double userProbability = input.nextDouble();
		System.out.println("What is the number of floors in the building?\n"
				+ "Please enter an integer value");
		int userNumFloors = input.nextInt();
		System.out.println("What is the number of elevators in the building?\n"
				+ "Please enter an integer value");
		int userNumElevs = input.nextInt();
		System.out.println("What is the time length of the simulation?\n"
				+ "Please enter an integer value");
		int userTimeSim = input.nextInt();
		//I don't like static methods....
		Simulator.simulate(userProbability, userNumFloors, userNumElevs, userTimeSim);
		
	}
}
