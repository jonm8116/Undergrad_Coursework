package chp8;

public class GradeExam {
	
	//For this multiple choice test there are:
	/* - 8 students
	 * - 10 questions
	 * - 1 key
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Exam Grader");
		char[][] students = {
				{'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, //student0
				{'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'}, //studen1
				{'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'}, //student2
				{'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'}, //student3
				{'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, //student4
				{'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, //student5
				{'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, //student6
				{'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}
		};
		char[] key = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};
		grader(students, key);
	}
	
	public static void grader(char[][] students, char[] key){
		int count=0;
		for(int i=0; i<students.length; i++){
			count = 0;
			for(int j=0; j<students[i].length; j++){
				if(students[i][j] == key[j]){
					count++;
				}
				
			}
			System.out.println("Student " + i + " got " + count + " questions right");
		}
	}
	
}
