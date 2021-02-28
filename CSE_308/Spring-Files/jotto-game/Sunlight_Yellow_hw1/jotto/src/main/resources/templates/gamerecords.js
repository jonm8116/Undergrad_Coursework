function loadGames(callback){
	var username = document.cookie.split("=")[2];
	//make get request call
	var wins = 0;
	var lost = 0;
	var total = 0;
	//Get html elements
	var winsTag = document.getElementById("total_wins");
	var lostTag = document.getElementById("total_lost");
	var totalPlayed = document.getElementById("total_played");
	var recordsTag = document.getElementById("game_guess_history_list");
	
//	var game2 = {
//	        word: 'LOSTED',
//	        won: false,
//	        date_created: 'today',
//	        user_list: [{data:'apple', points:'2'}, {data:'pencil', points:'4'}],
//	        comp_list: [{data:'orange', points:'2'}, {data:'babies', points:'4'}],
//
//	    };
	
	axios.get('http://localhost:8080/username/'+username)
	.then(function(response){
		var wordVal;
		var wonVal;
		var date_created;
		var objList = [];
		var playerList = [];
		var cpuList = [];
		
		for(var i=0; i<response.data.length; i++){
//			console.log(JSON.parse(response.data[i].playerGuess));
			//for each guess array
//			var playerGuess = [];
//			var cpuGuess = [];
//			for (var j=0; j<response.data[i].playerGuess; j++){
//				playerGuess.push(JSON.parse(response.data[i].playerGuess[j]));
//			}
//			for (var j=0; j<response.data[i].cpuGuess; j++){
//				cpuGuess.push(JSON.parse(response.data[i].cpuGuess[j]));
//			}
			
			if(response.data[i].result){
				wins = wins + 1;
				var game = {
					word: "WON",
					won: true,
					date_created: "today",
					user_list: [],
					comp_list: []
				};
				objList.push(game);
				
			} else{
				lost = lost + 1;
				var game = {
						word: "LOST",
						won: false,
						date_created: "today",
						user_list: [],
						comp_list: []
				};
				objList.push(game);
			}
			total = total + 1;
		}
		winsTag.innerHTML = wins;
		lostTag.innerHTML = lost;
		totalPlayed.innerHTML = total;
		callback(objList);
//		return objList;
	})
	.catch(function(error){
		console.log(error);
	});
}