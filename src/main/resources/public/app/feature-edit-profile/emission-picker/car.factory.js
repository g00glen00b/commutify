(function(angular) {
  'use strict';

  function Car($resource) {
    return $resource('./api/cars', {}, {
      getManufacturers: {
        url: './api/cars/manufacturers',
        method: 'GET'
      },
      getModels: {
        url: './api/cars/models',
        method: 'GET'
      },
      getTypes: {
        url: './api/cars/types',
        method: 'GET'
      }
    });
  }

  Car.$inject = ['$resource'];

  angular.module('commutify.features.profile.edit').factory('Car', Car);
}(angular));