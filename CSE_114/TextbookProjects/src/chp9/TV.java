package chp9;

public class TV {
	
	private int channel;
	private int volumeLevel;
	private boolean isOn;
	
	//highest channel is 60
	//lowest channel is 1
	//highest volume is 7
	//lowest volume is 1
	TV(){
		channel = 1;
		volumeLevel = 1;
		isOn = false;
	}
	public int getChannel(){
		return channel;
	}
	public int getvolumeLevel(){
		return volumeLevel;
	}
	public boolean getTVStatus(){
		return isOn;
	}
	public boolean turnOn(){
		isOn = true;
		return isOn;
	}
	public boolean turnOff(){
		isOn = false;
		return isOn;
	}
	public void setChannel(int newChannel){
		if(isOn && newChannel>=1 && newChannel<=60)
		channel = newChannel;
	}
	public void setVolume(int newVolume){
		if(isOn && newVolume>=1 && newVolume<=7)
		volumeLevel = newVolume;
	}
	public void channelUp(){
		if(isOn && channel<60)
		channel = channel +1;
	}
	public void channelDown(){
		if(isOn && channel>1)
		channel = channel -1;
	}
	public void volumeUp(){
		if(isOn && volumeLevel<7)
		volumeLevel = volumeLevel +1;
	}
	public void volumeDown(){
		if(isOn && volumeLevel>1)
		volumeLevel = volumeLevel -1;
	}
	
}
