'use strict';

// Declare app level module which depends on filters, and services
var @aplication.name@ = angular.module('@aplication.name@', ['services'])
    .config(function ($compileProvider){
        $compileProvider.urlSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|tel):/);
    })
    .config(['$routeProvider', function($routeProvider) {

        $routeProvider.when('/', {templateUrl: 'view/auth/login.html', controller: 'UserCtrl'});
        $routeProvider.when('/home', {templateUrl: 'view/home.html', controller: 'UserCtrl'});
        $routeProvider.otherwise({redirectTo: '/'});
    }]);