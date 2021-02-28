package chp13;
import java.util.ArrayList;
import java.math.*;

public class LargestNumber {

	public static void main(String[] args) {
		ArrayList<Number> list = new ArrayList<>();
		list.add(45);
		list.add(2000);
		list.add(new BigInteger("2342344353453453"));
		list.add(new BigDecimal("2344444333.43453453454"));
		System.out.println("the number is " + getLargestNumber(list));
	}
	
	public static Number getLargestNumber(ArrayList<Number> list){
		if(list ==null || list.size()==0)
			return null;
		Number num = list.get(0);
		for(int i=0; i<list.size(); i++){
			//The .doubleValue() method will return the double value of a number
			//I don't think it has to be a number object
			if(num.doubleValue() < list.get(i).doubleValue()){
				num = list.get(i);
			}
		}
		return num;
	}

}
