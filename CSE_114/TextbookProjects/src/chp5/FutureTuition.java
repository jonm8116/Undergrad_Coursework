package chp5;

public class FutureTuition {

	public static void main(String[] args) {
		//This sample program is used to calculate future tuition 
		//The starting tuition of the university $10000 
		double initialTuition = 10000;
		int years = 0;
		
		while(initialTuition < 20000){
			initialTuition = initialTuition *1.07;
			years++;
		}
		System.out.println("The time it takes for the tuition to double is " + years + " years");
	}

}
