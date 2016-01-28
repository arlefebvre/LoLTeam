(function(angular) {
  var UserFactory = function($resource) {
    return $resource('/users/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };

  UserFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("User", UserFactory);
}(angular));