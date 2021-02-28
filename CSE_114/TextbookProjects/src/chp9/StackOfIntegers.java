package chp9;

public class StackOfIntegers {
	private int[] elements;
	private int size;
	
	StackOfIntegers(){
		elements = new int[16];
	}
	StackOfIntegers(int capacity){
		elements = new int[capacity];
	}
	public boolean empty(){
//		int count = 0;
//		for(int i=0; i<elements.length; i++){
//			if(elements[i] != 0){
//				count++;
//			}
//		}
//		if(count==0){
//			return true;
//		}
//		else{
//			return false;
//		}
		return size == 0;
	}
	public int peek(){
//		int peekValue =0;
//		for(int i=0; i<elements.length; i++){
//			if(elements[i] == 0 && elements[i+1] == 0 && elements[i+2] == 0){
//				peekValue = elements[i-1];
//			}
//		}
//		return peekValue;
		return elements[size -1];
	}
	
	public int pop(){
//		int peekValue =0;
//		for(int i=0; i<elements.length; i++){
//			if(elements[i]==0 && elements[i+1] == 0 && elements[i+2]==0){
//				peekValue = elements[i-1];
//				elements[i-1] = 0;
//			}
//			else if(elements[i]==0 && elements[i+1]==0){
//				peekValue = elements[i-1];
//				elements[i-1] = 0;
//			}
//		}
//		return peekValue;
		return elements[--size];
	}
	
	public int getSize(){
		int count=0;
		for(int i=0; i<elements.length; i++){
			if(elements[i]!= 0){
				count++;
			}
		}
		return count;
	}
	public void push (int value){
		if(size>=elements.length){
			int[] temp = new int[elements.length * 2];
			System.arraycopy(elements, 0, temp, 0, elements.length);
			elements = temp;
		}
		elements[size++] = value;
	}
}
