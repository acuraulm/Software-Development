(function () {
    'use strict';

    angular
        .module('app')
        .controller('TestController', TestController);

    TestController.$inject = ['$http', '$location', '$window'];
    
    function TestController($http, $location, $window) {
        var vm = this;
       
        vm.testLog = testLog;
        vm.testSignUp = testSignUp;
        vm.testFindAllBooks = testFindAllBooks;
        vm.testPublishBook = testPublishBook;
        init();

        function init(){
        	
        	console.log("Blabla");
        	testLog();
        }
        
        function testLog(){
        	
        	console.log("Blabla");
        }
        function testSignUp(){
        	console.log("Signing up.");
        	
        	var url = '/test/signUp';
        	var data = {		"id":1,
					        	"name":"First Author",
					        	"userRole":"AUTHOR"
        	  			}

        	$http.post(url, data).then(function (response) {

        	console.log("Request sent sucessfully.");
        	console.log(response.data)

        	}, function (response) {

        		console.log("Test error.");

        	});
        }
    
        function testFindAllBooks(){
        	console.log("Retrieving books list.\n");

        	$http({
        		method: 'GET',
        		url: '/test/findAllBooks',
        		}).then(function success(response) {

                	console.log("Request sent sucessfully.\n");
                	console.log(response.data)

        		}, function error(response) {
        			console.log("Test error.");
        		});
                	
        }
        
        function testPublishBook(){
        	console.log("Publishing book.");
        	
        	var url = '/test/publishBook';
        	var data = {	"id":3,
					        "title":"THIRD Book",
					        "genre":"FICTION",
					        "authorId":1,
					        "authorName":"NA"
        	  			}

        	$http.post(url, data).then(function (response) {

        	console.log("Request sent sucessfully.");
        	console.log(response.data)

        	}, function (response) {

        		console.log("Test error.");

        	});
        }
        
          
    }
})();
