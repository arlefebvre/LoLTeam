(function(angular) {
  var AppController = function($scope, User) {
    User.query(function(response) {
      $scope.users = response ? response : [];
    });

    $scope.addUser = function(login,region) {
      new User({
        description: "vide",
        login: login
        region: region
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

  var RegionController = function($scope, $http) {
     $scope.getRegions = function() {
            $http.get('/regions').success(function(regionsTypes){
                $scope.regions = regionsTypes;
            })
        }
  }
  AppController.$inject = ['$scope', 'User'];
  angular.module("myApp.controllers").controller("AppController", AppController);
  RegionController.$inject = ['$scope', '$http'];
  angular.module("myApp.controllers").controller("RegionController", RegionController);
}(angular));