'use strict';
function @controller.name@(DAO,$scope,$rootScope){
    if(!$rootScope.appConfig){
        window.location.href="/auth/"
    }
    $scope.filter=""
    $scope.@class.instance@s={}
    $scope.@class.instance@=[]
    $scope.flags.save  = false;
    $scope.errors={showErrors: false, showServerError:false}

    $scope.getAll = function(){
        //get all
        DAO.query({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller:'@class.instance@', action:'getAll'},
            function(result){
                $scope.@class.instance@s=result;
            },
            function(error){
            });
    };

    $scope.new@class.instance@ =function(){
        $scope.@class.instance@=[]
        window.location.href="/@class.instance@/create"
    }

    $scope.manualSave = function(){
        $scope.flags.save  = false;
        if($scope.@class.instance@.id==''){
            $scope.save();
        }else{
            $scope.update();
        }
    }

    $scope.save = function(){
        DAO.save({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, @class.instance@:$scope.@class.instance@, controller:'@class.instance@', action:'newItem'},
            function(result){
                $scope.@class.instance@.id=result.id;
                $scope.flags.save = true;
            },
            function(error){
                $scope.flags.save = false;
                $scope.errors.showErrors = true;
                $scope.errors.showServerError = true;
            });
    }

    $scope.update=function(){
        DAO.update({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, @class.instance@:$scope.@class.instance@, controller:'@class.instance@', action:'update'},
            function(result){
                $scope.flags.save = true;
            },
            function(error){
                $scope.flags.save = false;
                $scope.errors.showErrors = true;
                $scope.errors.showServerError = true;
            });
    }

    $scope.edit=function(@class.instance@){
        DAO.get({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, id:@class.instance@.id, controller:'@class.instance@', action:'getById'},
            function(result){
                $scope.@class.instance@=result;
                window.location.href="/@class.instance@/edit"
            },
            function(error){
                $scope.errors.showErrors = true;
                $scope.errors.showServerError = true;
            });
    }

    $scope.confirmDelete@class.name@ =function(){
        DAO.delete({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, id:$scope.@class.instance@.id, controller:'@class.instance@', action:'delete'},
            function(result){
                if(result.response=="@class.name@_deleted"){
                    window.location.href="/@class.instance@/list"
                }
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    }
}

