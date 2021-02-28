package chp7;
import java.util.Scanner;

public class PokemonGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] pokeTypes = {"Poison", "Water", "Metal", "Grass", "Fighting", 
								"Psychic", "Dark", "Fire", "Ground", "Electric",
								"Normal", "Flying", "Dragon"};
		String[] attacksNormal = {"Growl", "Leer", "Quick Attack", "Tackle", "Takedown", 
								"Stun", "Quick Punch"};
		String[] attacksLightning = {"Shockwave", "Thundershock", "Thunder Tackle", "Thunder Tale"};
		pokemonArr(pokeTypes);
	}
	
	//Things to add in:
	/* -HP 
	 * - Speed
	 * - Strength
	 * - Defense
	 * - Attack
	 * 
	 */
	public static void pokemonArr(String[] arr){
		String pokeChoice = arr[(int)(Math.random() * arr.length)];
		System.out.println("The computer picked a " + pokeChoice + " type");
		Scanner input = new Scanner(System.in);
		System.out.println("What type do you choose?");
		String guess = input.nextLine();
		System.out.println("What level do you want?");
		int guess1 = input.nextInt();

		System.out.println("Whoa that was a tough battle but you won!");
		
		
		
	}
}
