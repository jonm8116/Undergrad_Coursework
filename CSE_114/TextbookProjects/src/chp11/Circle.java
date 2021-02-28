package chp11;

public class Circle extends GeometricObject {
	private double radius;
	
	Circle(double radius){
		this.radius = radius;
	}
	Circle(double radius, String color, boolean filled){
		this.radius = radius;
		setColor(color);
		setFilled(filled);
	}
	public double getRadius(){
		return radius;
	}
	public void setRadius(double radius){
		this.radius = radius;
	}
	public double getArea(){
		return Math.pow(radius, 2) * Math.PI;
	}
	public double getDiameter(){
		return 2 * radius;
	}
	public double circumference(){
		return 2 * radius * Math.PI;
	}
	public void printCircle(){
		System.out.println("The circle is created " + getDateCreated() + 
							" and the radius is " + radius);
	}
}
