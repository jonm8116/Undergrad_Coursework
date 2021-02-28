package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
	
	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/menu")
	public String menu() {
		return "index";
	}
	
	@RequestMapping("/pastgames")
	public String userPastGames() {
		return "pastgames";
	}
	
	@RequestMapping("/profile")
	public String directToProfile() {
		return "menu";
	}
	
	//Below have mappings for js files
	@RequestMapping("/gamejs")
	public String gameJS() {
		return "game.js";
	}
	
	@RequestMapping("/gamerecordsjs")
	public String gameRecordsJS() {
		return "gamerecords.js";
	}
	
	@RequestMapping("/loginjs")
	public String loginJS() {
		return "login.js";
	}
}
