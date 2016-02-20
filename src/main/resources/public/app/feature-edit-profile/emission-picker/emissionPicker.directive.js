(function(angular) {
  'use strict';

  function emissionPicker() {
    return {
      restrict: 'E',
      require: '^ngModel',
      replace: true,
      scope: { },
      bindToController: true,
      controller: EmissionPickerController,
      controllerAs: 'vm',
      templateUrl: 'app/feature-edit-profile/emission-picker/emissionPicker.tpl.html',
      link: link
    };

    function link(scope, elem, attrs, ngModel) {
      scope.vm.ngModel = ngModel;

      ngModel.$formatters.unshift(format);

      ////////

      function format(value) {
        scope.vm.emission = value;
        return value;
      }
    }
  }

  function EmissionPickerController(Car) {
    var vm = this;
    vm.selectionType = 'direct';
    vm.setManufacturer = setManufacturer;
    vm.setModel = setModel;
    vm.setEmission = setEmission;

    getManufacturers();

    ////////

    function setManufacturer(manufacturer) {
      vm.model = null;
      getModels(manufacturer);
    }

    function setModel(model) {
      vm.type = null;
      getTypes(vm.manufacturer, model);
    }

    function setEmission(emission) {
      vm.emission = emission;
      vm.ngModel.$setViewValue(emission);
    }

    function getManufacturers() {
      Car.getManufacturers({ offset: 0, limit: 50 }).$promise.then(function(result) {
        vm.manufacturers = result.results;
      });
    }

    function getModels(manufacturer) {
      if (manufacturer != null) {
        Car.getModels({manufacturer: manufacturer, offset: 0, limit: 50}).$promise.then(function (result) {
          vm.models = result.results;
        });
      }
    }

    function getTypes(manufacturer, model) {
      if (manufacturer != null && model != null) {
        Car.getTypes({
          manufacturer: manufacturer,
          model: model,
          offset: 0,
          limit: 50
        }).$promise.then(function (result) {
          vm.types = result.results;
        });
      }
    }
  }

  EmissionPickerController.$inject = ['Car'];

  angular.module('commutify.features.profile.edit').directive('emissionPicker', emissionPicker);
}(angular));