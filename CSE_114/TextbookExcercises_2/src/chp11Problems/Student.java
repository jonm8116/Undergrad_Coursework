package chp11Problems;

public class Student extends Person {
	private final String status;
	
	Student(String status, String address, String name, String phoneNumber, String emailAddress){
		//This super constructor which is defined in person
		//must be called when the class is extended 
		//Then you just set the last status
		super(name, address, phoneNumber, emailAddress);
		this.status = status;
	}
	@Override
	public String toString(){
		return super.getName() + " is a Student";
	}
}
