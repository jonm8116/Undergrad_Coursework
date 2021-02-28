package chp9;

public class Course {
	//Let's approx and assume max ppl that can enroll are 
	private String courseName;
	private String[] students = new String[100];
	private int numberOfStudents;
	
	Course(String courseName){
		this.courseName = courseName;
	}
	public String getCourseName(){
		return courseName;
	}
	public void addStudent(String student){
		for(int i=0; i<students.length; i++){
			if(students[i]== null){
				students[i] = student;
				//If you did not put in this break statement in for this
				//The for loop would've set the student to the same person repeatedly 
				//Remember to put in break statements if you only want this done once
				break;
			}
		}
	}
	public void dropStudent(String student){
		for(int i=0; i<students.length; i++){
			if(students[i] == student){
				students[i] = null;
			}
		}
		
	}
	public String[] getStudents(){
		return students;
	}
	public void setNumberOfStudents(int NumberOfstudents){
		this.numberOfStudents = NumberOfstudents;
	}
	public int getNumberOfStudents(){
		return numberOfStudents;
	}
	public int countNumOfStudents(){
		int count = 0;
		for(int i=0; i<students.length; i++){
			if(students[i] != null){
				count++;
			}
		}
		return count;
	}
}
