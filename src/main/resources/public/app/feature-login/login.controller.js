(function(angular) {
  'use strict';

  function LoginController(User, $state, toastr) {
    var vm = this;
    vm.login = login;

    ////////

    function login(user) {
      User.login(user).then(function() {
        $state.go('commutify');
      }, function() {
        toastr.error('Logging in failed', 'Error');
      });
    }
  }

  LoginController.$inject = ['User', '$state', 'toastr'];

  angular.module('commutify.features.login').controller('LoginController', LoginController);
}(angular));