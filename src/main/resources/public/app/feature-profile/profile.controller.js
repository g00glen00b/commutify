(function(angular) {
  'use strict';

  function ProfileController(Profile, $stateParams, toastr) {
    var vm = this;
    vm.getDayContent = getDayContent;
    vm.getEntry = getEntry;
    vm.updateEntry = updateEntry;
    vm.deleteEntry = deleteEntry;
    vm.getSquareMeters = getSquareMeters;
    vm.types = [{
      id: 1,
      name: 'Tram/Subway',
      ecological: true,
      icon: 'fa-subway'
    }, {
      id: 2,
      name: 'Bus',
      ecological: true,
      icon: 'fa-bus'
    }, {
      id: 3,
      name: 'Carpool',
      ecological: true,
      icon: 'fa-car'
    }, {
      id: 4,
      name: 'Bicicle',
      ecological: true,
      icon: 'fa-bicycle'
    }, {
      id: 5,
      name: 'Train',
      ecological: true,
      icon: 'fa-train'
    }];

    initialize();

    ////////

    function getDayContent(day) {
      console.log(day);
      return '<p></p>';
    }

    function getProfile(id) {
      return Profile.get({ id: id });
    }

    function initialize() {
      vm.profile = getProfile($stateParams.id);
    }

    function getEntry(day) {
      var mm = moment(day);
      vm.entry = _.find(vm.profile.entries, function(entry) {
        return mm.isSame(entry.date, 'day');
      });
      if (vm.entry == null) {
        vm.entry = { date: day, distance: vm.profile.averageKmDay };
      } else {
        vm.entry.type = _.find(vm.types, function(type) {
          return type.id === vm.entry.type.id;
        });
      }
    }

    function updateEntry(entry) {
      Profile.updateEntry(entry, function(profile) {
        vm.profile = profile;
        toastr.success('Your calendar was successfully updated');
      }, function(err) {
        toastr.error(err.data.message || 'An unexpected error occured', 'Error');
      });
    }

    function deleteEntry(entry) {
      Profile.deleteEntry(entry, function(profile) {
        vm.profile = profile;
        toastr.success('Your calendar was successfully updated');
      }, function(err) {
        toastr.error(err.data.message || 'An unexpected error occured', 'Error');
      });
    }

    function getSquareMeters(emission) {
      var tons =  emission / 1000000;
      var c = 0.28; // According to http://www.milieurapport.be/Upload/main/MIRA10-01_Ecologische_voetafdruk_Vlaanderen_TW.pdf
      var hToM = 10000;
      return tons * c * hToM;
    }
  }

  ProfileController.$inject = ['Profile', '$stateParams', 'toastr'];

  angular.module('commutify.features.profile').controller('ProfileController', ProfileController);
}(angular));