(function(angular) {
  'use strict';

  function NavbarController(User) {
    var vm = this;
    vm.isAuthenticated = isAuthenticated;
    vm.getUser = getUser;

    ////////

    function isAuthenticated() {
      return User.isAuthenticated();
    }

    function getUser() {
      return User.getUser();
    }
  }

  NavbarController.$inject = ['User'];

  angular.module('commutify.features.navbar').controller('NavbarController', NavbarController);
}(angular));