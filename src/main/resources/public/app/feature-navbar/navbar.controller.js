(function(angular) {
  'use strict';

  function NavbarController(User) {
    var vm = this;
    vm.isAuthenticated = isAuthenticated;
    vm.getUser = getUser;

    initialize();

    ////////

    function isAuthenticated() {
      return User.isAuthenticated();
    }

    function getUser() {
      return User.getUser();
    }

    function initialize() {
      User.initialize();
    }
  }

  NavbarController.$inject = ['User'];

  angular.module('commutify.features.navbar').controller('NavbarController', NavbarController);
}(angular));