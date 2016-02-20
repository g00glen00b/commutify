(function(angular) {
  'use strict';

  function stateFactory($rootScope, $state, $stateParams) {
    return {
      initialize: initialize
    };

    function initialize() {
      $rootScope.$state = $state;
      $rootScope.$stateParams = $stateParams;
    }
  }

  stateFactory.$inject = ['$rootScope', '$state', '$stateParams'];

  angular
      .module('commutify.routing')
      .factory('state', stateFactory);
}(angular));