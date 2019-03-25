(function () {
    'use strict';

    angular
        .module('app')
        .controller('IndexController', IndexController);

    IndexController.$inject = ['$http', '$location', '$window'];
    
    function IndexController($http, $location, $window) {
        var vm = this;

        vm.returnList = [];
        vm.groupasList = [];
        vm.studentsList = [];
        vm.getAll = getAll;
        vm.loginTeacher = loginTeacher;
        vm.loginStudent = loginStudent;
        vm.getGroupas = getGroupas;
       	vm.getStudents = getStudents;
        init();

        function init(){
            getAll();
            getGroupas(4);
            getStudents(2);
        }
        
        function getAll(){
        
           var url = "/index/all";
            var indexsPromise = $http.get(url);
            indexsPromise.then(function(response){
            	console.log("\ngetAll()\n");
            	console.log(response.data);
                vm.returnList = response.data; 
            });
        }
        
        function loginTeacher(username){
        	console.log("\n Verifying login: " + username);
       	 var url = "/index/teacher/" + username;
       	  var indexsPromise = $http.get(url);
             indexsPromise.then(function(response){
           	 console.log("\nRecieved answer\n");
           	 console.log(response.data);
           	 if(response.data == true)
           		 $window.location.href = '/teacher';
           	 else{
           			
           			console.log("Invalid username")
           	 }
            });
                	
        }
        function loginStudent(username){
        	console.log("\n Verifying login: " + username);
       	 var url = "/index/student/" + username;
       	  var indexsPromise = $http.get(url);
             indexsPromise.then(function(response){
            	 console.log("\nRecieved answer\n");
           	 console.log(response.data);
           	 if(response.data == true)
           		 $window.location.href = '/student';
           	 else{
        	
        			console.log("Invalid username")
        	 }
            });
                	
        }
        /*
        function getGroupas(id){
        	console.log("\nID FROM getGroupas(): " + id);
        	 var url = "/index/" + id;
             $http.post(url).then(function(response){
            	 console.log("\ngetGroupas()\n");
            	 console.log(response.data);
                 vm.groupasList = response.data;
             });
        }
        function getStudents(id){
        	console.log("\nID FROM getStudents(): " + id);
        	 var url = "/index/group/" + id;
             $http.post(url).then(function(response){
            	 console.log("\ngetStudents()\n");
            	 console.log(response.data);
                 vm.studentsList = response.data;
             });
          }
          */
        function getGroupas(id){
        	console.log("\nID FROM getGroupas(): " + id);
        	 var url = "/index/" + id;
        	  var indexsPromise = $http.get(url);
              indexsPromise.then(function(response){
            	 console.log("\ngetGroupas()\n");
            	 console.log(response.data);
                 vm.groupasList = response.data;
                 vm.studentsList = [];
             });
        }
        function getStudents(id){
        	console.log("\nID FROM getStudents(): " + id);
        	 var url = "/index/group/" + id;
        	  var indexsPromise = $http.get(url);
              indexsPromise.then(function(response){
            	 console.log("\ngetStudents()\n");
            	 console.log(response.data);
                 vm.studentsList = response.data;
             });
          }
          
    }
})();
