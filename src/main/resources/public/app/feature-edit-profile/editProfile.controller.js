(function(angular) {
  'use strict';

  function EditProfileController(Profile, User, toastr) {
    var vm = this;
    vm.profile = Profile.getMine();
    vm.update = update;

    ////////

    function update(profile) {
      return Profile.update(profile).$promise.then(function(profile) {
        toastr.success('Your profile is now successfully updated');
        User.setUser(profile);
      }, function(err) {
        toastr.error(err.data.message || 'An unexpected error occured', 'Error');
      });
    }
  }

  EditProfileController.$inject = ['Profile', 'User', 'toastr'];

  angular.module('commutify.features.profile.edit').controller('EditProfileController', EditProfileController);
}(angular));