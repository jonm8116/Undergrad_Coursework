package chp10Problems;

public class Course {
	private String courseName;
	private String[] students;
	private int numberOfStudents;
	
	Course(String name){
		students = new String[10];
		this.courseName = name;
	}
	//Getters and Setters
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public String getCourseName(){
		return courseName;
	}
	//Add and Delete functions
	public void addStudent(String studentName){
		if(ifArrayFull()){
			doubleArr(students);
			System.out.println("double arr is called");
		} 
		for(int i=0; i<students.length; i++){
			if(students[i] == null){
				students[i] = studentName;
				break;
			}
		}
		
	}
	public void deleteStudent(String studentName){
		for(int i=0; i<students.length; i++){
			if(students[i]==studentName){
				students[i] = null;
			}
		}
	}
	public void printStudents(){
		System.out.print("The students are: ");
		for(int i=0; i<students.length; i++){
			System.out.print(students[i] + " ");
		}
	}
	public boolean ifArrayFull(){
		int count = 0;
		boolean isArrFull = false;
		for(int i=0; i<students.length; i++){
			if(students[i] != null){
				count++;
			}
		}
		if(count == students.length){
			isArrFull = true;
		}
		return isArrFull;
	}
	public void doubleArr(String[] arr){
		String[] students = new String[arr.length*2];
		for(int i=0; i<arr.length; i++){
			students[i] = arr[i];
		}
		//this actually doubles the size of the array when 
		//I reference the data field
		//but if reference the parameter argument (which I believe is the same)
		//It will not change the reference to the array
		this.students = students;
	}
	public void printlength(){
		System.out.println(students.length);
	}
}
