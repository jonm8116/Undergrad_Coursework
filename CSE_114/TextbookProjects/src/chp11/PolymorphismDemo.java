package chp11;

public class PolymorphismDemo {

	public static void main(String[] args) {
		//Display circle and rectangle properties 
		displayObject(new Circle(1, "red", false));
		displayObject(new Rectangle(1, 1, "black", true));
	}
	
	public static void displayObject(GeometricObject object){
		System.out.println("This object is " + object.getColor());
	}
}
