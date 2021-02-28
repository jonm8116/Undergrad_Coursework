package pokeGame;

public class PokeType {
	private String type;
	private String[] moves = new String[4];
	//Should you define array of all poke moves in this class
	//Because technically this poke type will have only four moves
	//Should all the moves be for a parent class?
	private final String[] ALL_MOVES = {"quick attack", "iron tail", "thunder wave", "gust", 
										"leech seed", "flame charge", "confusion", "water pulse",
										"ember", "strength", "absorb", "mega absorb"};
	
	PokeType(String type){
		this.type = type;
		getMoves();
	}
	
	//Should this method be in the driver class or 
	//The pokeType Class
	public String[] getMoves(){
		//use this method to set the move set for the pokemon
		//make sure that no move is repeated in the move set
		for(int i=0; i<moves.length; i++){
			String move = ALL_MOVES[(int)(Math.random()* (ALL_MOVES.length-1))];
			boolean inMoveSet = false;
			//This nested for loop is meant for:
			//to check if a pokemon's move is already in the array
			//So that a pokemon doesn't have two of the same moves
				for(int j=0; j<moves.length; j++){
					if(move == moves[j]){
						inMoveSet = true;
					}
				
				}
				//this is supposed to get rid of null
				//But not working for some reason
				if(inMoveSet == false && move != null)
					moves[i] = move;
					
				
		}
		return moves;
	}
	
	public void printMoves(){
		for(int i=0; i<moves.length; i++){
			System.out.println(i+1 + " " + moves[i]);
		}
	}
}
