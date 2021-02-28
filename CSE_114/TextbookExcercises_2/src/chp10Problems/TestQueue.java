package chp10Problems;

public class TestQueue {

	public static void main(String[] args) {
		Queue queue1 = new Queue();
		
		for(int i=1; i<7; i++)
			queue1.enqueue(i);
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
	}

}
