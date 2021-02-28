const http = require('http');
const querystring = require('querystring');
const fs = require('fs');
var express = require('express');
var app = express();
var finalhandler = require('finalhandler');
var serveStatic = require('serve-static');

var serve = serveStatic('./assets');

//grid

const server = http.createServer(function(req, res) {
	console.log(req.method);
	var queryData ="";
	var userName = "";
	var datetime = new Date();

	if( req.method == "POST"){
		req.on('data', function(data){
			console.log("hit callback");
			queryData += data;
			userName = querystring.parse(queryData);
			console.log(typeof data);
		});

		req.on('end', function(){
			//print type
			console.log(typeof queryData);			

			req.post = querystring.parse(queryData);
			console.log(querystring.parse(queryData)['name']);
			fs.readFile('./assets/tictactoe.html', function(err, html) {
				console.log("hit inside tic tac toe read");	
				res.writeHeader(200, {'Content-Type': 'text/html'});
				res.write("Hello " + querystring.parse(queryData)['name'] + ", " +datetime);
				res.write(html);
				res.end();
			});
		});
	} else {
		/*
		var handler = finalhandler(req, res);
		serve(req, res, handler);
		*/
		fs.readFile('./assets/form.html', function(err, html) {
			res.writeHeader(200, {'Content-Type': 'text/html'});
			res.write(html);
			res.end();
		});
		
		
	}
});

server.listen('3000', function(){
	console.log("server start on port 3000");
});

//Have REST API listen on port 4000
app.post('/ttt/play', (req, res) => {
	console.log('hit rest endpoint');
});

app.listen(4000, () => {
	console.log("REST API started on port 4000");
	//console.log(app._router.stack);
});
