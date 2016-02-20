(function(angular) {
  'use strict';

  function profileFactory($resource) {
    return $resource('./api/profile/:id', { id: '@id' }, {
      register: {
        method: 'POST',
        url: './api/profile/register'
      },
      login: {
        method: 'POST',
        url: './j_spring_security_check'
      }
    });
  }

  profileFactory.$inject = ['$resource'];

  angular.module('commutify.services').factory('Profile', profileFactory);
}(angular));