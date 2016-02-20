(function(angular) {
  'use strict';

  function routeConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('', '/');

    $stateProvider
      .state('overview', {
        url: '/',
        views: {
          'mainView@': {
            templateUrl: 'app/feature-overview/overview.tpl.html',
            controller: 'OverviewController',
            controllerAs: 'vm'
          }
        }
      })

      .state('login', {
        url: '/login',
        views: {
          'mainView@': {
            templateUrl: 'app/feature-login/login.tpl.html',
            controller: 'LoginController',
            controllerAs: 'vm'
          }
        }
      });
  }

  routeConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
  angular.module('commutify.routing').config(routeConfig);
}(angular));