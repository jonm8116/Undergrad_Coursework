package wof;

public class Player {
	private String name;
	private int score;
	//Take out turn and put it in driver class
		
	//This is the default constructor
	public Player(){
		name = "";
		score = 0;
		
	}
	
	//This is the specified constructor 
	public Player(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	//Getter and Setter methods
	//Name
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	//score
	public void setScore(int score){
		this.score = score;
	}
	//This uses the ternary operator
	//The operator assigns the score the value added
	//if the value is greater than zero
	//if not it adds nothing
	public void addScore(int value){
		this.score = (value>0) ? this.score+=value: 0; 
	}
	public int getScore(){
		return score;
	}
	
	//This is the toString method for the player's stats
	public String PlayerStats(){
		return ("Player stats \n"  
				+ "Name "+ name + "\n"
				+ "Score " + score 
				);
	}
}
