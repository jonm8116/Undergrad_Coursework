var app= angular.module('appname', []); //origin: submitapp

app.controller('submitController', ['$scope', '$http', function($scope,$http) {
  console.log("The app saved value is " + localStorage.curuser);
  var user = localStorage.curuser;
  if(user != null){
    var item = document.getElementById('userprofile');
    var loginbtn = document.getElementById('sign-in-out-button');
    item.innerHTML = user;
    console.log("user " + user);
    // loginbtn.innerHTML = "Sign Out"
  }
  $scope.form = {};
  $scope.submitUser = function() {
    console.log($scope.passenger);
    var data = JSON.stringify($scope.passenger);
    console.log(data);
    var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
    $http.post('/sign-up', data, config).then(function (data, config){
      console.log("Successful");
    }, function(data, config){
      console.log("failure");
    });
  };

  $scope.checklogin = function(){
    if(localStorage.curuser != null){
        localStorage.clear();
        location.href = "./signup.html";
    } else{
      location.href = "./signup.html";
    }
  };
}]);
