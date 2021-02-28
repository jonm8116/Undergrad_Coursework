const http = require('http');
const querystring = require('querystring');
const fs = require('fs');
const path = require('path');
var express = require('express');
var router = express.Router();
var parser = require('body-parser');

//Mongo variables
var Db = require('mongodb').Db;
var MongoClient = require('mongodb').MongoClient;
var handlebars = require('handlebars');
var nodemailer = require('nodemailer');

//jwt stuff
var jwt = require('jsonwebtoken');
var jwtSecretKey = 'jwt_secret_secret_key';
var jwtExpireTime = 300;

var app = express();

const validation_key = 'thisvalidationkey356';
const backdoorkey = 'abracadabra';	

app.use(parser.urlencoded({ extended: false }));
app.use(parser.json());

app.listen(3000, function() {
	console.log("server started on port 3000");
	//console.log(app._router.stack);
});

//adduser
// send email in adduser
//change route after 
app.post('/adduser', function(req,res) {
	console.log("hit /adduser");

    var transporter = nodemailer.createTransport({
		service: 'gmail',
		auth: {
				user:'csetestemail300@gmail.com',
				pass:'csetestemail300password'
			}
	});

	MongoClient.connect('mongodb://localhost:27017/tictactoe', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'ERROR'}));

		} else{
			console.log("hit else statement in /adduser");
			console.log(req.body);
			var dbtemp = client.db('tictactoe');
			var test = {'username': req.body.username,'password':req.body.password, 'email': req.body.email, 'validate': false};
			//check 
			dbtemp.collection('users').insertOne(test);
            fs.readFile('./assets/email.html', 'utf-8', function(err, html){
		        var template = handlebars.compile(html);
		        var replace = {
			        validation_key: validation_key,
                    email: req.body.email
		        }
		        var emailhtml = template(replace);
		        console.log(emailhtml);

		        var mailOptions = {
			        from: 'csetestemail300@gmail.com',
			        to: req.body.email,
			        subject: 'Welcome cse356 tictactoe: verify needed',
			        html: emailhtml 
		        };
        		transporter.sendMail(mailOptions, function(info, err){
		        	if(err){
				        console.log(err);
        				res.end(JSON.stringify({'status': 'ERROR'}));
		        	} else {
        				console.log(info);
		        		res.end(JSON.stringify({'status': 'OK'}));
        			}
        		});
	
        	});
        
			res.end(JSON.stringify({'status': 'OK'}));
		}
		res.end(JSON.stringify({'status': 'OK'}));
	});
});

app.post('/verify', function(req, res) {
	console.log("hit /verify endpoint");
    
    var email = req.body.email;
    var key = req.body.key;

    MongoClient.connect('mongodb://localhost:27017/tictactoe', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'ERROR'}));

		} else{
			console.log("hit else statement in /verify GET");
			console.log(req.body);
			var dbtemp = client.db('tictactoe');
            dbtemp.collection('users').findOne({'email': email}, function(err, doc){
                console.log("in findOne callback");
                //console.log(doc);
                if(key == validation_key){
                    dbtemp.collection('users').update(doc, {'username':doc.username,'password':doc.password,'validate':true}, function(err, result){
                        console.log("in update callback");
                        console.log(result);
                    }); 
                }
            });
            res.end(JSON.stringify({'status': 'OK'}));
		}
		res.end(JSON.stringify({'status': 'OK'}));
	});
 

	console.log("request body below");
	//console.log(req.body);
	res.end(JSON.stringify({'status': 'OK'}));
});

// to validate user account
app.get('/verify', function(req,res){
    //email & key validation
    var email = req.query.email;
    var key = req.query.key;

    MongoClient.connect('mongodb://localhost:27017/tictactoe', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'ERROR'}));

		} else{
			console.log("hit else statement in /verify GET");
			console.log(req.body);
			var dbtemp = client.db('tictactoe');
            dbtemp.collection('users').findOne({'email': email}, function(err, doc){
                console.log("in findOne callback");
                console.log(doc);
                dbtemp.collection('users').update(doc, {'username':doc.username,'password':doc.password,'validate':true}, function(err, result){
                    console.log("in update callback");
                    console.log(result);
                });
            });
            res.end(JSON.stringify({'status': 'OK'}));
		}
		res.end(JSON.stringify({'status': 'OK'}));
	});

});

app.post('/login', function(req,res){
    MongoClient.connect('mongodb://localhost:27017/tictactoe', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'ERROR'}));

		} else{
			console.log(req.body);
            //user credentials
            var username = req.body.username;
            var password = req.body.password;

			var dbtemp = client.db('tictactoe');
            dbtemp.collection('users').findOne({'username': req.body.username}, function(err, doc){
                console.log("in findOne callback");
                console.log(doc);
                if(doc.validate){
                    //password check
                    if(username == doc.username && password == doc.password){ 
                        //do jwt stuff
                        const token = jwt.sign(doc, jwtSecretKey, {
                            algorithm: 'HS256',
                            expiresIn: jwtExpireTime
                        });
                        res.cookie('token', token, { maxAge: jwtExpireTime * 1000});
                        res.end(JSON.stringify({'status': 'OK'}));

                    } else{
                        res.end(JSON.stringify({'status': 'ERROR'}))
                    }
                } else {
                    res.end(JSON.stringify({'status': 'ERROR'}));
                }
            });
		}
	});
	
});

app.post('/logout', function(req,res){
    res.cookie.set('token', {maxAge: Date.now()});
	res.end(JSON.stringify({'status': 'OK'}));
});

app.post('/listgames', function(req,res){
	res.end(JSON.stringify({'status': 'OK'}));
});

app.post('/getgame', function(req,res){
	res.end(JSON.stringify({'status': 'OK'}));
});

app.post('/getscore', function(req,res){
	res.end(JSON.stringify({'status': 'OK'}));
});

//grid
//router.get('/ttt', function(req,res){
//	res.sendFile(path.join(__dirname+'/assets/form.html'));
//});

//Send css file as its own route
//router.get('/ttt/css', function(req, res){
//	res.sendFile(__dirname + '/assets/tictacstyle.css');
//});

//app.post('/ttt/', function(req,res) {
//	var datetime = new Date();
//	fs.readFile('./assets/tictactoe.html', function(err,html){
//		res.writeHeader(200, {'Content-Type': 'text/html'});
//		res.write("Hello " + req.body.name + ", " + datetime);
//		res.write(html);
//		res.end();
//	});
//				
//});

function checkGrid(tacgrid){
	var oCounter = 0;
	var xCounter = 0;
	var isWinner = '';
	var isMoveDone = false;
	for(var i=0; i<tacgrid.length; i++){
			if(tacgrid[i] == 'X'){
				xCounter += 1;
			} else if(tacgrid[i] == ' '){
				if(!isMoveDone){
					console.log("hit inside not move done");
					tacgrid[i] = 'O';
					oCounter += 1;
					isMoveDone = true;
					console.log(tacgrid);
				}
			} else if(tacgrid[i] == 'O'){
				oCounter += 1;	
			}

			if(xCounter >= 3){
				isWinner = 'W';
			} else if(oCounter >= 3) {
				isWinner = "L";
			} else{
				isWinner ="";
			}
		}
	return [tacgrid,isWinner];
	
}

// play endpoint
app.post('/ttt/play', async(req, res, next) => {
	console.log('hit /ttt/play endpoint');
	//console.log(req.body.grid);
	var tacgrid = JSON.parse(JSON.stringify(req.body.grid));
	console.log(tacgrid);
	
	var retval = await checkGrid(tacgrid);
	
	
	res.setHeader('Content-Type', 'application/json');
	res.end(JSON.stringify({"grid": retval[0], "winner": retval[1]}))	
	
});

//serve directory
//app.use(express.static(__dirname + 'assets/'));

//serve directory


