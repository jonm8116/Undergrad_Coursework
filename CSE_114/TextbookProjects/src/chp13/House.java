package chp13;

public class House implements Cloneable, Comparable<House>{
	private int id;
	private double area;
	
	public House(int id, double area){
		this.id = id;
		this.area = area;
	}
	public int getId(){
		return id;
	}
	public double getArea(){
		return area;
	}
	@Override //Override protected clone method defined in Object class
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	@Override //Implement the compareTo method defined in Comparable
	public int compareTo(House o){
		if(area > o.area)
			return 1;
		else if(area<o.area)
			return -1;
		else 
			return 0;
	}
	
}
