var app= angular.module('profileApp', []);

app.controller('profileController', ['$scope', '$http', function($scope,$http) {
    $scope.profile = {};
	$scope.group = {};
	$scope.transport = {};
	$scope.location = {};
	var profileID = localStorage.curuser;
	// profile query
	$http.get("/profile/"+profileID).then(function(response){
		$scope.profile = response.data[0];
		// group query
		if(response.data[0].GroupID)
			$http.get("/group/"+response.data[0].GroupID).then(function(response){
				$scope.group =response.data[0];
				var table;
				var transportID = null;
				if(response.data[0].FlightID){
					table = "flight";
					transportID = response.data[0].FlightID;
				}
				else if(response.data[0].CruiseID){
					table = "cruise";
					transportID = response.data[0].CruiseID;
				}
				else if(response.data[0].CarID){
					table = "`car rental`";
					transportID = response.data[0].CarID;
				}
				// transport query
				if(transportID)
					$http.get("/transport/"+table+"."+transportID).then(function(response){
						$scope.transport =response.data[0];
						$http.get("/search/*.location").then(function(response){
							$scope.location =response.data;
							for (var i = 0; i < $scope.location.length; i++) {
								if($scope.location[i].LocationID == $scope.transport.Origin)
									$scope.Origin = $scope.location[i];
								if($scope.location[i].LocationID == $scope.transport.Destination)
									$scope.Destination = $scope.location[i];
								
							}
						}).catch(function (response){

						}).finally(function(){

						});
					}).catch(function (response){

					}).finally(function(){

					});
			}).catch(function (response){

			}).finally(function(){

			});
    }).catch(function (response){

    }).finally(function(){
        
    });
}]);
