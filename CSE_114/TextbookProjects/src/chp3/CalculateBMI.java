package chp3;
import java.util.Scanner;

public class CalculateBMI {

	public static void main(String[] args) {
		System.out.println("BMI CALCULATOR");
		//Scanner Object
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter weight in pounds");
		int weight = input.nextInt();
		System.out.println("Enter height in inches");
		int height = input.nextInt();
		
		//Calculating bmi
		double kilogramWeight = weight * 0.45359237; 
		double metersHeight = height * 0.0254;
		float bmiValue = (float)(kilogramWeight/(Math.pow(metersHeight, 2)));
		String bmiType = "";
		if (bmiValue < 18.5){
			bmiType = "UnderWeight";
		}
		else if(bmiValue < 25.0 && bmiValue >= 18.5){
			bmiType = "Normal";
		}
		else if(bmiValue >= 25.0 && bmiValue < 30.0){
			bmiType = "Overweight";
		}
		else if(bmiValue >= 30.0){
			bmiType = "Obese";
		}
		System.out.println("Your bmi value is " + bmiValue);
		System.out.print("And your " + bmiType);
	}

}
