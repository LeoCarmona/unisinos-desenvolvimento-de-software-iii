let homeModule = angular.module('homeModule', []);

homeModule.controller('homeController', function ($scope, $http) {
    let init = function () {
        $scope.getProducts();
    };

    $scope.getProducts = function () {
        $http({
            method: 'GET',
            url: API_URL + '/products'
        }).then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {

        });
    }

    $scope.add = function (id, quantity) {
        let _session = session.data();

        $.ajax({
            url: API_URL + "/basket/product/",
            method: 'POST',
            data: JSON.stringify({
                customerId: _session.customer.id,
                productId: id,
                quantity: quantity
            }),
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
                xhr.setRequestHeader('Content-Type', 'application/json');
                xhr.setRequestHeader('Authorization', _session.token);
            },
            success: function (data) {
                toastr.success('Produto adicionado com sucesso!');
            },
            error: function (error) {
                toastr.error('Ocorreu um erro ao adicionar o produto!');
            }
        });

    }

    $scope.isLogged = function() {
        return session.data().token != null;
    }

    init();
});