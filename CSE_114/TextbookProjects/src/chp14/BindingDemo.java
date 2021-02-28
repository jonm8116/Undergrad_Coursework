package chp14;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class BindingDemo {

	public static void main(String[] args) {
		//This is unidirectional binding
		//Change in the property of one object is reflected
		//in the change of the property of another object
		DoubleProperty d1 = new SimpleDoubleProperty(1);
		DoubleProperty d2 = new SimpleDoubleProperty(2);
		d1.bind(d2);
		//If you want to do bidirectional binding 
		//use the bindBidirectional method
		System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());
		d2.set(70.2);
		System.out.println("d1 is " + d1.getValue() + " and d2 is " + d2.getValue());
	}

}
