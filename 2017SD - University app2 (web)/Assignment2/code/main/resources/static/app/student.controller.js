(function () {
    'use strict';

    angular
        .module('app')
        .controller('StudentController', StudentController);

    StudentController.$inject = ['$http'];

    function StudentController($http) {
        var vm = this;

        vm.returnList = [];
        vm.courseList = [];
        vm.adminList = [];
        vm.getAll = getAll;
        vm.getCourses = getCourses;
        vm.getAffordable = getAffordable;
        vm.createStudent = createStudent;
        vm.editStudent = editStudent;
        vm.deleteStudent = deleteStudent;
        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/student/all";
            var studentsPromise = $http.get(url);
            studentsPromise.then(function(response){
                vm.returnList = response.data;
            });
            var url2 = "/student/all/admins";
            var studentsPromise2 = $http.get(url2);
            studentsPromise2.then(function(response2){
                vm.adminList = response2.data;
            });
        }
        function getCourses(id){
        	var url = "/student/get/" + id;
            var studentsPromise = $http.get(url);
            studentsPromise.then(function(response){
                vm.courseList = response.data;
            });
        }

        function getAffordable(){
            var url = "/student/affordable/" + "popoct";
            var studentsPromise = $http.get(url);
            studentsPromise.then(function(response){
                vm.returnList = response.data;
            });
        }

        function editStudent(id, username, name){
        	var url = "/student/edit/" + id + "/" + username +"/" + name;
        	$http.post(url).then(function(response){
        		vm.returnList = response.data;
        	});
        }
        function createStudent(username, name){
        	var url = "/student/create/" + username +"/" + name;
        	$http.post(url).then(function(response){
        		vm.returnList = response.data;
        	});
        }
        
        function deleteStudent(id){
            var url = "/student/delete/" + id;
            $http.post(url).then(function(response){
                vm.returnList = response.data;
            });
        }
    }
})();
