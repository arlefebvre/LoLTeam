(function(angular) {
  var AppController = function($scope, User) {
    User.query(function(response) {
      $scope.users = response ? response : [];
    });

    $scope.addUser = function(login) {
      new User({
        description: "vide",
        login: login
      }).$save(function(user) {
        $scope.users.push(user);
      });
      $scope.newLogin = "";
    };

    $scope.updateUser = function(user) {
      user.$update();
    };

    $scope.deleteUser = function(user) {
      user.$remove(function() {
        $scope.users.splice($scope.users.indexOf(user), 1);
      });
    };
  };

  AppController.$inject = ['$scope', 'User'];
  angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));