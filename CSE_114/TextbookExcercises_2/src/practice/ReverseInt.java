package practice;

public class ReverseInt {

	public static void main(String[] args) {
		int input = 2345;
		System.out.println("the input is " + input +"\n" + "reversed is " + reverseInt(input));
	}
	
	public static int reverseInt(int input)
	{
	    int reversedNum = 0;

	    int input_long = input;

	    while (input_long != 0)
	    {
	        reversedNum = reversedNum * 10 + input_long % 10;
	        input_long = input_long / 10;
	    }
	    return reversedNum;
	}

}
