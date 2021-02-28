package chp9;

public class TestTV {
	
	//This program tests the TV class 
	public static void main(String[] args) {
		TV tv1 = new TV();
		TV tv2 = new TV();
		
		tv1.turnOn();
		tv1.channelUp();
		tv1.channelUp();
		tv1.volumeUp(); 
		System.out.println("The TV is " + tv1.getTVStatus() + " its on channel " + tv1.getChannel() + 
						" and its volume is " + tv1.getvolumeLevel());
	}

}
