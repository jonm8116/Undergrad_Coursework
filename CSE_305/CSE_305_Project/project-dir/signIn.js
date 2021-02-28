var app= angular.module('appname', []); //origin: signapp

app.controller('signInController', ['$scope','$http', function($scope,$http) {
  $scope.form = {};
  $scope.signIn = function() {
    var data = $scope.passenger;
    var config = {
      headers : {
        'Content-Type': 'application/json'
      }
    }
    console.log(data);
    $http.get("/signInUser-profile/"+data.username+"."+data.password).then(function(response){
      var result = response.data[0];
      console.log(result);
      var curuser = result.PassengerID;
      if(result != null){
        localStorage.setItem("curuser", curuser);
        console.log("curuser in if " + curuser);
        console.log("localstorage in if " + localStorage.curuser);
      } else{
        console.log("Only null value");
      }
    });
    // console.log("curuser in if " + curuser);
    console.log("localstorage outside if " + localStorage.curuser);
    // console.log("The app saved value is " + $scope.curuser);
    location.href = "index.html";
  };
}]);
