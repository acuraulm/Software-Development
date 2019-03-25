(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdministratorController', AdministratorController);

    AdministratorController.$inject = ['$http'];

    function AdministratorController($http) {
        var vm = this;

        vm.returnList = [];
        vm.getAll = getAll;
        vm.getAffordable = getAffordable;
        vm.createAdministrator = createAdministrator;
        vm.editAdministrator = editAdministrator;
        vm.deleteAdministrator = deleteAdministrator;
        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/administrator/all";
            var administratorsPromise = $http.get(url);
            administratorsPromise.then(function(response){
                vm.returnList = response.data;
            });
        }

        function getAffordable(){
            var url = "/administrator/affordable/" + "popoct";
            var administratorsPromise = $http.get(url);
            administratorsPromise.then(function(response){
                vm.returnList = response.data;
            });
        }

        function editAdministrator(id, username, name){
        	var url = "/administrator/edit/" + id + "/" + username +"/" + name;
        	$http.post(url).then(function(response){
        		vm.returnList = response.data;
        	});
        }
        function createAdministrator(username, name){
        	var url = "/administrator/create/" + username +"/" + name;
        	$http.post(url).then(function(response){
        		vm.returnList = response.data;
        	});
        }
        
        function deleteAdministrator(id){
            var url = "/administrator/delete/" + id;
            $http.post(url).then(function(response){
                vm.returnList = response.data;
            });
        }
    }
})();
