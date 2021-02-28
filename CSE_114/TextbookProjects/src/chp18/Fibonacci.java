package chp18;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println("Welcome to fibonacci THE PROGRAM:");
		System.out.println("series is : " + fibonacci(9));
	}
	
	public static int fibonacci(int index){
		if(index==0){
			return 0;
		}
		else if(index==1){
			return 1;
		}
		else{
			return fibonacci(index-1) + fibonacci(index-2);
		}
	}

}
