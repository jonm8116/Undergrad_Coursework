package chp13;

public class Rectangle extends GeometricObjectAbstract{
	private double width;
	private double height;
	private static String color;
	private static boolean filled;
	
	Rectangle(double width, double height){
		//For the super constructor why can't you use the data fields 
		//from the GeometricObjectAbstract class 
		super(color, filled);
		this.height = height;
		this.width = width;
	}
	
	Rectangle(double width, double height, String color, boolean filled){
		super(color, filled);
		this.width = width;
		this.height = height;
		
	}
	public double getWidth(){
		return width;
	}
	public void setWidth(double width){
		this.width = width;
	}
	public double getHeight(){
		return height;
	}
	public void setHeight(double height){
		this.height = height;
	}
	public double getArea(){
		return width * height;
	}
	public double getPerimeter(){
		return 2 * (width + height);
	}
}
