package com.example.demo;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.annotation.Id;

public class Guess {
	
	private String playerGuess;
	private int playerScore;

	
	public Guess(String playerGuess, int playerScore){
		this.playerGuess = playerGuess;
		this.playerScore = playerScore;
	}
	
	
	public String playerGuess() {
		return playerGuess;
	}
	
	public void setPlayerGuess(String playerGuess) {
		this.playerGuess = playerGuess;
	}
	
	public int playerScore() {
		return playerScore;
	}
	
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	
}
