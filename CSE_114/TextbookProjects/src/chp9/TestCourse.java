package chp9;

public class TestCourse {

	public static void main(String[] args) {
		//Construct objects
		Course course1 = new Course("Data Structures");
		Course course2 = new Course("Algorithms");
		//add students to Data Structures
		course1.addStudent("Peter Jones");
		course1.addStudent("Kim Smith");
		course1.addStudent("Anne Kennedy");
		//add students to Algorithms
		course2.addStudent("Peter Jones");
		course2.addStudent("Steve Smith");
		
		System.out.println("Number of students in Data structures " + course1.countNumOfStudents());
		String[] students = course1.getStudents();
		System.out.println("They are: ");
		for(int i=0; i<course1.countNumOfStudents(); i++){
			System.out.print(students[i] + ", ");
		}
		System.out.println();
		System.out.println("Number of students in Algorithms " + course2.countNumOfStudents());
		System.out.println("They are: ");
		String[] students2 = course2.getStudents();
		for(int i=0; i<course2.countNumOfStudents(); i++){
			System.out.print(students2[i] + ", ");
		}
		
	}

}
