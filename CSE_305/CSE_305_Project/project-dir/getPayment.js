var app= angular.module('paymentapp', []);

app.controller('paymentController', ['$scope', '$http', function($scope,$http) {
  $scope.form = {};
  $scope.submitPayment = function() {
    console.log($scope.reviews);
    var config = {
      headers : {
        'Content-Type': 'application/json'
      }
    };
    if(localStorage.curuser != null){
      $scope.reviews.PassengerID = localStorage.curuser;
      var data = JSON.stringify($scope.reviews);
      console.log($scope.reviews);
      var postresult = $http.post('/postPayment', data, config);
      postresult.then(function (data, config){
        console.log("Successful");
      });
    }else {
      console.log("Not logged in!");
    }
  };
}]);
