(function(angular, btoa) {
  'use strict';

  function UserService($http) {
    var vm = this;
    var currentUser = null;
    vm.login = login;
    vm.isAuthenticated = isAuthenticated;
    vm.getUser = getUser;
    vm.setUser = setUser;
    vm.initialize = initialize;

    ////////

    function initialize() {
      return $http.get('./api/profile/my').then(function(result) {
        currentUser = result.data;
      });
    }

    function getUser() {
      return currentUser;
    }

    function setUser(user) {
      currentUser = user;
    }

    function isAuthenticated() {
      return currentUser != null;
    }

    function login(user) {
      return $http.get('./api/profile/my', { headers: basicAuthHeader(basicAuth(user)) }).then(function(result) {
        currentUser = result.data;
        return result;
      });
    }

    function basicAuth(user) {
      return btoa(user.email + ':' + user.password);
    }

    function basicAuthHeader(hash) {
      return { 'Authorization': 'Basic ' + hash };
    }
  }

  UserService.$inject = ['$http'];

  angular.module('commutify.services').service('User', UserService);
}(angular, btoa));