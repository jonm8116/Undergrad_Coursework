var app = angular.module('appname', []);

app.controller('controller', function($scope, $http){
    $http.get('/allFlight').then(function(response){
      $scope.post = response.data;

    }).catch(function (response){

    }).finally(function(){
        console.log("process finally");
    });
    var user = localStorage.curuser;
    if(user != null){
      var item = document.getElementById('userprofile');
      var loginbtn = document.getElementById('sign-in-out-button');
      item.innerHTML = "Hi, "+ user;
      console.log("user " + user);
      loginbtn.innerHTML = "Sign Out"
    }

    $scope.checklogin = function(){
      if(localStorage.curuser != null){
          localStorage.clear();
          location.href = "./signup.html";
      } else{
        location.href = "./signup.html";
      }
    };
    //MAKE FUNCTION FOR ON CLICK BOOK BTN
    $scope.bookTrip = function(){
      
    };

  });
