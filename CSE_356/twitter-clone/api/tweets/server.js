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
var ObjectId = require('mongodb').ObjectID;

//elastic search
var es = require('elasticsearch');
var elasticClient = new es.Client({
    host: '192.168.122.22:9200',
    log: 'trace'
});

//jwt stuff
var jwt = require('jsonwebtoken');
var jwtSecretKey = 'jwt_secret_secret_key';
var jwtExpireTime = 300;

var app = express();

const validation_key = 'thisvalidationkey356';
const backdoorkey = 'abracadabra';	

var dbIP = '130.245.168.180';

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

async function mediaInTweet(dbtemp, media){
    dbtemp.collection('media').find({'mediaId':{'$in': media} }).toArray(function(err,doc){
    if(doc){
        console.log('outside doc.find(x => x.usedInTweet == true');
        console.log(doc);
           
        if(doc.find(x => x.usedInTweet == true)){
            console.log('hit inside');
            return true;
        } else {
            // change usedInTweet value for each doc
            console.log('inside else for checking media in tweet');
            for(var i=0; i<media.length; i++){
                dbtemp.collection('media').findOneAndUpdate({'mediaId':media[i]}, {'$set':{'usedInTweet':true}}, function(err,doc){
                    console.log('inside media updateMany');
                    if(err){
                        console.log(err);
                    } else {

                    }
                });
            }
            return false; 
        }
    }
    });
}





/*
    TODO:
        - add retweets and replies
            - check childType -> if retweet query for parent content and increase
            retweet number
        - media array
*/

app.post('/additem', function(req, res){
    //console.log(req.headers.cookie);
    //var authToken = req.headers.cookie.split('=')[1];
    //var decoded = jwt.verify(authToken, jwtSecretKey);
    //console.log(decoded);
    console.log('hit /additem endpoint');    
    // Insert data into Mongo
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
        if(err){
            console.log('Error connecting to mongo server');
            console.log(err);
            res.end(JSON.stringify({'status':'error'}));
        } else{
            console.log('inside mongodb connect');
            var dbtemp = client.db('twitterdb');
            if(req.headers.cookie){
                console.log('inside token found');
                var authToken = req.headers.cookie.split('=')[1];
                var decoded = jwt.verify(authToken, jwtSecretKey);
                console.log("decoded is " + decoded.username);                

                if(req.body.content){
                    var tweet = {}
                    // do check for each field
                    if(req.body.childType){
                        tweet['childType'] = req.body.childType;
                        //query for parent item
                        if(req.body.childType == "retweet"){
                            dbtemp.collection('tweets').findOneAndUpdate({'_id':req.body.parent}, { $inc: { 'retweeted': 1 } }, function(err, doc){
                                if(doc){
                                     
                                }
                            });
                        }
                    }
                    if(req.body.parent){
                        tweet['parent'] = req.body.parent;
                    }
                    if(req.body.media){
                        tweet['media'] = req.body.media;
                        // Do extensive check to see if in other items
                        console.log('check for media');
                        console.log(req.body.media);
                        var value = await mediaInTweet(dbtemp, req.body.media);
                        if(value){
                            res.end(JSON.stringify({'status':'error'}));
                        }

                        


                    }
                    //set all other values
                    tweet['username'] = decoded.username;
                    tweet['property'] = {
                        'likes': 0
                    }
                    tweet['retweeted'] = 0;
                    tweet['content'] = req.body.content;
                    tweet['timestamp'] = Date.now(); 
                    dbtemp.collection('tweets').insert(tweet, function(err){
                        //after successful insert
                        if(err){
                            console.log(err); 
                            res.end(JSON.stringify({'status':'error'}));
                        } else{
                            //insert into elasticsearch first
                            //insertES(tweet._id, tweet);
                            //send JSON response
                            res.end(JSON.stringify({'status':'OK','id':tweet._id}));
                        }
                    });
                } else{
                    //in case content is null
                    res.end(JSON.stringify({'status':'error'}));
                }
            // in else case -> the user didn't log in
            } else{
                console.log('no token found');
                res.end(JSON.stringify({'status':'error'}));
            }
        }
    }); 
    
});

function insertES(id, tweetBody){
    elasticClient.index({
        index: 'tweets',
        type: 'tweets',
        body:  tweetBody
    }, function(err,resp,status){
        console.log('error in insert into es');
        console.log(err);
    });
}


app.get('/item/:id', function(req,res){
    var itemId = req.url.substring(6,req.url.length);
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
        if(err){
            res.end(JSON.stringify({'status':'error'}));
        } else{
            var dbtemp = client.db('twitterdb');
            console.log('item id ' + itemId);
            var item = dbtemp.collection('tweets').findOne({'_id':ObjectId(itemId)})
                .then(function(doc){
                    if(!doc){
                        res.end(JSON.stringify({'status':'error'}));
                    } else {
                        console.log(doc);
                        var tweetItem = {}
                        // do check for data fields
                        if(doc.childType){
                            tweetItem['childType'] = doc.childType;
                        }                        
                        if(doc.parent){
                            tweetItem['parent'] = doc.parent;
                        }
                        if(doc.media){
                            tweetItem['media'] = doc.media;
                        }
                        // fill out rest of fields
                        tweetItem['id'] = doc._id;
                        tweetItem['username'] = doc.username;
                        tweetItem['property'] = {
                            'likes': doc.property.likes
                        }
                        tweetItem['retweeted'] = doc.retweeted;
                        tweetItem['content'] = doc.content;
                        tweetItem['timestamp'] = doc.timestamp;
    
                        res.end(JSON.stringify({'status':'OK','item':tweetItem}));
                    }
                });
        }
    });
});

/*
    SEARCH ENDPOINT -> WILL BE MOVED TO OTHER API FILE
    *   other note: will also need to change the 
        implementation of how the search is written

*/

app.post('/search', function(req,res){
    console.log('hit inside /search endpoint');
    // first do error checking for limit
    console.log(req.body);
    var searchLimit;
    if(req.body.limit){
        if(req.body.limit > 100){
            searchLimit = 100;
        } else{
            searchLimit = req.body.limit;
        }
    } else {
        searchLimit = 25;
    }
    
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
        if(err){
            console.log(err);
            res.end(JSON.stringify({'status':'error'}));
        } else{
            console.log('hit inside else of search');
            var dbtemp = client.db('twitterdb');
            
            var queryOptions = {};
            var skipFields = true;
            var searchEmpty = false;
            var hasRank = false;
            var rankTerm = "";
            // first check if there is a following field 
            if(req.body.following){
                // search only for 
                queryOptions['following'] = req.body.following;
                skipFields = false;
                if(req.headers.cookie){
                    var authToken = req.headers.cookie.split('=')[1];
                    var decoded = jwt.verify(authToken, jwtSecretKey);
                    dbtemp.collection('users').findOne({'username':decoded.username}, 
                    function(err, doc){
                        // dbtemp.collection('tweets').find({'timestamp':{'$gte':req.body.timestamp}, 'username': {'$in':doc.following}}).limit(searchLimit).toArray(function(err,doc){
                    console.log('inside /search following');

                        dbtemp.collection('tweets').find({'username': {'$in':doc.followingList}}).limit(searchLimit).toArray(function(err,doc){
                            if(err){
                                console.log(err);
                                res.end(JSON.stringify({'status':'error'}));
                            } 
                            res.end(JSON.stringify({'status':'OK', 'items':doc}));
                        });
                    });
                                        

                } else {
                    console.log('hit else in req.headers.cookie');
                    res.end(JSON.stringify({'status':'error'}));
                }
              //new else added  
            } else{
                if(req.body.q){
                    console.log('check search q');
                    if(req.body.q == ''){
                        searchEmpty=true;
                    }
                    queryOptions['$text'] = {
                        '$search': req.body.q
                    };
                }
                if(req.body.username){
                    queryOptions['username'] = req.body.username;
                }
                // before: {'timestamp':{'$lte':req.body.timestamp}}
                if(req.body.timestamp){
                    queryOptions['timestamp'] = {
                        '$gte': req.body.timestamp
                    }
                }
                if(req.body.rank){
                    hasRank = true;
                    if(req.body.rank == "time"){
                        rankTerm = "time";
                    } else{
                        rankTerm = "interest";
                    }
                }
                if(req.body.replies){
                    if(req.body.parent){
                        queryOptions['parent'] = req.body.parent;
                    } else {
                        //no parent specified
                        res.end(JSON.stringify({'status':'error'}));
                    }
                }
                console.log(queryOptions);
                if(searchEmpty){
                    console.log('if searchEmpty');
                    dbtemp.collection('tweets').find({'timestamp':{'$gte':req.body.timestamp}}).limit(searchLimit).toArray(function(err,doc){
                        if(err){
                            console.log(err);
                            res.end(JSON.stringify({'status':'error'}));
                        } else{
                            res.end(JSON.stringify({'status':'OK','items':doc}));
                        }               
                    });

                } else{
                    // check for rank
                    // check for hasMedia
                    console.log('/search last else');
                    if(hasRank){
                        if(rankTerm=="time"){
                            dbtemp.collection('tweets').find(queryOptions).limit(searchLimit).sort({"timestamp":1}).toArray(function(err,doc){
                                console.log(err);
                                if(err){
                                    console.log(err);
                                    res.end(JSON.stringify({'status':'error'}));
                                } else{
                                    res.end(JSON.stringify({'status':'OK','items':doc}));
                                }               
                            });
                        } else {
                            dbtemp.collection('tweets').find(queryOptions).limit(searchLimit).sort({"likes":1, "retweeted":1}).toArray(function(err,doc){
                                console.log(err);
                                if(err){
                                    console.log(err);
                                    res.end(JSON.stringify({'status':'error'}));
                                } else{
                                    res.end(JSON.stringify({'status':'OK','items':doc}));
                                }               
                            });
                        }
                    } else {

                        dbtemp.collection('tweets').find(queryOptions).limit(searchLimit).toArray(function(err,doc){
                            console.log(err);
                            if(err){
                                console.log(err);
                                res.end(JSON.stringify({'status':'error'}));
                            } else{
                                res.end(JSON.stringify({'status':'OK','items':doc}));
                            }               
                        });
                    }
                }

            //new else added
            }
        }
    });


});

function compareTimeStamp(obj1, obj2){
    if(obj1.timestamp < obj2.timestamp){
        return 1;
    } else{
        return -1;
    }
    return 0;
}

/*
    DELETE REST ENDPOINT
*/

app.delete('/item/:id', function(req,res){
    console.log('inside delete endpoint');
    var itemId = req.url.substring(6,req.url.length);
    console.log(req.headers.cookie);
    if(req.headers.cookie){
        console.log('inside token found');
        var authToken = req.headers.cookie.split('=')[1];
        var decoded = jwt.verify(authToken, jwtSecretKey);
    
        MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
            if(err){
                res.end(JSON.stringify({'status':'error'}));
            } else{
                var dbtemp = client.db('twitterdb');
                //put delete statement
                
                var item = dbtemp.collection('tweets').findOne({'_id':ObjectId(itemId)})
                    .then(function(doc){
                    if(!doc){
                        res.end(JSON.stringify({'status':'error'}));
                    } else {
                        if(doc.username == decoded.username){
                            var item = dbtemp.collection('tweets').remove({'_id':ObjectId(itemId)})
                                .then(function(doc){
                                    dbtemp.collection('media').remove({'mediaId': { '$in': doc.media }});
                                });
                            res.end(JSON.stringify({'status':'OK'}));
                        } else {
                            res.status(300).end(JSON.stringify({'status':'error'}));
                        }
                    }
                });

            }
        });

    } else {
        console.log('hit error return');
        res.status(300).end(JSON.stringify({'status':'error'}));
    }
});

app.post('/item/:id/like', function(req,res){
    console.log('inside item like endpoint');
    var id;
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
            if(err){
                res.end(JSON.stringify({'status':'error'}));
            } else{
                var dbtemp = client.db('twitterdb');
                //put delete statement

                var item = dbtemp.collection('tweets').findOne({'_id':ObjectId(id)})
                    .then(function(doc){
                    if(!doc){
                        res.end(JSON.stringify({'status':'error'}));
                    } else {
                        if(req.body.likes){
                            // increment likes
                            dbtemp.collection.update(doc, 
                            {
                                'username': doc.username,
                                'property': {
                                    'likes': doc.property.likes + 1
                                },
                                'retweeted': doc.retweeted,
                                'content': doc.content,
                                'timestamp': doc.timestamp

                            }, function(err, result){
                                console.log(result);
                                res.end(JSON.stringify({'status': 'OK'}));
                            });
                        } else {
                            // decrement likes
                            if(doc.property.likes==0){
                                dbtemp.collection.update(doc, 
                                {
                                    'username': doc.username,
                                    'property': {
                                        'likes': 0
                                    },
                                    'retweeted': doc.retweeted,
                                    'content': doc.content,
                                    'timestamp': doc.timestamp

                                }, function(err, result){
                                    console.log(result);
                                    res.end(JSON.stringify({'status': 'OK'}));
                                });

                            } else {
                                dbtemp.collection.update(doc, 
                                {
                                    'username': doc.username,
                                    'property': {
                                        'likes': doc.property.likes - 1
                                    },
                                    'retweeted': doc.retweeted,
                                    'content': doc.content,
                                    'timestamp': doc.timestamp

                                }, function(err, result){
                                    console.log(result);
                                    res.end(JSON.stringify({'status': 'OK'}));
                                });
                            }
                        }
                    }
                });

            }
    });

});
