package wof;
import java.util.Random;

public class Wheel {
	private final int BANKRUPT = 0;
	private final int ONE_MILLION = 1000000;
	private int[] values= {50, -50, 100, -100, 150, -150, 200, -200,
							250, -250, 300, -300, 350, -350, 400, -450,
							500, -500, 550, -550, 600, -600, 650, -650, 700, 
							-700, 750, -750, 800, -800, 850, -850, 900, -900,
							950, -950, 1000, -1000, BANKRUPT, ONE_MILLION};
	
	public Wheel(){
		
	}
	
	public int spin(){
		Random random = new Random();
		return values[random.nextInt(values.length)];
	}
}
