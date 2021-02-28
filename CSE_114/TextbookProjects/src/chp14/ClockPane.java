package chp14;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockPane extends Pane{
	private int hour;
	private int minute;
	private int second;
	private double width=250;
	private double height=250;
	
	ClockPane(){
		hour = 0;
		minute = 0; 
		second = 0;
	}
	ClockPane(int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
//	public double getWidth() {
//		return width;
//	}
	public void setWidth(double width) {
		this.width = width;
	}
//	public double getHeight() {
//		return height;
//	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setCurrentTime(){
		Calendar calendar = new GregorianCalendar();
		
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		
		paintClock();
	}
	//Have one method to paint the UI
	//Still follow oop format
	protected void paintClock(){
		double clockRadius = Math.min(width, height) * 0.8 * 0.5;
		double centerX = width / 2;
		double centerY = height / 2;
		//Draw circle 
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		
		Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
		
	}
}
