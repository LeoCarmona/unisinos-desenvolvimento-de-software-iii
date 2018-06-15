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
                console.log(data);
                // toastr.success('Compra realizada com sucesso!');
            },
            error: function (error) {
                console.log(error);
                // toastr.success('Compra realizada com sucesso!!');
            }
        });

    }
});