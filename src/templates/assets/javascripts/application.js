//= require jquery/jquery
//= require bootstrap/bootstrap
//= require angular/angular
//= require angular-route/angular-route
//= require angular-resource/angular-resource.min
//= require angular-table/ng-table
//= require $app/index
//= require $app/services
//= require $app/arrestedDirectives
//= require_tree custom-$app
//= require_tree views
//= require_self


if (typeof jQuery !== 'undefined') {
	(function(\$) {
		\$('#spinner').ajaxStart(function() {
			\$(this).fadeIn();
		}).ajaxStop(function() {
			\$(this).fadeOut();
		});
	})(jQuery);
}
