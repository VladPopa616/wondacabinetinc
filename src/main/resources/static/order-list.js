'use strict';

angular.module('orderList', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('orders', {
                parent: 'app',
                url: '/orders',
                template: '<order-list></order-list>'
            })
    }]);