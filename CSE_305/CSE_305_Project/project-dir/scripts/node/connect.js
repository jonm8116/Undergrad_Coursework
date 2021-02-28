var mysql;
var con;

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  database: "mydb"
});

con.connect(function(err) {
 if (err) throw err;
 console.log("Connected!");
});

//var select = "select * from location";
//var insert = "insert into location (LocationID, City, State, Country) values (11111, 'City of No Where', 'My State', 'My country')"

// Connection can be established implicitly by invoking a query
var SQL_Query = function (sql, callback){
	con.query(sql, function(err, results, fields){
		if (err)
			callback(err, null, null);
		else
			callback(null, results, fields);
	});
}

//SQL_Query(insert, function(err, results, fields){
//	if (err)
//		console.log("ERROR: ",err);
//	else {
//		for(field in fields){
//			console.log(fields[field].name);
//		}
//		console.log(results);
//	}
//});

//SQL_Query(select, function(err, results, fields){
//	if (err)
//		console.log("ERROR: ",err);
//	else {
//		for(field in fields){
//			console.log(fields[field].name);
//		}
//		console.log(results);
//	}
//});

module.exports = {
	Query: SQL_Query
}
