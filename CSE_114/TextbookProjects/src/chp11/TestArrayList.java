package chp11;
import java.util.ArrayList;

public class TestArrayList {

	public static void main(String[] args) {
		//Create list to store cities
		ArrayList<String> cityList = new ArrayList<>();
		
		//Add some cities in the list
		cityList.add("London");
		cityList.add("Denver");
		cityList.add("Paris");
		cityList.add("Miami");
		
		System.out.println("list size? " + cityList.size());
		System.out.println("Is Miami in the list? " + cityList.contains("Miami"));
		System.out.println("Is the list empty? " + cityList.isEmpty());
		
		cityList.add(2, "Xian");
		
		ArrayList<Circle> list = new ArrayList<>();
		 
	}

}
