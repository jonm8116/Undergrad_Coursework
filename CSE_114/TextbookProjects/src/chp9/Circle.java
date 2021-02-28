package chp9;

public class Circle {
	
	private double radius;
	private final double PI = 3.14159;
	
	Circle(){
		this.radius = radius;
	}
	Circle(double newRadius){
		radius = newRadius;
	}
	public double getRadius(){
		return radius;
	}
	public double getArea(){
		double area = PI *(Math.pow(radius, 2));
		return area;
	}
	public double getCircumference(){
		double circumference = 2*PI*radius;
		return circumference;
	}
	public void setRadius(double newRadius){
		radius = newRadius;
	}
}
