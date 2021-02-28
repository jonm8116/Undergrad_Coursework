var app= angular.module('reviewapp', []);

app.controller('reviewController', ['$scope', '$http', function($scope,$http) {
  $scope.form = {};
  var user = localStorage.curuser;
  if(user != null){
    var item = document.getElementById('userprofile');
    var loginbtn = document.getElementById('sign-in-out-button');
    item.innerHTML = "Hi, "+ user;
    console.log("user " + user);
    loginbtn.innerHTML = "Sign Out"
  }
  $scope.submitReview = function() {
    console.log($scope.reviews);
    var config = {
      headers : {
        'Content-Type': 'application/json'
      }
    }
    if(localStorage.curuser != null){
      $scope.reviews.PassengerID = localStorage.curuser;
      var data = JSON.stringify($scope.reviews);
      console.log($scope.reviews);
      var postresult = $http.post('/postReview', data, config);
      postresult.then(function (data, config){
        console.log("Successful");
      });
    }else {
      console.log("Not logged in!");
    }
  }
}]);

app.controller('getreviewController', function($scope, $http){
  var user = localStorage.curuser;
  if(user != null){
    var item = document.getElementById('userprofile');
    var loginbtn = document.getElementById('sign-in-out-button');
    item.innerHTML = "Hi, "+ user;
    console.log("user " + user);
    loginbtn.innerHTML = "Sign Out"
  }
    $http.get('/allReviews').then(function(response){
    console.log(response.data);
      $scope.post = response.data;
    }).catch(function (response){

    }).finally(function(){
        console.log("process finally");
    });
  });
