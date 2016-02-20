(function(angular) {
  'use strict';

  function EditProfileController(Profile, toastr) {
    var vm = this;
    vm.profile = Profile.get();
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

  EditProfileController.$inject = ['Profile', 'toastr'];

  angular.module('commutify.features.profile.edit').controller('EditProfileController', EditProfileController);
}(angular));