var app = angular.module('carname', []);

app.controller('carController', function($scope, $http){
    $http.get('/allRental').then(function(response){
      $scope.post = response.data;
      var user = localStorage.curuser;
      if(user != null){
        var item = document.getElementById('userprofile');
        var loginbtn = document.getElementById('sign-in-out-button');
        item.innerHTML = "Hi, "+ user;
        loginbtn.innerHTML = "Sign Out"
      }
    }).catch(function (response){

    }).finally(function(){
        console.log("process finally");
    });
  });
