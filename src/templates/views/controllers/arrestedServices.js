var app=angular.module('arrestedServices',[]).

app.factory('LangService',["\$http","\$rootScope", function(\$http,\$rootScope) {
	   var userLocale= {
	 myres:null,
		getLang: function() {
		  this.myres =\$http.get('auth/getLocale').then(function(r) {\$rootScope.userLocale=r.data.lang;}); 
		}
	  }
	   userLocale.getLang();
	   return userLocale;
	}]);
