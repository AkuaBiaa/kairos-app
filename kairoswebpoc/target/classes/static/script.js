//todo: Use Github as datasource for storing points

(function(){
    var app = angular.module("mainModule",[]);

    var MainController = function ($scope,$http){
        var tenPoints = 10;
        $scope.totalPoints = 0;
        $scope.title = "Kairos";
        $scope.pointPalceholder = "Enter points...";
        $scope.points = "";
        $scope.submitText = "Post";
        $scope.viewPoints = "View Points";

        $scope.runTotalPoints = function (){
            $scope.totalPoints = 0;
            if($scope.points.includes(",")){
                $scope.totalPoints += ($scope.points.split(",").length-1)*tenPoints;
            }
            console.info("Total points = "+$scope.totalPoints);
            //TODO: Add Later
            // logPointsDownload($scope.totalPoints,scope.pointPalceholder);
            return $scope.totalPoints;
        };

        $scope.fetchPoints = function () {
            $http.get("http://localhost:8080/pointService/fetchPoints")
                .then(onReturnedUserData,onError);
        };

        var onReturnedUserData = function(response){
            var flagGetRequest = false;
            if (response.data.points !== undefined){
                //Get request handler
                if(parseInt(response.data.points) >= 0){
                    $scope.totalPoints = parseInt(response.data.points);
                    flagGetRequest = true;
                }
            }
            if (flagGetRequest){
                $scope.points = "Current Points: +" + $scope.totalPoints+"!";
            }
            else {
                $scope.points = "Slaying it! +" + $scope.totalPoints+"!";
            }
            console.log("Returned code data:"+response.data);
            console.log("Response Successful! Code:");
        };

        var onError = function(reason){
            alert("Error: Unable to fetch user details due to:"+reason.data) ;
        };

        $scope.postPoints = function(){
            var pointDetailObject = {
                point:"",
                task:"",
                time: ""
            };
            if (isPointValid($scope.points)){
                var postTotalPoints = $scope.runTotalPoints();
                console.log("Posting points "+postTotalPoints+" to kairos rest kairos.poc.web.kairoswebpoc.service");

                //Arrange point data before posting to endpoint resource
                pointDetailObject.point = postTotalPoints;
                pointDetailObject.task = $scope.points;
                pointDetailObject.point = new Date().toString();

                $http.put("http://localhost:8080/pointService/savePoints/".concat(postTotalPoints)).then(onReturnedUserData,onError);
                //todo: revert after POC testing pojo sends points
                //$http.put("http://localhost:8080/pointService/savePoints/".concat(postTotalPoints)).then(onReturnedUserData,onError);
            }
            else {
                alert("Invalid points for submission. Please enter CSV format CC,BB,DD,");
                $scope.points = "";
            }
        };
        var isPointValid = function (point) {
            if (point.includes(":") || !point.includes(",")){
                return false;
            }
            else {
                return true;
            }
        }
    };


    app.controller("MainController",["$scope","$http",MainController]); //dependencies in brackets

    var textFile = null;
    var makeTextFile = function (text) {
        var data = new Blob([text], {type: 'text/plain'});

        // If we are replacing a previously generated file we need to
        // manually revoke the object URL to avoid memory leaks.
        if (textFile !== null) {
            window.URL.revokeObjectURL(textFile);
        }

        textFile = window.URL.createObjectURL(data);

        // returns a URL you can use as a href
        return textFile;
    };

//Downloads or logs the file
//TODO: can use this to call the fetchPoints method and save the value in a file here - el;iminates need for JavaScript
    var logPointsDownload = function(points,tasks){
        try{
            console.info("downloading points "+points+"+...");
            var link = document.createElement('a');
            var currentDateTime = new Date()+"";
            currentDateTime = currentDateTime.replace(/\s+/g, '');
            currentDateTime = currentDateTime.substring(0,currentDateTime.indexOf(":"))+"-T-"+new Date().getTime();
            var fileName = "KAI-LOGS-"+currentDateTime+".txt";

            link.setAttribute('download', fileName);
            link.href = makeTextFile("Points:"+points+" Tasks:"+tasks+" Time:"+ new Date());
            document.body.appendChild(link);

            // wait for the link to be added to the document
            window.requestAnimationFrame(function () {
                var event = new MouseEvent('click');
                link.dispatchEvent(event);
                document.body.removeChild(link);
            });
        }
        catch(err){
            console.error("ERROR: "+err);
        }
    };

    /**
     **/
//note: relfection: Reviewed controllers, http, and now modules to store congtrollers as follows:
//note: 1) Create the module name, 2) Register its controllers 3) specify the module.name in html w/ app.js
}());