package chp12;

public class InvalidRadiusException extends Exception{
	
	private double radius;
	
	InvalidRadiusException(double radius){
		super("Invalid radius " + radius);
		this.radius = radius;
	}
	
	public double getRadius(){
		return radius;
	}
}
