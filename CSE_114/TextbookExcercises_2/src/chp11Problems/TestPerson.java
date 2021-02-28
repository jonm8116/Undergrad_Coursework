package chp11Problems;

public class TestPerson {

	public static void main(String[] args) {
		Employee employee = new Employee("3rd Street", "Jackson", "516 245 3432", "you@.com",
				 "pearl office", 3000, "October 30 21");
		Student student = new Student("freshman", "4th street", "Job", "233 232 3434", "me@.com");
		Person person = new Person("Joshua", "6th street", "516 334 4443", "third@.com");
		System.out.println(employee.toString());
		System.out.println(student.toString());
		System.out.println(person.toString());
	}

}
