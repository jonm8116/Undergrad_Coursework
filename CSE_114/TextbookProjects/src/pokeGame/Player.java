package pokeGame;

public class Player {
	private int HP;
	private PokeType type;
	private String name;
	private boolean turn;
	//Constructor to initialize a player
	Player(String name){
		this.HP = 100;
		this.name = name;
		this.turn = false;
		//this.type = type;
	}
	//Getter and setters
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		this.HP = hP;
	}
	public PokeType getType() {
		return type;
	}
	public void setType(PokeType type) {
		this.type = type;
	}
	public int subtractHP(int damage){
		setHP(HP-damage);
		return getHP();
	}
	public void getStatus(){
		System.out.println("name: " + name + "\n" + "HP: " + HP + "turn: " + turn);
	}
	
}
