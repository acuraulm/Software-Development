(function () {
    'use strict';

    angular
    	.module('app', ['ngStorage'])
        .controller('AuthorController', AuthorController);

    AuthorController.$inject = ['$scope', '$localStorage', '$http', '$window', '$location'];

    function AuthorController($scope, $localStorage, $http, $window, $location) {
        var vm = this;
        
        vm.loggedAuthor = {};
        
        $scope.data = {};
        $localStorage.data = {};
        
        $scope.$storage = $localStorage;
        $scope.loggedUser = $localStorage.loggedUser;
        
        vm.loggedAuthor = $scope.loggedUser;

        console.log($scope.loggedUser);
        
        if(!$scope.loggedUser.id){
        	$window.location.href = "/";
        }
        
        vm.getLoggedAuthor = getLoggedAuthor;
        vm.writtenBooks = [];
        vm.getWrittenBooks = getWrittenBooks;
        
        
        init();

        function init(){
            getWrittenBooks();
        }
        
        function getLoggedAuthor(){
        	return $cookies.get('name');
        }
        
        function getWrittenBooks(){
        	var url = "/author/writtenBooks/" + $scope.loggedUser.id;
            var authorsPromise = $http.get(url);
            authorsPromise.then(function(response){
            	console.log("\n WrittenBooks of author: \n");
            	console.log(response.data)
                vm.writtenBooks = response.data;
            });
        }
        
      
    }
})();
