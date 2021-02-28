function addUser(){
	var username = document.getElementById("username").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	console.log(username);
	console.log(email);
	console.log(password);
	var xhr = new XMLHttpRequest();
	var url = "http://130.245.170.128/adduser";
	xhr.open("POST", url, true);		
	//Finished request
	xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			console.log("finished?")
			console.log(xhr.responseText);
			var url = "./verify.html?email="+encodeURIComponent(email)+"&username="+encodeURIComponent(username);
			document.location.href=url;
		}
	}	
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify({"username": username,"email":email,"password":password}));	
}

function logInUser(){
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var xhr = new XMLHttpRequest();
	var url = "http://130.245.170.128/login";
	xhr.open("POST", url, true);		
	//Finished request
	xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			console.log("finished?")
			console.log(xhr.responseText);
			var url = "./temp.html?username="+encodeURIComponent(username);
			document.location.href=url;
		}
	}	
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify({"username": username,"password":password}));	
}

function logOutUser(){
	var xhr = new XMLHttpRequest();
	var url = "http://130.245.170.128/logout";
	xhr.open("POST", url, true);		
	//Finished request
	xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			console.log("finished?")
			console.log(xhr.responseText);
			var url = "./temp.html?username="+encodeURIComponent(username);
			document.location.href=url;
		}
	}	
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();	
}

function verifyUser(){
	var key = document.getElementById("key");
	var verifyURL = new URL(window.location.href);
	var email = verifyURL.searchParams.get("email");
	var xhr = new XMLHttpRequest();
	var url = "http://130.245.170.128/verify";
	xhr.open("POST", url, true);		
	//Finished request
	xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			console.log("finished?")
			console.log(xhr.responseText);
			var url = "./temp.html?username="+encodeURIComponent(username);
			document.location.href=url;
		}
	}	
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify({"email": email,"key":key}));	
}

function loadUser(){
	var userURL = new URL(window.location.href);
	var username = userURL.searchParams.get("username");
	var xhr = new XMLHttpRequest();
	var profileURL = "http://130.245.170.128/user/"+encodeURIComponent(username);
	var postsURL = "http://130.245.170.128/user/" +encodeURIComponent(username)+ "/posts";
	var urllist = [profileURL, postsURL];

	for(var i=0; i<urllist.length; i++){
		xhr.open("GET", urllist[i], true);	
		//Finished request
		xhr.onreadystatechange = function() {
			if (this.readyState == XMLHttpRequest.DONE) {
				console.log("finished?")
				console.log(xhr.responseText);
				//var url = "./temp.html?username="+encodeURIComponent(username);
				var jsonData = JSON.stringify(xhr.responseText);
				if(jsonData['email']){
					console.log('found email');
					console.log(jsonData['email']);
				}
				if(jsonData['items']){
					console.log('found items');
					console.log(jsonData['items']);
					for(var j=0; j<jsonData['items'].length; j++){
						urllist.push("http://130.245.170.128/item/" + encodeURIComponent(jsonData['items'][j]));
					}
				}
				if(jsonData['item']){
					console.log('found single item');
					console.log(jsonData['item']);
				}
				//document.location.href=url;
			}
		}	
		//xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send();	
	}
}

function addItem() {
	var content = document.getElementById("content");
	var childType = document.getElementById("childType");
}

//look up followers list