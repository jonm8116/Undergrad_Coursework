var mysql = require('mysql'),
	url = require('url'),
	express = require('express'),
	bodyParser = require('body-parser');

var con = mysql.createConnection({
	  host: "localhost",
	  user: "root",
	  password: "",
	  database: "mydb"
});

// starting the server
var app = express();

// parse application/json
app.use(bodyParser.json());
// sends entire directory
app.use(express.static(__dirname));

app.get('/search/:select.:table', function (req, res) {
    var urlParam = req.params;
	con.query('select ? from ?', [urlParam.select, urlParam.table], function(err, results, fields){
		if(err) console.log(err);
		else{
			res.json(results);
		}
	});
});

app.post('/insert/:table.:columns.:values', function (req, res) {
	var urlParam = req.params;
	con.query('insert into ? (?) values (?)', [urlParam.table, urlParam.columns, urlParam.values], function(err, results, fields){
		if(err) console.log(err);
		else{
			res.send('Successfully inserted');
		}
	});
});

app.listen(3000);
console.log("Express server listening");


// Exmaples
//Respond with Hello World! on the homepage:
//app.get('/', function (req, res) {
//  res.send('Hello World!')
//});
//
//Respond to POST request on the root route (/), the application’s home page:
//app.post('/', function (req, res) {
//  res.send('Got a POST request')
//});
//
//Respond to a PUT request to the /user route:
//app.put('/user', function (req, res) {
//  res.send('Got a PUT request at /user')
//});
//
//Respond to a DELETE request to the /user route:
//app.delete('/user', function (req, res) {
//  res.send('Got a DELETE request at /user')
//});
