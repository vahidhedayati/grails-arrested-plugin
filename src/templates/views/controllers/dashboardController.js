'use strict';
function DashboardCtrl(\$scope,LangService,\$cacheFactory,\$templateCache){
    \$scope.dashboard=function() { 
    	\$templateCache.removeAll();
    	\$cacheFactory.get('\$http').removeAll();
	LangService.getLang()
	  .then(function(data) {
	    \$scope.myLang = data;
	});
	\$scope.userLocale2 = function() {
	  LangService.getLang2()
	    .then(function(data) {
	      \$scope.userLocale = data;
	  });
	  //window.location.href="#/"
	};	
    }
    
}
