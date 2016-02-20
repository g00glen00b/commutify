(function(angular) {
  'use strict';

  function routeConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('', '/commutify');

    $stateProvider
      .state('commutify', {
        url: '/commutify',
        views: {
          'mainView@': {
            templateUrl: 'app/feature-overview/overview.tpl.html',
            controller: 'OverviewController',
            controllerAs: 'vm'
          },
          'navView@': {
            templateUrl: 'app/feature-navbar/navbar.tpl.html',
            controller: 'NavbarController',
            controllerAs: 'vm'
          }
        }
      })

      .state('commutify.login', {
        url: '/login',
        views: {
          'mainView@': {
            templateUrl: 'app/feature-login/login.tpl.html',
            controller: 'LoginController',
            controllerAs: 'vm'
          }
        }
      })

        .state('commutify.register', {
          url: '/register',
          views: {
            'mainView@': {
              templateUrl: 'app/feature-login/feature-register/register.tpl.html',
              controller: 'RegisterController',
              controllerAs: 'vm'
            }
          }
        });
  }

  routeConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
  angular.module('commutify.routing').config(routeConfig);
}(angular));