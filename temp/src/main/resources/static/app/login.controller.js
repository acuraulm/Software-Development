(function () {
    'use strict';

    angular
    .module('app', ['ngStorage'])
    .controller('LoginController', LoginController);

    LoginController.$inject = ['$scope', '$localStorage', '$http', '$window', '$location'];

    function LoginController($scope, $localStorage, $http, $window, $location) {
        var vm = this;

        $scope.data ={};
        $localStorage.data = {};

        $scope.$storage = $localStorage;
        
        $localStorage.loggedUser = {};
        $scope.loggedUser = {};

        vm.login = login;
        vm.loginStudent = loginStudent;
        init();

        function init(){
        	
        }
        
        function login(username, password){
        	console.log("\n Verifying login: " + username);

        	$http({
        		method: 'GET',
        		url: '/login/' + username,
        		}).then(function success(response) {

        			if(!response.data)
        				{
        				$window.alert("Username not found.")
        				}
        			else{
        				console.log(response.data);
	        			if(!response.data.password.localeCompare(password))
	        				{
		        				console.log("Login sent sucessfully.");
		                    	console.log(response.data);
		                    	
		                    	if(response.data.password)
		                        $localStorage.loggedUser = response.data;
		                        $scope.loggedUser = $localStorage.loggedUser;
		                               	
		                    	console.log($scope.loggedUser);
		                    	if(!response.data.userRole.localeCompare("AUTHOR"))
		                    		$window.location.href = '/author';
		                    	else
		                    		$window.location.href = '/reader';
	        				}
	        			else
		        			{
		        				$window.alert("Invalid password")
		        			}
        			}
                	
        		}, function error(response) {
        			console.log("Login error.");
        		});
                	
        }
        
        function loginStudent(username, password){
        	console.log("\n Verifying login: " + username);
        	
        	var url = '/login/' + username;
        	var data = '' + password;

        	$http.post(url, data).then(function (response) {

        	console.log("Login sent sucessfully.");
        	console.log(response.data);

        	}, function (response) {

        		console.log("Login error.");

        	});
                	
        }

          
    }
})();
