package chp10Problems;

public class Queue {
	//this class designs a queue 
	private int[] elements;
	private int size;
	
	//constructor
	Queue(){
		this.elements = new int[8];
	}
	public void enqueue(int v){
		for(int i=0; i<elements.length; i++){
			if(elements[i] == 0){
				elements[i] = v;
				size++;
				break;
			}
		}
	}
	public int dequeue(){
		int value=elements[0];
		int position=0;
		
			for(int i=1; i<elements.length; i++){
				this.elements[i-1] = elements[i];
			}

		if(elements[0]==0){
			return 0;
		}
		System.out.println(value);
		return value;
	}
	public boolean empty(){
		boolean value = false;
		int count=0;
		for(int i=0; i<elements.length; i++){
			if(elements[i]==0){
				count++;
			}
		}
		if(count==elements.length)
			value = true;
		return value;
	}
	public int getSize(){
		int count=0;
		for(int i=0; i<elements.length; i++){
			if(elements[i]!= 0)
				count++;
		}
		return count;	
	}
	
}
