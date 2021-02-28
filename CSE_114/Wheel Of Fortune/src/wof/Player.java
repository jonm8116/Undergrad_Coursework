package wof;

public class Player {
	private String name;
	private int score;
	private boolean turn;
	
	//This is the default constructor
	public Player(){
		name = "Bob";
		score = 0;
		turn = false;
	}
	
	//This is the specified constructor 
	public Player(String name, int score, boolean turn){
		this.name = name;
		this.score = score;
		this.turn = turn;
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
	//turn
	public void setTurn(boolean turn){
		this.turn = turn;
	}
	public boolean getTurn(){
		return turn;
	}
	
	//This is the toString method for the player's stats
	public String PlayerStats(){
		return ("Player stats \n"  
				+ "Name "+ name + "\n"
				+ "Score " + score + "\n"
				+ "Turn " + turn);
	}
}
