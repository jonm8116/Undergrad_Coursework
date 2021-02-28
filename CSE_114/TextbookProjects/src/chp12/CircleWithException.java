package chp12;

public class CircleWithException {
	private double radius;
	private static int numberOfObjects = 0;
	
	CircleWithException(){
		this(1.0);
	}
	CircleWithException(double newRadius){
		setRadius(newRadius);
		numberOfObjects++;
	}
	
	public void setRadius(double newRadius) throws IllegalArgumentException {
		if(newRadius >= 0){
			radius = newRadius;
		}
		else
			throw new IllegalArgumentException("Radius cannot be negative");
	}
	
	public static int getNumberOfObjects(){
		return numberOfObjects;
	}
	public void setNumberOfObjects(int numberOfObjects){
		this.numberOfObjects = numberOfObjects;
	}
	public double findArea(){
		double area = Math.pow(radius, 2) * Math.PI;
		return area;
	}
}
