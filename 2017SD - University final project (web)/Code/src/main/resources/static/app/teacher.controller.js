(function () {
    'use strict';

    angular
        .module('app')
        .controller('TeacherController', TeacherController);

    TeacherController.$inject = ['$http'];

    function TeacherController($http) {
        var vm = this;

        vm.returnList = [];
        vm.studentsList = [];
        vm.getAll = getAll;
        vm.getAllStudents = getAllStudents;
        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/teacher/all";
            var teachersPromise = $http.get(url);
            teachersPromise.then(function(response){
            	console.log("\n getAll: \n");
            	console.log(response.data)
                vm.returnList = response.data;
            });
        }

        function getAllStudents(courseid){
            var url = "/teacher/all/" + courseid;
            var teachersPromise = $http.get(url);
            teachersPromise.then(function(response){
            	console.log("\n getAllStudents: \n");
            	console.log(response.data)
                vm.studentsList = response.data;
            });
        }

    }
})();
