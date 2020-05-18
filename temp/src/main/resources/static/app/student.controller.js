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
        vm.grade = 0;
        vm.getCoursesOfStudent = getCoursesOfStudent;
        vm.getGrade = getGrade;
        vm.enrollInCourse = enrollInCourse;
        vm.unenrollFromCourse = unenrollFromCourse;
        init();

        function init(){
            getCoursesOfStudent();
        }

        function getCoursesOfStudent(){
            var url = "/student/coursesOfStudent";
            var studentsPromise = $http.get(url);
            studentsPromise.then(function(response){
            	console.log("\n Courses of student: \n");
            	console.log(response.data)
                vm.returnList = response.data;
            });
            var url2 = "/student/allCourses";
            var studentsPromise2 = $http.get(url2);
            studentsPromise2.then(function(response2){
            	console.log("\n Courses available: \n");
            	console.log(response2.data)
                vm.courseList = response2.data;
            });
        }
        
        function enrollInCourse(id){
        	 var url = "/student/enroll/" + id;
        	 var studentsPromise = $http.get(url);
             studentsPromise.then(function(response){
                 vm.returnList = response.data;
             });
        }
        function unenrollFromCourse(id){
        	var url = "/student/unenroll/" + id;
        	var studentsPromise = $http.get(url);
            studentsPromise.then(function(response){
                vm.returnList = response.data;
            });
        }
        function getGrade(id){
        	var url = "/student/grade/" + id;
        	var studentsPromise = $http.get(url);
            studentsPromise.then(function(response){
                vm.grade = response.data;
                return vm.grade;
            });
        }
       
    }
})();
