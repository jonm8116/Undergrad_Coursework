var mysql = require('mysql'),
	url = require('url'),
	express = require('express'),
	bodyParser = require('body-parser');

var con = mysql.createConnection({
	  host: "localhost",
	  user: "root",
	  password: "N!njamaster8",
	  database: "mydb"
});

// starting the server
var app = express();
// parse json
app.use(bodyParser.json());
// sends entire directory
app.use(express.static(__dirname));
//app.use('/controller', express.static(__dirname));

app.get('/search/:select.:table', function (req, res) {
    var urlParam = req.params;
	if(urlParam.select === '*'){
		con.query('select * from ??', [urlParam.table], function(err, results, fields){
			if(err){
				console.log(err);
				res.send(err);
			}
			else{
//				console.log('*');
				res.status(200).json(results);
			}
		});
	}
	else{
		// Tokenize column parameter
		var columnString = urlParam.select;
		var columns = columnString.split(",");
		con.query('select ?? from ??', [columns, urlParam.table], function(err, results, fields){
			if(err){
				console.log(err);
				res.send(err);
			}
			else{
//				console.log('!*');
				res.status(200).json(results);
			}
		});
	}
});
//GET REQUEST FOR USER SIGN IN
app.get('/signInUser-profile/:username.:password', function (req, res) {
		var data = req.params;
		var query = "SELECT * FROM PASSENGER WHERE PassengerID='"+data.username + "' AND Password='"+data.password+"'";
		con.query(query, function(err, results, fields){
			if(err){
				console.log(err);
				res.send(null);
			}
			else{
				res.status(200).json(results);
			}
		});
});


// Category, Cost, originCity, originState, originCountry, destCity, destState, destCountry
app.get('/allRental', function (req, res) {
	var query = "select transport.Category, transport.Cost, loc1.City as originCity, loc1.State as originState, loc1.Country as originCountry, loc2.City as destCity, loc2.State as destState, loc2.Country as destCountry from `car rental` transport join location loc1 on transport.Origin  = loc1.LocationID join location loc2 on transport.Destination= loc2.LocationID";
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			res.status(200).json(results);
		}
	});
});

// Category, Fare, originCity, originState, originCountry, destCity, destState, destCountry
app.get('/allCruise', function (req, res) {
	var query = "select transport.CruiseID, transport.Category, transport.Fare, loc1.City as originCity, loc1.State as originState, loc1.Country as originCountry, loc2.City as destCity, loc2.State as destState, loc2.Country as destCountry from cruise transport join location loc1 on transport.Origin  = loc1.LocationID join location loc2 on transport.Destination= loc2.LocationID";
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			res.status(200).json(results);
		}
	});
});

// Airline, Category, Fare, originCity, originState, originCountry, destCity, destState, destCountry
app.get('/allFlight', function (req, res) {
	var query = "select transport.FlightID, transport.Airline, transport.Category, transport.Fare, loc1.City as originCity, loc1.State as originState, loc1.Country as originCountry, loc2.City as destCity, loc2.State as destState, loc2.Country as destCountry from flight transport join location loc1 on transport.Origin  = loc1.LocationID join location loc2 on transport.Destination= loc2.LocationID";
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			res.status(200).json(results);
		}
	});
});



app.get('/profile/:userID', function (req, res) {
	var columns = "`Name`, Gender, Age, GroupID";
	var query = "select " + columns + " from passenger where PassengerID = '" + req.params.userID + "'";
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			res.status(200).json(results);
		}
	});
});

app.get('/group/:groupID', function (req, res) {
	var query = "select * from `Group` where GroupID = '" + req.params.groupID + "'";
//	query = mysql.format(query);
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			res.status(200).json(results);
		}
	});
});

app.get('/transport/:table.:transportID', function (req, res) {
	var attribute;
	if(req.params.table=="`car rental`")
		attribute = "RentalID";
	else if(req.params.table=="cruise")
		attribute = "CruiseID";
	else
		attribute = "FlightID";
	var query = "select * from "+req.params.table+" where "+attribute+" = '" + req.params.transportID + "'";
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			res.status(200).json(results);
		}
	});
});

app.get('/allReviews', function (req, res) {

	var query = "select * from reviews";

	con.query(query, function(err, results, fields){

		if(err){

			console.log(err);

			res.send(err);

		}

		else{

			res.status(200).json(results);

		}

	});

});

app.post('/bookTransport', function (req, res) {
	var attribute;
	var data = req.body;
	if(data.transport=="Car")
		attribute = "RentalID";
	else if(data.transport=="Cruise")
		attribute = "CruiseID";
	else{
		attribute = "FlightID";
	}

	var query = "update `group` set "+attribute+"="+data.transportID+",Size="+data.size+" where GroupID = " + data.groupID;
	con.query(query, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			query = "update passenger set GroupID = "+data.groupID+" where PassengerID = '"+data.passengerID+"'";
			con.query(query, function(err, results, fields){
				if(err){
					console.log(err);
					res.send(err);
				}
				else{
					res.status(200).json(results);
				}
			});
		}
	});
});

app.post('/sign-up', function (req, res) {
	var data = req.body;
	var name 	 = data.name;
	var	username = data.username,
		gender 	 = data.gender,
		age 	 = data.age,
		password = data.password;

	var sqlQeury = mysql.format("insert into passenger (name,passengerID,gender,age,password) values ('" + name + "','" + username + "','" + gender + "', " + age + ", '" + password + "')");
	con.query(sqlQeury, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			console.log('Successfully inserted');
			res.status(200).send('Successfully inserted');
//			res.redirect('/');
		}
	});
});

app.post('/postReview', function (req, res) {
	var data = req.body;
	var ID 	 = data.PassengerID,
		Rating = data.Rating,
		Review 	 = data.DetailedReview;

	var sqlQeury = mysql.format("insert into reviews (ReviewID,Rating,DetailedReview) values ('" + ID + "'," + Rating + ", '" + Review + "')");
	con.query(sqlQeury, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			console.log('Successfully inserted');
			res.status(200).send('Successfully inserted');
//			res.redirect('/');
		}
	});
});

app.post('/postPayment', function (req, res) {
	var data = req.body;
	var PaymentID 	 = data.GroupID,
		Name = data.Name,
		CardNumber 	 = data.CardNumber,
		CardExpiryDate = data.CardExpiryDate,
//		Currency = data.Currency,
		CCV = data.CCV;
	var sqlQeury = mysql.format("insert into payment (PaymentID, CardNumber, CardExpiryDate, CCV, Name) values (" + PaymentID + ",'" + CardNumber + "', '" + CardExpiryDate + "','"+CCV+"','"+Name+"')");
	con.query(sqlQeury, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			console.log('Successfully inserted');
			res.status(200).send('Successfully inserted');
//			res.redirect('/');
		}
	});
});

app.post('/postGroup', function (req, res) {

	var sqlQeury = mysql.format("insert into `Group` () values ()");
	con.query(sqlQeury, function(err, results, fields){
		if(err){
			console.log(err);
			res.send(err);
		}
		else{
			console.log('Successfully inserted');
			res.status(200).send('Successfully inserted');
//			res.redirect('/');
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
