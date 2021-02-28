package chp11Problems;

public class Employee extends Person{
	private String office;
	private int salary;
	private String dateHired;
	
	Employee(String address, String name, String phoneNumber, String emailAddress,
			 String office, int salary, String dateHired){
		super(name, address, phoneNumber, emailAddress);
		this.office = office;
		this.dateHired = dateHired;
		this.salary = salary;

	}
	@Override
	public String toString(){
		return super.getName() + " is an Employee";
	}
	
}
