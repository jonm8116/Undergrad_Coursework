package chp10Problems;
import java.util.Arrays;

public class TestCourse {

	public static void main(String[] args) {
		Course CSE101 = new Course("CSE101");
		CSE101.addStudent("Jack");
		CSE101.addStudent("Ben");
		CSE101.addStudent("Ashley");
		CSE101.addStudent("Alan");
		CSE101.addStudent("Nihad");
		CSE101.addStudent("Bob");
		CSE101.addStudent("Arslan");
		CSE101.addStudent("Yousef");
		CSE101.addStudent("Bob");
		CSE101.addStudent("Richu");
		CSE101.addStudent("Matt");
		try{
			CSE101.addStudent("Mathews");
			CSE101.addStudent("John");
			//Delete students 
			CSE101.deleteStudent("Bob");
			CSE101.deleteStudent("Jack");
			
		}
		catch(ArrayIndexOutOfBoundsException ex){
			CSE101.printlength();
		}
		CSE101.printStudents();
		System.out.println(CSE101.ifArrayFull());
		CSE101.printlength();
		
	}

}
