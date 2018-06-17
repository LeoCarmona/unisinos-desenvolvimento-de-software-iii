let shoppingModule = angular.module('shoppingModule', []);

shoppingModule.controller('shoppingController', function ($scope, $http) {
    let init = function () {
        $scope.getProducts();
    };

    $scope.getProducts = function () {
        let _session = session.data();

        $http({
            method: 'GET',
            url: API_URL + '/shopping',
            headers: {
                Authorization : _session.token
            }
        }).then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {

        });
    }

    $scope.isLogged = function() {
        return session.data().token != null;
    }

    init();
});