// This is the version used for the HTML home-ajs.html with AngularJS
// This is the trending technology
var cs480App = angular.module('cs480App', []);

cs480App.controller('GpsCtrl', function ($scope, $http) {

  $scope.listAllGpsProducts = function() {
	  $http.get("list/all/gps")
	  	.success(function(data){
	  		$scope.products = data;
	  	});
  }

  $scope.listAllGpsProducts();

});