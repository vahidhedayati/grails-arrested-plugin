'use strict';
function UserCtrl($rootScope, DAO){

    $rootScope.changePassword = {currentPassword:'', newPassword:'', passwordConfirm:''};

    if(!$rootScope.appConfig){
        $rootScope.appConfig = {serverHost:'localhost:8080', appName:'@app.name@', token:''};
        $rootScope.user = {username:'', passwordHash:''};
        $rootScope.errors = {forgotPassword:false, showErrors:false, showMessage:false, showFunctionError:false, showServerError:false, showPasswordError:false};
    }

    function initializeVariables(){
        $rootScope.appConfig = {serverHost:'localhost:8080', appName:'@app.name@', token:''};
        $rootScope.user = {username:'', passwordHash:''};
        $rootScope.errors = {forgotPassword:false, showErrors:false, showMessage:false, showFunctionError:false, showServerError:false, showPasswordError:false};
        $rootScope.changePassword = {currentPassword:'', newPassword:'', passwordConfirm:''};
    }

    $rootScope.errorValidation = function(){
        $rootScope.errors = {forgotPassword:false, showErrors:false, showMessage:false, showFunctionError:false, showServerError:false, showPasswordError:false};
        $rootScope.changePassword = {currentPassword:'', newPassword:'', passwordConfirm:''};
    };

    $rootScope.login = function(){
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
                    window.location.href="/@app.name@/"
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
                window.location.href="/@app.name@/"
            },
            function(error){
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
            });
    };

    $rootScope.register = function(){
        DAO.save({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller:'user', action:'newItem', user:$rootScope.user},
            function(result){
                if(result.response == "user_created"){
                    $rootScope.errors.showMessage = true;
                    window.location.href="/@app.name@/"
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

    $rootScope.changePassword = function(){
        if($rootScope.changePassword.newPassword == $rootScope.changePassword.passwordConfirm){
            DAO.update({serverHost:$rootScope.appConfig.serverHost, appName:$rootScope.appConfig.appName, token:$rootScope.appConfig.token, controller:'user', action:'changePassword', currentPassword:$rootScope.changePassword.currentPassword, newPassword:$rootScope.changePassword.newPassword},
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

    $rootScope.loadProfile = function(){
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

    $rootScope.updateProfile= function(){
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