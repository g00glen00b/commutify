(function(angular) {
  'use strict';

  angular.module('commutify.core', ['ngResource', 'ui.router', 'toastr']);
  angular.module('commutify.services', ['commutify.core']);
  angular.module('commutify.components', ['commutify.core']);
  angular.module('commutify.routing', ['commutify.core']);
  angular.module('commutify.features.overview', ['commutify.components', 'commutify.services']);
  angular.module('commutify.features.login', ['commutify.components', 'commutify.services']);
  angular.module('commutify', [
    'commutify.routing',
    'commutify.features.overview',
    'commutify.features.login'
  ]);
}(angular));