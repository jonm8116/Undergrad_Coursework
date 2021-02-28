package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class Controller {
	@Autowired
	private UserRepository repository;
	@Autowired
	private GameRecordRepository gameRepository;
	
	/**
	 * Creates a user who decides to
	 * register for the game
	 * @param user
	 */
	@RequestMapping(value = "/addusers", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User user) {
		if(repository.findByUsername(user.getUsername())!= null) {
			return "Duplicate";
		}else {
			repository.save(user);
			return "success";
		}
	}
	/**
	 * Returns the list of users that are
	 * currently added in the database
	 * @return list of users
	 */
	//@CrossOrigin
	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	public List<User> getUsers(){
		return repository.findAll();
	}
	/**
	 * Creates a record for a game played
	 * by the previous user
	 * @param game
	 */
	@RequestMapping(value = "/addGameRecord", method = RequestMethod.POST)
	public void createGameRecord(@Valid @RequestBody GameRecord game) {
		gameRepository.save(game);
	}
	
	 /**
	 * Returns a list of game records
	 * based on logged-in user
	 * @return list of game records
	 */
	@RequestMapping(value = "/username/{user}", method = RequestMethod.GET)
	public List<GameRecord> getAllGames(@PathVariable String user) {
		return gameRepository.findByUsername(user);
	}
	

	

	
}
