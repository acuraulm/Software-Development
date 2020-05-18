(function () {
    'use strict';

    angular
        .module('app')
        .controller('CourseController', CourseController);

    CourseController.$inject = ['$http'];

    function CourseController($http) {
        var vm = this;

        vm.returnList = [];
        vm.getAll = getAll;
        vm.students = [];
        vm.enroll = enroll;
        vm.getStudents = getStudents;
        vm.createCourse = createCourse;
        vm.deleteCourse = deleteCourse;
        init();

        function init(){
            getAll();
            getStudents(1);
        }

        function getAll(){
            var url = "/course/all";
            var coursesPromise = $http.get(url);
            coursesPromise.then(function(response){
                vm.returnList = response.data;
            });
        }

        function getStudents(id){
        	 var url = "/course/get/" + id;
             var coursesPromise = $http.get(url);
             coursesPromise.then(function(response){
                 vm.students = response.data;
             });
        }
        function createCourse(name){
        	var url = "/course/create/" + name;
        	$http.post(url).then(function(response){
        		vm.returnList = response.data;
        	});
        }
        function enroll(id1,id2){
        	//getAll();
        	var url = "/course/enroll/" + id1 + "/" + id2;
        	var coursesPromise = $http.get(url);
            coursesPromise.then(function(response){
                vm.students = response.data;
            });
            //getStudents(1);
        }
        
        function deleteCourse(id){
            var url = "/course/delete/" + id;
            $http.post(url).then(function(response){
                vm.returnList = response.data;
            });
        }
    }
})();
