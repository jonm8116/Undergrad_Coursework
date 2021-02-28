package chp13;

public abstract class GeometricObjectAbstract {
	private String color;
	private boolean filled;
	
	protected GeometricObjectAbstract(String color, boolean filled){
		this.color = color;
		this.filled = filled;
	}
	public String getColor(){
		return color;
	}
	public void setColor(String color){
		this.color = color;
	}
	public void setFilled(boolean filled){
		this.filled = filled;
	}
	public boolean isFilled(){
		return filled;
	}
	@Override
	public String toString(){
		return "color is " + color + " and filled is: " + filled;
	}
	//Abstract methods
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
}
