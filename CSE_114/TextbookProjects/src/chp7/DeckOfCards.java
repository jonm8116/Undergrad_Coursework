package chp7;
import java.util.Scanner;

public class DeckOfCards {
	
	//This program will choose a 4 random cards from a deck of cards
	//It does this by creating a large array of 51 values
	//The suits are broken down by dividing the value of the card by 13 (cardNumber / 13)
	//Modular division is used to determine the rank of the card (cardNumber % 13)
	public static void main(String[] args) {
		System.out.println("Welcome to the Deck of Card generator");
		System.out.println("Enter 0 in order to run the program");
		Scanner input = new Scanner(System.in);
		int run = input.nextInt();
		if(run == 0){
			for(int i=1; i<=4; i++){
				DeckOfCards();
			}
		}

	}
	
	public static void DeckOfCards(){
		int[] deck = new int[52];
		for(int i=0; i<deck.length; i++){
			deck[i] = i;
		}
		//Now need to pick four cards
		//Let's start picking out one card
		int cardNumber = deck[(int)(Math.random()*51)];
		int cardSuit = (int)(cardNumber / 13);
		String cardSuitString = "";
		if(cardSuit == 0){
			cardSuitString = "Spades";
		}
		else if(cardSuit == 1){
			cardSuitString = "Hearts";
		}
		else if(cardSuit == 2){
			cardSuitString = "Diamonds";
		}
		else if(cardSuit == 3){
			cardSuitString = "Clubs";
		}
		//switch statement for card rank
		//Might have been more efficient to make an array 
		int cardRankNum = cardNumber % 13;
		String cardRank = "";
		switch(cardRankNum){
		case 0: 
			cardRank = "Ace";
			break;
		case 1: 
			cardRank = "2";
			break;
		case 2: 
			cardRank = "3";
			break;
		case 3: 
			cardRank = "4";
			break;
		case 4: 
			cardRank = "5";
			break;
		case 5: 
			cardRank = "6";
			break;
		case 6: 
			cardRank = "7";
			break;
		case 7: 
			cardRank = "8";
			break;
		case 8: 
			cardRank = "9";
			break;
		case 9: 
			cardRank = "10";
			break;
		case 10: 
			cardRank = "Jack";
			break;
		case 11: 
			cardRank = "Queen";
			break;
		case 12: 
			cardRank = "King";
			break;
		}
		System.out.println("You picked " + cardRank + " of " + cardSuitString);
	}

}
