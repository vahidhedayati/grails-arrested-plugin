'use strict';
function UserCtrl($scope, $rootScope, DAO){

    $scope.changePassword = {currentPassword:'', newPassword:'', passwordConfirm:''};

    if(!$rootScope.appConfig){
        $rootScope.appConfig = {serverHost:'localhost', appName:'@app.name@', token:''};
        $rootScope.user = {username:'', passwordHash:''};
        $rootScope.errors = {forgotPassword:false, showErrors:false, showMessage:false, showFunctionError:false, showServerError:false, showPasswordError:false};
    }

    function initializeVariables(){
        $rootScope.appConfig = {serverHost:'localhost', appName:'@app.name@', token:''};
        $rootScope.user = {username:'', passwordHash:''};
        $rootScope.errors = {forgotPassword:false, showErrors:false, showMessage:false, showFunctionError:false, showServerError:false, showPasswordError:false};
        $scope.changePassword = {currentPassword:'', newPassword:'', passwordConfirm:''};
    }

    $rootScope.errorValidation = function(){
        $rootScope.errors = {forgotPassword:false, showErrors:false, showMessage:false, showFunctionError:false, showServerError:false, showPasswordError:false};
        $scope.changePassword = {currentPassword:'', newPassword:'', passwordConfirm:''};
    };

    $scope.login = function(){
        DAO.save({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, controller:'auth', action:'login', username:$rootScope.user.username, passwordHash:$rootScope.user.passwordHash},
            function(result){
                if(result.response == "bad_login"){
                    $rootScope.errors.showErrors = true;
                    $rootScope.errors.showFunctionError = true;
                }
                else{
                    $rootScope.user = result;
                    $rootScope.appConfig.token = result.token;
                    delete $rootScope.user.token;
                }
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    };

    $rootScope.logout = function(){
        DAO.get({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller:'auth', action:'logout'},
            function(result){
                initializeVariables();
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    };

    $scope.register = function(){
        DAO.save({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller:'user', action:'newItem', user:$rootScope.user},
            function(result){
                if(result.response == "user_created"){
                    $rootScope.errors.showMessage = true;
                }
                else if(result.response == "email_used"){
                    $rootScope.errors.showErrors = true;
                    $rootScope.errors.showFunctionError = true;
                }
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    };

    $scope.changePassword = function(){
        if($scope.changePassword.newPassword == $scope.changePassword.passwordConfirm){
            DAO.update({serverHost:$rootScope.appConfig.serverHost, appName:$rootScope.appConfig.appName, token:$rootScope.appConfig.token, controller:'user', action:'changePassword', currentPassword:$scope.changePassword.currentPassword, newPassword:$scope.changePassword.newPassword},
                function(result){
                    if(result.response == "user_updated"){
                        $rootScope.errors.showMessage = true;
                    }
                    else if(result.response == "password_incorrect"){
                        $rootScope.errors.showErrors = true;
                        $rootScope.errors.showFunctionError = true;
                    }
                },
                function(error){
                    $rootScope.errors.showErrors = true;
                    $rootScope.errors.showServerError = true;
                });
        }
        else{
            $rootScope.errors.showErrors = true;
            $rootScope.errors.showPasswordError = true;
        }
    };

    $scope.loadProfile = function(){
        DAO.get({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller:'user', action:'getCurrent'},
            function(result){
                $rootScope.user = result;
                delete $rootScope.user.token;
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    };

    $scope.updateProfile= function(){
        DAO.update({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller:'user', action:'update',user:$rootScope.user},
            function(result){
                if(result.response == "user_not_updated"){
                    $rootScope.errors.showErrors = true;
                    $rootScope.errors.showServerError = true;
                }
                else if(result.response == "email_used"){
                    $rootScope.errors.showErrors = true;
                    $rootScope.errors.showFunctionError = true;
                }
                else if(result.response == "user_updated"){
                    $rootScope.errors.showMessage = true;
                }
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    };
}