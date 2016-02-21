(function(angular) {
  'use strict';

  function OverviewController(Profile, Emission) {
    var vm = this;
    vm.offset = 0;
    vm.limit = 50;
    vm.profiles = getProfiles();
    vm.changePage = changePage;
    vm.getSquareMeters = getSquareMeters;

    ////////

    function getProfiles() {
      return Profile.query({ offset: vm.offset, limit: vm.limit });
    }

    function changePage(page) {
      vm.offset = (page - 1) * vm.limit;
      vm.profiles = getProfiles();
    }

    function getSquareMeters(emission) {
      return Emission.convertToArea(emission);
    }
  }

  OverviewController.$inject = ['Profile', 'Emission'];

  angular.module('commutify.features.overview').controller('OverviewController', OverviewController);
}(angular));