package wof;
import java.util.Random;

public class Wheel {
	private final int BANKRUPT = -1;
	//These are the predefined values within the wheel 
	private final int[] wheel = {50, 100, 150, 200, 250, 300, 350, 450, 500,
								550, 600, 650, 700, 750, 800, 850, 900, 950,
								1000, BANKRUPT};
	
	//The virtual machine will create a default constructor 
	//for this class
	
	
	//The spin method returns a value from the wheel
	//It creates a random object within the indices of the array
	//The random object chooses an integer from the range of 
	//0 to wheel length
	public int spin(){
		return wheel[ new Random().nextInt(wheel.length)];
	}
	
}
