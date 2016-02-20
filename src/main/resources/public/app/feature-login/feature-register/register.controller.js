(function(angular) {
  'use strict';

  function RegisterController(Profile, toastr) {
    var vm = this;
    vm.signup = signup;

    ////////

    function signup(user) {
      Profile.register(user, function() {
        toastr.success('You are now successfully registered');
      }, function(err) {
        toastr.error(err.data.message || 'An unexpected error occured', 'Error');
      });
    }
  }

  RegisterController.$inject = ['Profile', 'toastr'];

  angular.module('commutify.features.login').controller('RegisterController', RegisterController);
}(angular));