package chp12;

public class TestCircleWithException {

	public static void main(String[] args) {
		try{
		CircleWithException circle1 = new CircleWithException(3);
		CircleWithException circle2 = new CircleWithException(-6);
		CircleWithException circle3 = new CircleWithException(0);
		}
		catch(IllegalArgumentException ex){
			System.out.println(ex);
		}
		System.out.println("Number of objects created is " + CircleWithException.getNumberOfObjects());
		
	}

}
