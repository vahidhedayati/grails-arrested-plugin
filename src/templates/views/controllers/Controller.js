'use strict';
function @controller.name
@(DAO, $rootScope)
{
    if (!$rootScope.appConfig) {
        window.location.href = "#/login"
    }
    $rootScope.filter = ""
    $rootScope.@class.instance
    @s = {};
    $rootScope.@class.instance
    @ = [];
    $rootScope.flags = {save: false};
    $rootScope.errors = {showErrors: false, showServerError: false};

    $rootScope.getAll = function () {
        //get all
        DAO.query({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller: '@class.instance@', action: 'getAll'},
            function (result) {
                $rootScope.@class.instance
                @s = result;
            },
            function (error) {
            });
    };

    $rootScope.new
    @
class.
    name
    @ = function () {
        $rootScope.@class.instance
        @ = [];
        window.location.href = "#/@class.instance@/create"
    }

    $rootScope.manualSave = function () {
        $rootScope.flags.save = false;
        if ($rootScope.@class.instance@.id == ''
        )
        {
            $rootScope.save();
        }
        else
        {
            $rootScope.update();
        }
    }

    $rootScope.save = function () {
        DAO.save({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token,
        @
        class.
        instance
        @
        :
        $rootScope.@class.instance
        @, controller
        :
        '@class.instance@', action
        :
        'newItem'
    },
        function (result) {
            $rootScope.@class.instance
            @.id = result.id;
            $rootScope.flags.save = true;
        },
        function (error) {
            $rootScope.flags.save = false;
            $rootScope.errors.showErrors = true;
            $rootScope.errors.showServerError = true;
        }
)
    ;
}

$rootScope.update = function () {
    DAO.update({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token,
    @
    class.
    instance
    @
    :
    $rootScope.@class.instance
    @, controller
    :
    '@class.instance@', action
    :
    'update'
},
    function (result) {
        $rootScope.flags.save = true;
    },
    function (error) {
        $rootScope.flags.save = false;
        $rootScope.errors.showErrors = true;
        $rootScope.errors.showServerError = true;
    }
)
;
}

$rootScope.edit = function (@
class.
instance
@
)
{
    DAO.get({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, id: @class.instance
    @.id, controller
:
    '@class.instance@', action
:
    'getById'
}
,
function (result) {
    $rootScope.@class.instance
    @ = result;
    window.location.href = "#/@class.instance@/edit"
}
,
function (error) {
    $rootScope.errors.showErrors = true;
    $rootScope.errors.showServerError = true;
}
)
;
}

$rootScope.confirmDelete
@
class.
name
@ = function () {
    DAO.delete({serverHost: $rootScope.appConfig.serverHost, appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, id: $rootScope.@class.instance
    @.id, controller
    :
    '@class.instance@', action
    :
    'delete'
},
    function (result) {
        if (result.response == "@class.name@_deleted") {
            window.location.href = "#/@class.instance@/list"
        }
    },
    function (error) {
        $rootScope.errors.showErrors = true;
        $rootScope.errors.showServerError = true;
    }
)
;
}
}

