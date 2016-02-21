(function(angular) {
  'use strict';

  function Emission() {
    var vm = this;
    vm.convertToArea = convertToArea;

    ////////

    function convertToArea(emission) {
      var c = 0.28; // According to http://www.milieurapport.be/Upload/main/MIRA10-01_Ecologische_voetafdruk_Vlaanderen_TW.pdf
      return areaToSquareMeters(emissionAsTons(emission) * c);
    }

    function emissionAsTons(emission) {
      return emission / 1000000;
    }

    function areaToSquareMeters(gha) {
      return gha * 10000;
    }
  }

  angular.module('commutify.services').service('Emission', Emission);
}(angular));