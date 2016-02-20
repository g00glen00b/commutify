(function(angular) {
  'use strict';

  function httpConfig($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }

  httpConfig.$inject = ['$httpProvider'];

  angular.module('commutify').config(httpConfig);
}(angular));