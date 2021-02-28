package chp13;

public class TestGeometricObject {

	public static void main(String[] args) {
		//examples of polymorphism 
		GeometricObjectAbstract geoObject1 = new Circle(5, "blue", true);
		GeometricObjectAbstract geoObject2 = new Rectangle(5, 6, "orange", true);
		
		System.out.println("Circle and Rect have same area? " + equalArea(geoObject1, geoObject2));
		displayGeometricObject(geoObject1);
		displayGeometricObject(geoObject2);
		
		
	}
	
	public static boolean equalArea(GeometricObjectAbstract object1, GeometricObjectAbstract object2){
		return object1.getArea() == object2.getArea();
	}
	
	public static void displayGeometricObject(GeometricObjectAbstract object){
		System.out.println("\n The area is " + object.getArea());
		System.out.println("\n The perimeter is " + object.getPerimeter());

	}

}
