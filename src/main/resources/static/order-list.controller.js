'use strict';

angular.module('ordersList')
    .controller('OrderListController', ['$http', function ($http) {
        var self = this;

        $http.get('/orders').then(function (resp) {
            self.owners = resp.data;
            console.log(resp)
        });
    }]);