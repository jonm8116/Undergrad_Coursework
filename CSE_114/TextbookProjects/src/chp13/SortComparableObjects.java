package chp13;
import java.math.*;

public class SortComparableObjects {

	public static void main(String[] args) {
		String[] cities = {"Savannah", "Boston", "Atlanta", "Tampa", "Miami", "Knocksville"};
		//The sort method in the Arrays class 
		//Is used to put the elements in the array in ascending order
		java.util.Arrays.sort(cities);
		for(String city: cities){
			System.out.print(city + " ");
		}
		System.out.println();
		
		BigInteger[] hugeNumbers = {new BigInteger("232332332102302"), new BigInteger("43453534534545"), 
				new BigInteger("53453636345235344534")};
		
		java.util.Arrays.sort(hugeNumbers);
		for(BigInteger number: hugeNumbers)
			System.out.print(number + " ");
		
	}

}
