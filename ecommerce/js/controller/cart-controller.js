let cartModule = angular.module('cartModule', []);

cartModule.controller('cartController', function ($scope, $http) {
    $scope.finish = function () {
        let _session = session.data();

        $.ajax({
            url: API_URL + "/basket/finish/",
            method: 'POST',
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', _session.token);
            },
            success: function (data) {
                window.open("./shopping.html","_self")
            },
            error: function (error) {
                toastr.success('Ocorreu um erro ao realizar a compra!');
            }
        });

    }

    $scope.isLogged = function() {
        return session.data().token != null;
    }
    
});