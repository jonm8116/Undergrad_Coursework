package practice_219_review;

public class Person {
	
	private String name;
	
	public void talk(){
		System.out.print("Yo what's good\n");
	}
	
	public static void main(String[] args){
		Person p = new Person();
		Student s = new Student();
		
		p = (Person)new Student();
		p = (Student)new Student();
		//s = (Person)new Person();
		s = (Student)new Person();
	}
}

class Student extends Person{
	
	@Override 
	public void talk(){
		
	}
}
