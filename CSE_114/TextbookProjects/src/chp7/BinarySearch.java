package chp7;

public class BinarySearch {
	
	//This class implements the binary search algorithm 
	//You should really study this algorithm 
	//Could come up in 214
	public static void main(String[] args) {
		System.out.println("Welcome to binary search algorithm");
		System.out.println("This program implements the binary search algorithm");
		int[] arr = new int[1000];
		for(int i=0; i<arr.length; i++){
			arr[i] = (int)(Math.random() * arr.length);
		}
		System.out.print((binarySearch(arr, 21)));
	}
	
	public static int binarySearch (int[] list, int key){
		int low = 0;
		int high = list.length -1;
		
		while(high>= low){
			int mid = (low + high) /2;
			if(key < list[mid]){
				high = mid -1;
			}
			else if(key == list[mid]){
				return mid;
			}
			else{
				low = mid + 1;
			}
		
		}
		return -low - 1; //Now high < low, key not found 
	}
}
