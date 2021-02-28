package com.example.demo;

import java.util.Date;
import java.util.List;

public class GameRecord {
	

	private String username;
	private List<String> playerGuess;
	private List<String> playerScore;
	private List<String> cpuGuess;
	private List<String> cpuScore;
	private String cpuSecretWord;
	private String userSecretWord;
	private boolean result;
	private String date;
	
	
	public GameRecord(String username, List<String> playerGuess, List<String> playerScore, 
			List<String> cpuGuess, List<String> cpuScore, boolean result, 
			String userSecretWord, String cpuSecretWord, String date) {
		this.username = username;
		this.playerGuess = playerGuess;
		this.playerScore = playerScore;
		this.cpuGuess = cpuGuess;
		this.cpuScore = cpuScore;
		this.result = result;
		this.userSecretWord = userSecretWord;
		this.cpuSecretWord = cpuSecretWord;
		this.date = date;
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

	public String getCpuSecretWord() {
		return cpuSecretWord;
	}

	public void setCpuSecretWord(String cpuSecretWord) {
		this.cpuSecretWord = cpuSecretWord;
	}

	public String getUserSecretWord() {
		return userSecretWord;
	}

	public void setUserSecretWord(String userSecretWord) {
		this.userSecretWord = userSecretWord;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}