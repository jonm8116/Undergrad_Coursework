var app = angular.module('appname', []);

app.controller('paymentController', ['$scope', '$http', '$location', function($scope, $http, $location){
    var user = localStorage.curuser;
    var urlparam = $location.absUrl().split('?')[1]
    // if(user != null){
    //   var item = document.getElementById('userprofile');
    //   var loginbtn = document.getElementById('sign-in-out-button');
    //   item.innerHTML = "Hi, "+ user;
    //   console.log("user " + user);
    //   loginbtn.innerHTML = "Sign Out"
    // }
    var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
    var secondsplit = urlparam.split("&");
    var travelID = secondsplit[0].split("=")[1];
    var transportType = secondsplit[1].split("=")[1];
    console.log("current url " + secondsplit[0]); //travelID
    console.log("second half " + secondsplit[1]); //type
    var groupData = {};
    groupData["size"] = 1;
    groupData["passengerID"] = localStorage.curuser;
    //console.log( groupData);
    if(transportType === "flight"){
      groupData["transportID"] = travelID;
      groupData["transport"] = "Flight";
    } else if(transportType === "cruise"){
      groupData["transportID"] = travelID;
      groupData["transport"] = "Cruise";
    } else if(transportType === "car"){
      groupData["transportID"] = travelID;
      groupData["transport"] = "Car";
    }

    $http.post('/postGroup', groupData, config).then(function (data, config){
      console.log("Successful");

      $http.get('/search/*.group').then(function(response){
        var newgroupID = response.data[response.data.length-1].GroupID;
        // console.log(needgroupdata);
        groupData["groupID"] = newgroupID;
        $http.post('/bookTransport', groupData, config).then(function (data, config){
            console.log("success nest insert");
        }, function(data,config){
            console.log("failure nest");
        });

      }, function(response){

      });
    }, function(data, config){
      console.log("failure");
    });
    $scope.checklogin = function(){
      if(localStorage.curuser != null){
          localStorage.curuser = null;
          location.href = "./signup.html";
      } else{
        location.href = "./signup.html";
      }
    };
    //MAKE FUNCTION FOR ON CLICK BOOK BTN
    $scope.bookTrip = function(){
        //ACCESS GROUPID groupData["groupID"]
        var payData = {};
        payData["GroupID"] = groupData["groupID"];
        payData["Name"] = $scope.passenger.name;
        payData["CardNumber"] = $scope.passenger.cardnum;
        payData["CardExpiryDate"] = $scope.passenger.expiredate;
        payData["CCV"] = $scope.passenger.ccv;

        $http.post('/postPayment', payData, config).then(function (data, config){
          console.log("Successful");

        });
    };

  }]);
