package com.example.demo;

import java.util.List;

public class GameRecord {
	

	private String username;
	private List<String> playerGuess;
	private List<String> playerScore;
	private List<String> cpuGuess;
	private List<String> cpuScore;
	private boolean result;
	
	
	public GameRecord(String username, List<String> playerGuess, List<String> playerScore, 
			List<String> cpuGuess, List<String> cpuScore, boolean result) {
		this.username = username;
		this.playerGuess = playerGuess;
		this.playerScore = playerScore;
		this.cpuGuess = cpuGuess;
		this.cpuScore = cpuScore;
		this.result = result;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getPlayerGuess() {
		return playerGuess;
	}

	public void setPlayerGuess(List<String> playerGuess) {
		this.playerGuess = playerGuess;
	}

	public List<String> getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(List<String> playerScore) {
		this.playerScore = playerScore;
	}

	public List<String> getCpuGuess() {
		return cpuGuess;
	}

	public void setCpuGuess(List<String> cpuGuess) {
		this.cpuGuess = cpuGuess;
	}

	public List<String> getCpuScore() {
		return cpuScore;
	}

	public void setCpuScore(List<String> cpuScore) {
		this.cpuScore = cpuScore;
	}

	public boolean isResult() {
		return result;
	}


	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}