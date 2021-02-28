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
var bodyParser= require('body-parser')

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

// image stuff (mutlipart formdata)
var multer = require('multer');
var storage = multer.diskStorage(
    {
        destination: '../../imgs/',
    }
);

var imgPath = '/home/ubuntu/cse356_twitter_clone/imgs/';

var upload = multer({storage: storage});

var app = express();

const validation_key = 'thisvalidationkey356';
const backdoorkey = 'abracadabra';	

var dbIP = '130.245.168.180';

//app.use(parser.urlencoded({ extended: false }));
//app.use(parser.json());
app.use(bodyParser.urlencoded({extended: true}))

app.use(function (req, res, next) {
res.header("Access-Control-Allow-Origin", "*");
res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
next();
});

app.listen(3000, function() {
	console.log("server started on port 3000");
});

app.post('/addmedia', upload.single('content'), function(req,res){
    console.log('hit /addmedia');
    console.log(req.file);
    if(req.headers.cookie){
        var extension = req.file.mimetype.split("/")[1];

        fs.rename(req.file.path, req.file.path+"."+extension, function(err){
            if(err){
                console.log(err);
            }
            else{
                console.log('works?');
            }
        });
        try{
            MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
                if(err){
                    res.end(JSON.stringify({'status':'error'}));
                } else{
                    // get current user                    
                    var authToken = req.headers.cookie.split("=")[1];
                    var decoded = jwt.verify(authToken, jwtSecretKey);
                    var dbtemp = client.db('twitterdb');
                    var mediaItem = {
                        'username':decoded.username,
                        'path':req.file.path+extension,
                        'mediaId':req.file.filename,
                        'extension':extension,
                        'size':req.file.size,
                        'usedInTweet': false
                    }                
                    var item = dbtemp.collection('media').insert(mediaItem)
                        .then(function(doc){
                            if(!doc){
                                res.end(JSON.stringify({'status':'error'}));
                            } else {
                                console.log(doc);
                                res.end(JSON.stringify({'status':'OK', 'id':req.file.filename}));
                            }
                    });
                }
            });

        } catch(err){

        }
    } else {
        // delete file 
        // return error 
        fs.unlink(req.file.path);
        res.end(JSON.stringify({'status':'error'}));
    }
    
});

app.get('/media/:id', function(req,res){    
    var id = req.params.id;
    MongoClient.connect('mongodb://'+dbIP+'/twitterdb', function(err,client){
        if(err){
            res.end(JSON.stringify({'status':'error'}));
        } else{
            var dbtemp = client.db('twitterdb');
            var item = dbtemp.collection('media').findOne({'mediaId':id})
                .then(function(doc){
                    if(!doc){
                        res.end(JSON.stringify({'status':'error'}));
                    } else {
                        console.log(doc);
                        res.status(200).sendFile(imgPath+doc.mediaId+"."+doc.extension);
                    }
                });
        }
    });
});

