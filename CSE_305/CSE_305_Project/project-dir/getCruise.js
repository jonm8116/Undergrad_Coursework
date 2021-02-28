var app = angular.module('cruisename', []);

app.controller('cruiseController', function($scope, $http){
    $http.get('/allCruise').then(function(response){
      $scope.post = response.data;
      var user = localStorage.curuser;
      if(user != null){
        var item = document.getElementById('userprofile');
        var loginbtn = document.getElementById('sign-in-out-button');
        item.innerHTML = "Hi, "+ user;
        console.log("user " + user);
        loginbtn.innerHTML = "Sign Out"
      }
    }).catch(function (response){

    }).finally(function(){
        console.log("process finally");
    });
  });
