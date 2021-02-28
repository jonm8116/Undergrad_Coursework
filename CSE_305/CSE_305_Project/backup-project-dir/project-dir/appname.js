var app = angular.module('appname', []);

app.controller('controller', function($scope, $http){
    $http.get('http://127.0.0.1:8080/json/sample.json').then(function(response){
      $scope.post = response.data.post;

    }).catch(function (response){

    }).finally(function(){
        console.log("process finally")
    });
  });
