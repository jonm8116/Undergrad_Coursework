package chp13;

public class Circle extends GeometricObjectAbstract{
private double radius;
	
	Circle(double radius, String color, boolean filled){
		super(color, filled);
		this.radius = radius;
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
	public double getPerimeter(){
		return 2 * radius * Math.PI;
	}
	public void printCircle(){
		System.out.println("The circle is created " + 
							" and the radius is " + radius);
	}
}
