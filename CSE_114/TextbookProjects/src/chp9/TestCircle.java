package chp9;

public class TestCircle {

	public static void main(String[] args) {
		Circle circle1 = new Circle();
		circle1.setRadius(8.0);
		System.out.println("The radius of the circle1 is " + circle1.getRadius());
		System.out.println("The area of the circle1 is " + circle1.getArea());
		
		//Next circle
		Circle circle2 = new Circle(24);
		System.out.println("\n The radius of circle2 " + circle2.getRadius());
		System.out.println("The area of circle2 " + circle2.getArea());
	}

}
