(function(angular) {
  'use strict';

  angular.module('commutify.core', ['ngResource', 'ui.router', 'toastr']);
  angular.module('commutify.services', ['commutify.core']);
  angular.module('commutify.components', ['commutify.core']);
  angular.module('commutify', []);
}(angular));