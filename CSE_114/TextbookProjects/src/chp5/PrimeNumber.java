package chp5;

public class PrimeNumber {

	public static void main(String[] args) {
		//This program is made to find the first 50 prime numbers
		System.out.println("Welcome to the prime number program");
		int count = 0;
		int numbers = 2;
		final int NUMBER_OF_PRIMES = 50;
		
		while(count < NUMBER_OF_PRIMES){
			boolean isPrime = true;
			//This first for loop is to test whether the number is prime
			for(int divisor = 2; divisor <= numbers/2; divisor++){
				if(numbers % divisor == 0){
					isPrime = false;
					
				}
			}
			if(isPrime){
				System.out.printf("%4d", numbers);
				count++;
				if(count % 10 == 0){
					System.out.println();
				}
			}
			numbers++;
			
		}
		
//		while(count<50){
//			for(int i=2; i< numbers; i++){
//				if(numbers % i != 0){
//					System.out.print(numbers + " ");
//					count++;
//				}
//			}
//			if(count % 10 == 0){
//				System.out.println();
//			}
//			numbers++;
//		}
	}

}
