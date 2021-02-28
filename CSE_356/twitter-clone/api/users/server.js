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

//var sendmail = require('sendmail')(); //temp

var app = express();

const validation_key = 'thisvalidationkey356';
const backdoorkey = 'abracadabra';	

app.use(parser.urlencoded({ extended: false }));
app.use(parser.json());
app.use(function (req, res, next) {
res.header("Access-Control-Allow-Origin", "*");
res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
next();
});

app.listen(3000, function() {
	console.log("server started on port 3000");
});

var dbIP = '130.245.168.180';

/* 
    ADD USER ENDPOINT
    - adds a disabled user to monogo database
    - sends email to user through email link
*/
app.post('/adduser', function(req,res) {
	console.log("hit /adduser");

    var transporter = nodemailer.createTransport({
		service: 'gmail',
		auth: {
				user:email,
				pass:pass
			}
	});

	MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));

		} else{
			console.log("hit else statement in /adduser");
			//console.log(req.body);
			var dbtemp = client.db('twitterdb');
			var test = {
                'username': req.body.username,
                'password':req.body.password, 
                'email': req.body.email,
                'followers': 0,
                'following': 0,
                'followerList': [],
                'followingList': [],
                'validate': false
            };
			//check 
			dbtemp.collection('users').insertOne(test);

            
	        var mailOptions = {
		        from: email,
		        to: req.body.email,
		        subject: 'cse356 twitter: verify needed',
		        text: 'Use this key to verify <' + validation_key +'> '
	        };

            transporter.sendMail(mailOptions, function(info, err){
                    if(err){
                        console.log(err);
                        res.end(JSON.stringify({'status': 'ERROR'}));
                        res.end(JSON.stringify({'status': 'error'}));
                    } else {
                        console.log("SENT MAIL");
                        //console.log(info);
                        res.end(JSON.stringify({'status': 'OK'}));
                    }
                });
			res.end(JSON.stringify({'status': 'OK'}));
		}
		res.end(JSON.stringify({'status': 'OK'}));
	});
});
	
/*
    VERIFY USER
    - Sends request to verify an already created user 
*/
app.post('/verify', function(req, res) {
	console.log("hit /verify endpoint");
    
    var email = req.body.email;
    var key = req.body.key;
    console.log(req.body);

    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));

		} else{
			console.log("hit else statement in /verify POST");
			console.log(req.body);
			var dbtemp = client.db('twitterdb');
            dbtemp.collection('users').findOne({'email': email}, function(err, doc){
                console.log("in findOne callback");
                console.log(doc);
				
				console.log("key1");
				console.log(key);
				console.log("key2");
				console.log(validation_key);

                if(key == validation_key || key == backdoorkey){
					console.log("KEY VALID");
                    dbtemp.collection('users').update(doc, 
                        {
                            'username':doc.username,
                            'password':doc.password,
                            'email': doc.email,
                            'followers': doc.followers,
                            'following': doc.following,
                            'followersList': doc.followersList,
                            'followingList': doc.followingList,
                            'validate':true
                        }, function(err, result){
                            console.log("in update callback");
                            console.log(result);
						    res.end(JSON.stringify({'status': 'OK'}));
			
                    }); 
                } else {
					console.log("FAIL: INCORRECT KEY");
					res.end(JSON.stringify({'status': 'error'}));
				}
            });
		}
	});
	console.log("request body below");
	//console.log(req.body);
});

/*
    LOGIN ENDPOINT
    - allow user to login with credentials
    - store currently logged in user with jwt
*/
app.post('/login', function(req,res){


    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		console.log("hit mongoclient connect");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));

		} else{
			console.log(req.body);
            //user credentials
            var username = req.body.username;
            var password = req.body.password;
            console.log('username is ' + username);

			var dbtemp = client.db('twitterdb');
            dbtemp.collection('users').findOne({'username': req.body.username}, function(err, doc){
                console.log("in findOne callback");
                console.log(doc);
                if(!doc){
                    console.log('hit null element');
                    res.end(JSON.stringify({'status': 'error'}));
                }

                else if(doc.validate){
                    //password check
                    console.log('inside password check');
                    if(username == doc.username && password == doc.password){ 
                        //do jwt stuff
                        const token = jwt.sign(doc, jwtSecretKey, {
                            algorithm: 'HS256',
                            expiresIn: jwtExpireTime
                        });
                        res.cookie('token', token, { maxAge: jwtExpireTime * 1000});
                        res.end(JSON.stringify({'status': 'OK'}));

                    } else{
						console.log("INCORRECT CREDENTIALS");
                        res.end(JSON.stringify({'status': 'error'}))
                    }
                } 
                
                else {
                    console.log('inside login not validate');
                    res.end(JSON.stringify({'status': 'error'}));
                }
            });
		}
	});
});

/*
    LOGOUT ENDPOINT
    - logout user by clearing cookie
*/
app.post('/logout', function(req,res){
    res.clearCookie('token');
	res.end(JSON.stringify({'status': 'OK'}));
});

/*  GET USER ENDPOINT
*/
app.get('/user/:username', function(req,res){
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		console.log("user username");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));
		} else{
			console.log("hit else statement in /username/<username> GET");
            console.log(req.url);
            console.log(req.params);
            var account = req.url.substring(6, req.url.length);
			var dbtemp = client.db('twitterdb');
            dbtemp.collection('users').findOne({'username': account}, function(err, doc){
                console.log("in findOne callback /username");
                if(err){
                    console.log(err);
                    res.end(JSON.stringify({'status':'error'}));
                } else {
                    console.log(doc);
                    if(doc){
                        console.log('found doc');
                        res.end(JSON.stringify({'status':'OK','user':{'email':doc.email,'followers':doc.followers,'following':doc.following}}));
                    } else {
                        res.end(JSON.stringify({'status':'error'}));
                    }
                }
            });
		}
	});

});

/*  GET USER POSTS ENDPOINT
*/
app.get('/user/:username/posts', function(req,res){
    //first do error checking
    console.log('/user/<username>/posts');
    console.log(req.url);
    console.log(req.params);
    console.log('REQ QUERY BELOW');
    console.log(req.query.limit);
    var postLimit;
    
    if(req.query.limit){
        if(req.query.limit > 200){
            postLimit = 200;
        } else{
            postLimit = req.query.limit
        }
    } else{
        postLimit = 50;
    }
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		console.log("user username");
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));
		} else{
			console.log("hit else statement in /username/<username> GET");
            console.log(req.url);
            console.log(req.params);
            var account = req.params.username;
			var dbtemp = client.db('twitterdb');
            dbtemp.collection('tweets').find({'username': account}).limit(postLimit).toArray(function(err, doc){
                console.log("in find callback /posts");
                if(err){
                    console.log(err);
                    res.end(JSON.stringify({'status':'error'}));
                } else {
                    console.log('found doc');
                    //console.log(doc);
                    var resultArr = [];
                    for(var i=0; i<doc.length; i++){
                       resultArr.push(doc[i]._id);
                    }
                    res.end(JSON.stringify({'status':'OK','items':resultArr}));
                }
            });
		}
	});
});

app.get('/user/:username/followers', function(req,res){
    var userLimit;
    if(req.query.limit){
        if(req.query.limit > 200){
            userLimit = 200;
        } else{
            limit = req.query.limit;
        }
    } else { 
        userLimit = 50;
    }
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));
		} else{
			console.log("hit else statement in /username/followers GET");
            console.log(req.url);
            console.log(req.params);
            var account = req.params.username;
			var dbtemp = client.db('twitterdb');
            dbtemp.collection('users').findOne({'username': account}, function(err, doc){
                console.log("in find callback /followers");
                if(err){
                    console.log(err);
                    res.end(JSON.stringify({'status':'error'}));
                } else {
                    console.log('found doc');
                    if(doc){
                        resultArr = [];
                        console.log(doc);
                        for(var i=0; i<doc.followerList.length && i<userLimit; i++){
                            resultArr.push(doc.followerList[i]);
                        }
                        res.end(JSON.stringify({'status':'OK','users':resultArr}));
                    } else {
                        res.end(JSON.stringify({'status':'error'}));
                    }
                }
            });
		}
	});
});

app.get('/user/:username/following', function(req,res){
    var userLimit;
    if(req.query.limit){
        if(req.query.limit > 200){
            userLimit = 200;
        } else{
            limit = req.query.limit;
        }
    } else { 
        userLimit = 50;
    }
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
		if(err){
			console.log(err);
			res.end(JSON.stringify({'status': 'error'}));
		} else{
			console.log("hit else statement in /username/following GET");
            console.log(req.url);
            console.log(req.params);
            var account = req.params.username;
			var dbtemp = client.db('twitterdb');
            dbtemp.collection('users').findOne({'username': account}, function(err, doc){
                console.log("in find callback /following");
                if(err){
                    console.log(err);
                    res.end(JSON.stringify({'status':'error'}));
                } else {
                    console.log('found doc');
                    if(doc){
                        resultArr = [];
                        for(var i=0; i<doc.followingList.length && i<userLimit; i++){
                            resultArr.push(doc.followingList[i]);
                        }
                        res.end(JSON.stringify({'status':'OK','users':resultArr}));
                    } else {
                        res.end(JSON.stringify({'status':'error'}));
                    }
                }
            });
		}
	});
});


app.post('/follow', function(req,res){
    // check if user is signed in
    var currentUsername;
    if(req.headers.cookie){
        console.log('token found in /follow');
        var authToken = req.headers.cookie.split('=')[1];
        var decoded = jwt.verify(authToken, jwtSecretKey);
        currentUsername = decoded.username;
    } else {
        //user not signed in send error
        console.log('user not signed in');
        res.end(JSON.stringify({'status': 'error'}));
    }
    //connect to Mongo
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
        // check if request body empty
        var dbtemp = client.db('twitterdb');
        if(req.body.constructor === Object && Object.keys(req.body).length === 0) {
            // default to true value follow
            dbtemp.collection('tweets').findOne({'username': req.body.username}, function(err, doc){
                if(doc){
                    followUser(dbtemp, currentUsername, req.body.username, res, true); 
                } else {
                    res.end(JSON.stringify({'status':'error'})); }
            });
        } else {
            // follow
            dbtemp.collection('tweets').findOne({'username': req.body.username}, function(err, doc){
                if(doc){
                    followUser(dbtemp, currentUsername, req.body.username, res, req.body.follow);
                } else {
                    res.end(JSON.stringify({'status':'error'}));
                }
            });
        }
    });
});

function followUser(dbtemp, currentUsername, userToFollow, res, isFollow){
                if(isFollow){
                    dbtemp.collection('users').update({'username':currentUsername}, {'$push':{'followingList': userToFollow}, '$inc':{'following': 1} }, function(err, result){
                        if(err){
                            console.log(err);
                            console.log('/follow if 1');
                            res.end(JSON.stringify({'status':'error'}));
                        }
                        dbtemp.collection('users').update({'username':userToFollow}, {'$push':{'followerList': currentUsername}, '$inc':{'followers': 1} }, function(err, result){
                            if(err){
                                console.log(err);
                                console.log('/follow if 2');
                                res.end(JSON.stringify({'status':'error'}));
                            } else {
                                res.end(JSON.stringify({'status':'OK'}));
                            }
                        });
                    });
                    
                } else {
                    dbtemp.collection('users').update({'username':currentUsername}, {'$pull':{'followingList': userToFollow}, '$inc': {'following':-1} }, function(err, result){
                        if(err){
                            console.log(err);
                            console.log('/follow if 3');
                            res.end(JSON.stringify({'status':'error'}));
                        }
                        dbtemp.collection('users').update({'username':userToFollow}, {'$pull':{'followerList': currentUsername}, '$inc': {'followers':-1} }, function(err, result){
                            if(err){
                                console.log(err);
                                console.log('/follow if 4');
                                res.end(JSON.stringify({'status':'error'}));
                            } else {
                                res.end(JSON.stringify({'status': 'OK'}));
                            }
                        });
                    });
                }
}
